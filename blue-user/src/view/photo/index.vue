<template>
  <div class="photo">
    <div class="banner">
      <div class="animate__animated animate__slideInDown bg">
        <div class="bg-img">
          <img :src="bgUrl">
        </div>
        <div class="banner-nav">
          <div class="top">
            <span class="top-link">
              www.bluearchive.top
            </span>
            <span class="top-title">
              记忆照片
            </span>
            <span class="top-msg">
              时光定格美好,记忆永存快乐
            </span>
          </div>
          <div class="botton">
            <div class="target pointer scaleDraw" :id="item.id == sortIndex ? 'select' : ''" v-for="item in PhotoSort"
              :key="item.id" @click="searchSort(item.id)">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-sekuaibiaoqian"></use>
              </svg>
              <span>
                {{ item.sortName }}
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="photos">
      <div class="photo box-shadow pointer animate__animated animate__zoomIn" v-for="item in PhotoList" :key="item.id">
        <div class="photo-img">
          <n-image width="100%" :src="item.photoUrl" />
        </div>
        <div class="photo-msg">
          <span>
            {{ item.photoName }}
          </span>
        </div>
        <div class="photo-name">
          <span>
            发布者: {{ item.userName }}
          </span>
        </div>
        <div class="photo-time">
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            {{ item.createTime }}
          </span>
        </div>
      </div>
    </div>
    <Loading v-show="loading" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted, ref } from "vue"
import { listPhoto } from '@/api/photo'
import { listSort } from '@/api/sort/photoSort'
import { NImage } from 'naive-ui'
let { globalStore } = useStore()
import useStore from "@/store"
import Loading from '@/components/loading/CssLoadingView01.vue'

//视频背景
const bgUrl = ref(globalStore.getBgByType("3"))
//相册列表
const PhotoList = ref(<any>[])
//相册分类
const PhotoSort = ref(<any>[])
//查询参数
const queryParam = ref({
  sortId: 0,
  pageNum: 1,
  pageSize: 20,
})
//是否滚动到底部
const loadingEnd = ref(false)
//是否加载中
const loading = ref(false)
//当前选中的分类ID
const sortIndex = ref()
onMounted(() => {
  init()
})
onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll)
})
//初始化
function init() {
  loading.value = true;
  listSort().then((res: any) => {
    PhotoSort.value = res.rows;
    queryParam.value.sortId = PhotoSort.value[0].id;
    sortIndex.value = PhotoSort.value[0].id;
    listPhoto(queryParam.value).then((res: any) => {
      if (isLastPage(res.total)) {
        loadingEnd.value = true;
      }
      PhotoList.value = res.rows;
      loading.value = false;
    })
  })
  window.addEventListener('scroll', handleScroll)
}
//切换分类
function searchSort(sortId: number) {
  loading.value = false;
  loadingEnd.value = false;
  queryParam.value = {
    sortId: sortId,
    pageNum: 1,
    pageSize: 8,
  }
  sortIndex.value = sortId;
  listPhoto(queryParam.value).then((res: any) => {
    if (isLastPage(res.total)) {
      loadingEnd.value = true;
    }
    PhotoList.value = res.rows;
  })
}
//加载数据
const loadData = () => {
  loading.value = true
  queryParam.value.pageNum += 1;
  listPhoto(queryParam.value).then((res: any) => {
    for (const item of res.rows) {
      if (isLastPage(res.total)) {
        loadingEnd.value = true;
      }
      PhotoList.value.push(item)
    }
    loading.value = false;
  })
}
//滚动事件
const handleScroll = () => {
  // 监听滚动事件
  const photos = document.querySelector('.photos') // 获取滚动容器
  const banner = document.querySelector('.banner') // 获取滚动容器
  if (isScrolledToBottom(photos, banner)) {
    loadData()
  }
}
//判断是否滚动到底部
function isScrolledToBottom(photos: any, banner: any) {
  const clientHeight = photos.clientHeight; // 容器的视口高度  
  const windowY = window.scrollY; // 浏览器窗口高度
  //当前窗口高度 高于滚动窗口高度 并且 loading不是加载中
  return windowY >= clientHeight - banner.clientHeight && !loading.value && !loadingEnd.value;
}
//判断是不是到最后一页了
function isLastPage(total: number) {
  if (total < queryParam.value.pageSize) {
    return true;
  }
  // 计算总页数  
  var totalPages = Math.ceil(total / queryParam.value.pageSize);
  // 如果当前页码等于总页数，那么就是最后一页  
  return queryParam.value.pageNum >= totalPages;
}
</script>

<style lang="scss" scoped>
.photo {
  display: flex;
  flex-direction: column;
  align-items: center;
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

      .bg-img {
        width: 100%;
        height: 100%;

        img {
          width: 100%;
          height: 100%;
          object-fit: cover;
        }
      }

    }

    .banner-nav {
      position: absolute;
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
      width: 25%;
      height: 100%;
      right: 20px;
      top: 0%;

      .top {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        align-items: center;

        .top-link {
          font-size: 14px;
        }

        .top-title {
          font-size: 26px;
        }

        .top-msg {
          font-size: 14px;
        }

        span {
          width: 100%;
        }
      }

      .botton {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: space-between;

        .target {
          display: flex;
          justify-content: center;
          width: 40%;
          max-height: 30px;
          border-radius: 5px;
          background-color: #f08a5d;

          .icon {
            vertical-align: -0.15em;
            fill: currentColor;
            overflow: hidden;
            font-size: 26px;
            height: 100%;
          }

          span {
            display: flex;
            align-items: center;
          }
        }

        .target:hover {
          transition: all ease 0.5s;
          background-color: #e23e57;
        }
      }
    }
  }

  .photos {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    width: 90%;
    height: 100%;
    min-height: 400px;
    margin-top: 30px;

    .photo {
      display: flex;
      justify-content: space-between;
      width: 20%;
      border-radius: 10px;
      padding-bottom: 0;
      margin: 20px;
      max-height: 300px;

      .photo-img {
        flex-grow: 1;
        display: flex;
        justify-content: center;
        width: 90%;
        max-height: 60%;
        min-height: 60%;
        overflow: hidden;
        border-radius: 10px;
        margin-top: 10px;

        img {
          width: 100%;
          object-fit: cover;
          transition: all .3s ease-in 0s;
          border-radius: 10px;
        }

        img:hover {
          object-fit: cover;
          transform: scale(1.1);
          transition: all 0.3s ease-in;
        }
      }

      .photo-msg {
        display: flex;
        align-items: center;
        width: 80%;
        height: auto;

        span {
          font-size: 14px;
        }
      }

      .photo-time {
        .icon {
          font-size: 14px;
        }

        display: flex;
        align-items: center;
        width: 80%;
        height: 10%;
        font-size: 12px;
      }

      .photo-name {
        display: flex;
        align-items: center;
        width: 80%;
        height: 10%;
        font-size: 14px;
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