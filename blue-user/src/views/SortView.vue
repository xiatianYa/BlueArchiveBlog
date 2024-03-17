<template>
  <div class="sort">
    <div class="banner">
      <div class="animate__animated animate__slideInDown banner_video">
        <video autoplay loop muted>
          <source
              :src="bgUrl"
              type="video/mp4">
        </video>
        <div class="sorts">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
            <use xlink:href="#icon-biaoqian"></use>
          </svg>
          <div id="select" class="sort">
            <span class="sort_name">
              Java
            </span>
            <span class="sort_num">
              3
            </span>
          </div>
          <div class="sort">
            <span class="sort_name">
              Java
            </span>
            <span class="sort_num">
              3
            </span>
          </div>
          <div class="sort">
            <span class="sort_name">
              Java
            </span>
            <span class="sort_num">
              3
            </span>
          </div>
          <div class="sort">
            <span class="sort_name">
              Java
            </span>
            <span class="sort_num">
              3
            </span>
          </div>
          <div class="sort">
            <span class="sort_name">
              Java
            </span>
            <span class="sort_num">
              3
            </span>
          </div>
          <div class="sort">
            <span class="sort_name">
              Java
            </span>
            <span class="sort_num">
              3
            </span>
          </div>
          <div class="sort">
            <span class="sort_name">
              Java
            </span>
            <span class="sort_num">
              3
            </span>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="content animate__animated animate__zoomIn">
        <div class="content_body">
          <SortDetail></SortDetail>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue"
import SortDetail from '@/components/SortDetail.vue'
import {useBgStore} from '@/store/bg'

const bgUrl = ref(useBgStore().GET_BGLIST_BYTYPE("2"))
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
        top: 80%;
        box-sizing: border-box;
        border-radius: 10px;
        border: 2px dashed #c9d6df;
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
          width: 50px;
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
