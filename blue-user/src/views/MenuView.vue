<template>
  <div class="menu">
    <div class="menu-left">
      <div class="nav pointer" @click="changSelect(0)">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-wode"></use>
        </svg>
        <span :class="selectIndex === 0 ? 'nav-select' : ''">个人信息</span>
      </div>
      <div class="nav pointer" @click="changSelect(1)">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-shu1"></use>
        </svg>
        <span :class="selectIndex === 1 ? 'nav-select' : ''">我的文章</span>
      </div>
      <div class="nav pointer" @click="changSelect(2)">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-faxian"></use>
        </svg>
        <span :class="selectIndex === 2 ? 'nav-select' : ''">分享二创</span>
      </div>
      <div class="nav pointer" @click="changSelect(3)">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-xiangji"></use>
        </svg>
        <span :class="selectIndex === 3 ? 'nav-select' : ''">我的相册</span>
      </div>
      <div class="nav pointer" @click="changSelect(4)">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-jita"></use>
        </svg>
        <span :class="selectIndex === 4 ? 'nav-select' : ''">分享歌单</span>
      </div>
    </div>
    <div class="menu-right">
      <UserView :user="userStore" v-if="selectIndex === 0" />
      <ArticleView v-else-if="selectIndex === 1" />
      <FoundView v-else-if="selectIndex === 2" />
      <PhotoView v-else-if="selectIndex === 3" />
      <MusicView v-else-if="selectIndex === 4" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import UserView from '@/components/ShareUserDetail.vue';
import ArticleView from '@/components/ShareArticleDetail.vue';
import FoundView from '@/components/ShareFoundDetail.vue';
import PhotoView from '@/components/SharePhotoDetail.vue';
import MusicView from '@/components/ShareMusicDetail.vue'
import useStore from "@/store"
let { userStore } = useStore()

const selectIndex = ref(0)
function changSelect(index) {
  selectIndex.value = index;
}
</script>

<style lang="scss" scoped>
.menu {
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 100%;
  padding-top: 53px;

  .menu-left {
    width: 10%;
    height: 100%;

    .nav {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 100%;
      height: 30px;
      margin: 20px 0px 20px 0px;

      .icon {
        margin-right: 5px;
      }

      span {
        font-size: 14px;
      }
    }

    .nav-select {
      color: #00e0ff;
    }

    .nav:hover {
      color: #00e0ff;
    }
  }

  .menu-right {
    width: 90%;
    height: 100%;
    border-left: 1px solid #c8d9eb;
  }
}
</style>
