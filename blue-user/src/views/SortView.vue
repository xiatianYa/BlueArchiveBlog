<template>
  <div class="sort">
    <div class="banner no_select">
      <div class="animate__animated animate__slideInDown bg">
        <div class="bg_img">
          <img :src="bgUrl">
        </div>
        <div class="sorts">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-fenlei"></use>
          </svg>
          <div :id="sort.id == sortIndex ? 'select' : ''" class="sort pointer" v-for="sort in sortList"
            @click="selectSort(sort)">
            <span class="sort_name">
              {{ sort.sortName }}
            </span>
            <span class="sort_num">
              {{ sort.sortNumber }}
            </span>
          </div>
        </div>
        <div class="tags">
          <svg aria-hidden="true" class="icon" @click="goDown">
            <use xlink:href="#icon-biaoqian"></use>
          </svg>
          <div :id="tag.id == tagIndex ? 'select' : ''" class="tag pointer" v-for="tag in tagList"
            @click="selectArticleListByTagId(tag.id)">
            <span class="tag_name">
              {{ tag.tagName }}
            </span>
            <span class="tag_num">
              {{ tag.articleTagNumber }}
            </span>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="content">
        <div class="content_body">
          <SortDetail :article="article" v-for="article in articleList" @click="goArticlePreview(article.id)">
          </SortDetail>
        </div>
      </div>
    </div>
    <Loading v-show="loading" />
  </div>
</template>

<script setup lang="ts">
import SortDetail from '@/components/SortDetail.vue'
import { onMounted, onUnmounted, ref } from "vue"
import { listSort } from '@/api/sort/sort'
import { listTag } from '@/api/sort/tagSort'
import { listByTagId } from '@/api/article'
import { useRouter } from 'vue-router'
import { useMessage } from 'naive-ui'
import Loading from '@/components/CssLoadingView01.vue'
import useStore from "@/store"
let { globalStore } = useStore()
//提示框
const message = useMessage()

const router = useRouter()
const bgUrl = ref(globalStore.getBgByType("2"))
//分类下标
const sortIndex = ref(0)
//标签下标
const tagIndex = ref(0)
//分类列表
const sortList = ref([])
//标签列表
const tagList = ref({})
//文章列表
const articleList = ref([])
//查询参数
const queryParam = ref({
  pageNum: 1,
  pageSize: 10,
})
//是否滚动到底部
const loadingEnd = ref(false)
//是否加载中
const loading = ref(false)
onMounted(() => {
  init()
})
onUnmounted(() => {
  //移除滚动事件
  window.removeEventListener('scroll', handleScroll)
})
//初始化
function init() {
  //添加滚动事件
  window.addEventListener('scroll', handleScroll)
  //获取分类列表
  listSort().then(res => {
    sortList.value = res.rows
    //获取标签列表
    listTag().then(res => {
      //遍历标签列表 放入分类列表中
      for (let index = 0; index < sortList.value.length; index++) {
        const sort = sortList.value[index];
        //清空默认标签列表
        sort.tagList = [];
        //遍历所有标签
        for (const tag of res.rows) {
          //如果标签的分类ID和分类ID匹配则进入
          if (tag.sortId == sortList.value[index].id) {
            //如果sortList.value[index].tagList未空 则添加一个空列表
            if (!sortList.value[index].tagList) {
              //如果分类列表中标签列表为空 则为空列表
              sortList.value[index].tagList = []
            }
            //如果不为空则添加入标签列表中
            sortList.value[index].tagList.push(tag)
          }
        }
      }
      //获取从路由传递过来的分类参数
      if (router.currentRoute.value.query.sortId) {
        sortIndex.value = router.currentRoute.value.query.sortId
      } else {
        //设置第一个分类下标
        if (sortIndex.value === 0) {
          sortIndex.value = sortList.value[0].id
        }
      }
      //获取从路由传递过来的标签参数
      if (router.currentRoute.value.query.tagId) {
        tagIndex.value = router.currentRoute.value.query.tagId
        //设置分类ID下的标签列表
        const sort = sortList.value.find(item => item.id == sortIndex.value)
        tagList.value = sort.tagList;
      } else {
        //如果是从点击分类过来的 则匹配分类下的标签列表
        if (router.currentRoute.value.query.sortId) {
          //查询分类下标签列表
          const sort = sortList.value.find(item => item.id == sortIndex.value)
          tagList.value = sort.tagList;
        } else {
          //设置标签列表
          tagList.value = sortList.value[0].tagList
          //设置第一个标签下标
          if (tagList.value) {
            tagIndex.value = tagList.value[0].id
          }
        }
      }
      //查询文章列表
      listByTagId(tagIndex.value, queryParam.value).then(res => {
        if (isLastPage(res.total)) {
          loadingEnd.value = true;
        }
        articleList.value = res.rows
      })
    })
  })
}
//跳转到文章浏览页
function goArticlePreview(articleId) {
  router.push({ path: '/editPreView', query: { articleId: articleId } })
}
//设置标签下标 和标签列表
function selectSort(sort) {
  //清除查询列表
  queryParam.value = {
    pageNum: 1,
    pageSize: 10
  }
  //清除加载和到底
  loadingEnd.value = false;
  loading.value = false;
  articleList.value = []
  //设置下标
  sortIndex.value = sort.id
  //如果列表未空 则添加空列表
  if (!sort.tagList) {
    tagList.value = []
  } else {
    //设置标签列表    
    tagList.value = sort.tagList
    tagIndex.value = tagList.value[0].id
    //查询文章列表
    listByTagId(tagIndex.value, queryParam.value).then(res => {
      if (isLastPage(res.total)) {
        loadingEnd.value = true;
      }
      articleList.value = res.rows
    })
  }
}
//查询标签下的文章列表
function selectArticleListByTagId(tagId) {
  //清除查询列表
  queryParam.value = {
    pageNum: 1,
    pageSize: 10
  }
  //清除加载和到底
  loadingEnd.value = false;
  loading.value = false;
  articleList.value = []
  //如果点击的是当前选择的标签下标则不选择
  if (tagId === tagIndex.value) {
    return
  }
  tagIndex.value = tagId;
  listByTagId(tagId, queryParam.value).then(res => {
    if (isLastPage(res.total)) {
      loadingEnd.value = true;
    }
    articleList.value = res.rows
  }).catch(error => {
    message.error(error)
  })
}
//滚动事件
const handleScroll = () => {
  // 监听滚动事件
  const photos = document.querySelector('.container') // 获取滚动容器
  const banner = document.querySelector('.banner') // 获取滚动容器
  if (isScrolledToBottom(photos, banner)) {
    loadData()
  }
}
//判断是否滚动到底部
function isScrolledToBottom(photos, banner) {
  const clientHeight = photos.clientHeight; // 容器的视口高度  
  const windowY = window.scrollY; // 浏览器窗口高度
  //当前窗口高度 高于滚动窗口高度 并且 loading不是加载中
  return windowY >= clientHeight - banner.clientHeight && !loading.value && !loadingEnd.value;
}
//判断是不是到最后一页了
function isLastPage(total) {
  if (total < queryParam.value.pageSize) {
    return true;
  }
  // 计算总页数  
  var totalPages = Math.ceil(total / queryParam.value.pageSize + 1);
  // 如果当前页码等于总页数，那么就是最后一页  
  return queryParam.value.pageNum >= totalPages;
}
//加载数据
const loadData = () => {
  loading.value = true
  queryParam.value.pageNum += 1;
  listByTagId(tagIndex.value, queryParam.value).then(res => {
    for (const item of res.rows) {
      if (isLastPage(res.total)) {
        loadingEnd.value = true;
      }
      articleList.value.push(item)
    }
    loading.value = false;
  })
}
</script>

