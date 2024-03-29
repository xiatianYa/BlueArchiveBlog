<template>
  <div class="sort">
    <div class="banner">
      <div class="animate__animated animate__slideInDown banner_video">
        <video autoplay loop muted>
          <source :src="bgUrl" type="video/mp4">
        </video>

        <div class="sorts">
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-fenlei"></use>
          </svg>
          <div :id="sort.id == sortIndex ? 'select' : ''" class="sort" v-for="sort in sortList"
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
          <svg aria-hidden="true" class="icon pointer" @click="goDown">
            <use xlink:href="#icon-biaoqian"></use>
          </svg>
          <div :id="tag.id == tagIndex ? 'select' : ''" class="sort" v-for="tag in tagList"
            @click="selectArticleListByTagId(tag.id)">
            <span class="sort_name">
              {{ tag.tagName }}
            </span>
            <span class="sort_num">
              {{ tag.articleTagNumber }}
            </span>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="content">
        <div class="content_body">
          <SortDetail :articleList="articleList"></SortDetail>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import SortDetail from '@/components/SortDetail.vue'
import {onMounted, ref} from "vue"
import {useBgStore} from '@/store/bg'
import {listSort} from '@/api/sort/sort'
import {listTag} from '@/api/sort/tag'
import {listByTagId} from '@/api/article'
import {useRouter} from 'vue-router'
import promptMsg from "@/components/PromptBoxView"

const router = useRouter()
const bgUrl = ref(useBgStore().GET_BGLIST_BYTYPE("2"))
//分类下标
const sortIndex = ref(0)
//标签下标
const tagIndex = ref(0)
//分类列表
const sortList = ref({})
//标签列表
const tagList = ref({})
//文章列表
const articleList = ref([])
onMounted(() => {
  //获取从路由传递过来的参数
  if (router.currentRoute.value.query.sortId) {
    sortIndex.value=router.currentRoute.value.query.sortId
  }
  //获取分类列表
  listSort().then(res => {
    sortList.value = res.rows
    //获取标签列表
    listTag().then(res => {
      //遍历标签列表 放入分类列表中
      for (let index = 0; index < sortList.value.length; index++) {
        const sort = sortList.value[index];
        //遍历所有标签1
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
      //设置第一个分类下标
      if (sortIndex.value===0) {
        sortIndex.value = sortList.value[0].id
      }
      //设置标签列表
      tagList.value = sortList.value[0].tagList
      //设置第一个标签下标
      if (tagList.value) {
        tagIndex.value = tagList.value[0].id
      }
    })

  })
})
//设置标签下标 和标签列表
function selectSort(sort) {
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
    listByTagId(tagIndex.value).then(res => {
      articleList.value = res.rows
    }).catch(error => {
      promptMsg({ type: "success", msg: error })
    })
  }
}
//查询所有标签下的文章列表
function selectArticleListByTagId(tagId) {
  //如果点击的是当前选择的标签下标则不选择
  if (tagId === tagIndex.value) {
    return
  }
  listByTagId(tagId).then(res => {
    articleList.value = res.rows
    promptMsg({ type: "success", msg: res.msg })
  }).catch(error => {
    promptMsg({ type: "success", msg: error })
  })
}
</script>

<style lang="scss" scoped>
.sort {
  display: flex;
  flex-direction: column;
  transition: all 0.5s ease;
  padding-bottom: 40px;

  .banner {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 400px;
    padding-top: 60px;

    .banner_video {
      width: 90%;
      height: 100%;
      border-radius: 25px;
      overflow: hidden;
      position: relative;

      video {
        width: 100%;
        height: 400px;
        object-fit: cover;
        z-index: -1;
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
          margin-right: 20px;
          border-radius: 5px;
          padding: 0;

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

        .sort {
          display: flex;
          flex-direction: row;
          justify-content: center;
          align-items: center;
          margin-right: 20px;
          border-radius: 5px;
          padding: 0;

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
    justify-content: center;
    align-items: center;
    width: 100%;
    min-height: 400px;
    margin-top: 30px;

    .content {
      width: 80%;
      height: 100%;
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
