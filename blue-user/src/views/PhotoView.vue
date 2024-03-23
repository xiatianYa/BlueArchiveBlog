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
    <div class="photos animate__animated animate__zoomIn">
      <div class="photo box_shadow pointer" v-for="item in PhotoList" :key="item.id">
        <div class="photo_img">
          <img class="box_shadow" :src="item.photoUrl">
        </div>
        <div class="photo_msg">
          <span>
            {{ item.photoName }}
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
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

<script setup lang="ts">
import {onMounted, ref} from "vue"
import {useBgStore} from '@/store/bg'
import {listPhoto} from '@/api/photo'
import {listSort} from '@/api/sort/photo'

const bgUrl = ref(useBgStore().GET_BGLIST_BYTYPE("3"))
const PhotoList = ref([])
const PhotoSort = ref([])
//查询参数
const queryParam = ref({
    pageNum: 1,
    pageSize: 10,
    userId: null,
    sortId: null,
    photoUrl: null,
    status: null,
  })
onMounted(() => {
  listPhoto().then(res => {
    PhotoList.value = res.rows;
  })
  listSort().then(res => {
    PhotoSort.value = res.rows;
  })
})
//切换分类
function searchSort(sortId) {
  queryParam.value.sortId=sortId
  listPhoto(queryParam.value).then(res => {
    PhotoList.value = res.rows
  })
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
    justify-content: space-between;
    width: 90%;
    height: 100%;
    margin-top: 30px;

    .photo {
      box-sizing: border-box;
      width: 23%;
      height: 400px;
      border-radius: 10px;
      margin-bottom: 30px;

      .photo_msg {
        display: flex;
        align-items: center;
        width: 80%;
        height: 10%;

        span {
          font-size: 16px;
        }
      }

      .photo_img {
        display: flex;
        justify-content: center;
        width: 80%;
        height: 80%;
        overflow: hidden;
        border-radius: 10px;
        margin-top: 10px;

        img {
          width: 100%;
          height: 100%;
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