<style lang="scss" scoped>
.sort {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  transition: all 0.5s ease;
  padding-bottom: 40px;

  .banner {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 400px;
    padding-top: 60px;

    .bg {
      width: 90%;
      height: 100%;
      border-radius: 25px;
      overflow: hidden;
      position: relative;

      .bg_img {
        width: 100%;
        height: 100%;

        img {
          width: 100%;
          height: 100%;
          object-fit: cover;
        }
      }


      .sorts {
        position: absolute;
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 90%;
        height: 15%;
        padding: 0 20px;
        left: 5%;
        top: 60%;
        box-sizing: border-box;
        border-radius: 10px;
        border: 2px dashed black;
        overflow-x: auto;
        -webkit-overflow-scrolling: touch;

        .icon {
          vertical-align: -0.15em;
          fill: currentColor;
          overflow: hidden;
          font-size: 22px;
        }

        .sort {
          display: flex;
          flex-direction: row;
          justify-content: center;
          align-items: center;
          padding: 3px;
          border-radius: 10px;

          .sort_name {
            display: flex;
            align-items: center;
            color: #ff9a00;
          }

          .sort_num {
            display: flex;
            align-items: center;
            padding-bottom: 5px;
            color: #ff9a00;
          }
        }

        .sort:hover {
          background-color: #74f9ff;
        }
      }

      .tags {
        position: absolute;
        display: flex;
        justify-content: left;
        align-items: center;
        width: 90%;
        height: 15%;
        padding: 0 20px;
        left: 5%;
        top: 80%;
        box-sizing: border-box;
        border-radius: 10px;
        border: 2px dashed black;
        overflow-x: auto;
        -webkit-overflow-scrolling: touch;

        .icon {
          vertical-align: -0.15em;
          fill: currentColor;
          overflow: hidden;
          font-size: 22px;
        }

        .tag {
          display: flex;
          flex-direction: row;
          justify-content: center;
          align-items: center;
          margin-right: 20px;
          border-radius: 10px;
          padding: 3px;

          .tag_name {
            display: flex;
            align-items: center;
            color: #ff9a00;
          }

          .tag_num {
            display: flex;
            align-items: center;
            padding-bottom: 5px;
            color: #ff9a00;
          }
        }

        .tag:hover {
          background-color: #74f9ff;
        }
      }

      .sorts_tags::-webkit-scrollbar {
        display: none;
      }

      .sorts::-webkit-scrollbar {
        display: none;
      }
    }
  }

  .container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    min-height: 400px;
    margin-top: 30px;

    .content {
      width: 80%;
      height: 100%;

      .content_body {
        display: flex;
        justify-content: space-between;
        flex-direction: row;
        flex-wrap: wrap;
        align-items: center;
      }
    }
  }
}

//被选择的效果
#select {
  background-color: #8ef6e4;
}

#select:hover {
  background-color: #8ef6e4 !important;
}
</style>