<template>
  <div class="photo">
    <div class="banner">
      <div class="animate__animated animate__slideInDown banner_video">
        <video autoplay loop muted>
          <source :src="bgUrl" type="video/mp4">
        </video>
        <div class="banner_nav">
          <div class="top">
            <span class="top_link">
              www.baidu.com
            </span>
            <span class="top_title">
              记忆照片
            </span>
            <span class="top_msg">
              时光定格美好,记忆永存快乐
            </span>
          </div>
          <div class="botton">
            <div class="target pointer scaleDraw" v-for="item in PhotoSort" :key="item.id" @click="searchSort(item.id)">
              <svg class="icon pointer" aria-hidden="true" @click="goDown">
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
      <div class="photo box_shadow pointer animate__animated animate__zoomIn" v-for="item in PhotoList" :key="item.id">
        <div class="photo_img">
          <img v-lazy="item.photoUrl" class="box_shadow">
        </div>
        <div class="photo_msg">
          <span>
            {{ item.photoName }}
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            {{ item.createTime }}
          </span>
        </div>
        <div class="photo_name">
          <span>
            发布者: {{ item.userName }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, onUnmounted, ref} from "vue"
import {useBgStore} from '@/store/bg'
import {listPhoto} from '@/api/photo'
import {listSort} from '@/api/sort/photoSort'

//视频背景
const bgUrl = ref(useBgStore().GET_BGLIST_BYTYPE("3"))
//相册列表
const PhotoList = ref([])
//相册分类
const PhotoSort = ref([])
//查询参数
const queryParam = ref({
  sortId: null,
  pageNum: 1,
  pageSize: 20,
})
//是否滚动到底部
const loadingEnd = ref(false)
//是否加载中
const loading = ref(false)
onMounted(() => {
  listPhoto(queryParam.value).then(res => {
    PhotoList.value = res.rows;
  })
  listSort().then(res => {
    PhotoSort.value = res.rows;
  })
  window.addEventListener('scroll', handleScroll)
})
onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll)
})
//切换分类
function searchSort(sortId) {
  loading.value = false;
  loadingEnd.value = false;
  queryParam.value = {
    pageNum: 1,
    pageSize: 8,
  }
  queryParam.value.sortId = sortId
  listPhoto(queryParam.value).then(res => {
    if (isLastPage(res.total)) {
      loadingEnd.value = true;
    }
    PhotoList.value = res.rows
  })
}
//加载数据
const loadData = () => {
  loading.value = true
  queryParam.value.pageNum += 1;
  listPhoto(queryParam.value).then(res => {
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
  if (isScrolledToBottom(photos,banner)) {
    loadData()
  }
}
//判断是否滚动到底部
function isScrolledToBottom(photos,banner) {
  const clientHeight = photos.clientHeight; // 容器的视口高度  
  const windowY = window.scrollY; // 浏览器窗口高度
  //当前窗口高度 高于滚动窗口高度 并且 loading不是加载中
  return windowY >= clientHeight - banner.clientHeight && !loading.value && !loadingEnd.value;
}
//判断是不是到最后一页了
function isLastPage(total) {
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
    }

    .banner_nav {
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
        color: #222831;

        .top_link {
          font-size: 14px;
        }

        .top_title {
          font-size: 26px;
        }

        .top_msg {
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
          box-sizing: border-box;
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
      box-sizing: border-box;
      width: 20%;
      border-radius: 10px;
      padding-bottom: 0;
      margin: 20px;
      max-height: 300px;

      .photo_msg {
        display: flex;
        align-items: center;
        width: 80%;
        height: auto;

        span {
          font-size: 16px;
        }
      }

      .photo_img {
        flex-grow: 1;
        display: flex;
        justify-content: center;
        width: 80%;
        height: auto;
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

      .photo_time {
        .icon {
          font-size: 14px;
        }

        display: flex;
        align-items: center;
        width: 80%;
        height: 10%;
        font-size: 14px;
      }

      .photo_name {
        display: flex;
        align-items: center;
        width: 80%;
        height: 10%;
        font-size: 14px;
      }
    }
  }
}
</style>