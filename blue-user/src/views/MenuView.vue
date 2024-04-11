<template>
  <div class="menu">
    <div class="menu_left">
      <div class="nav" @click="changSelect(0)">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-wode"></use>
        </svg>
        <span :class="selectIndex === 0 ? 'nav_select' : ''">个人信息</span>
      </div>
      <div class="nav" @click="changSelect(1)">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-shu1"></use>
        </svg>
        <span :class="selectIndex === 1 ? 'nav_select' : ''">分享文章</span>
      </div>
      <div class="nav" @click="changSelect(2)">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-faxian"></use>
        </svg>
        <span :class="selectIndex === 2 ? 'nav_select' : ''">分享发现</span>
      </div>
      <div class="nav" @click="changSelect(3)">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-xiangji"></use>
        </svg>
        <span :class="selectIndex === 3 ? 'nav_select' : ''">分享相片</span>
      </div>
      <div class="nav" @click="changSelect(4)">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-jita"></use>
        </svg>
        <span :class="selectIndex === 4 ? 'nav_select' : ''">分享歌单</span>
      </div>
    </div>
    <div class="menu_right">
      <UserView v-show="selectIndex === 0" />
      <ArticleView v-show="selectIndex === 1" />
      <FoundView v-show="selectIndex === 2" />
      <PhotoView v-show="selectIndex === 3" />
      <MusicView v-show="selectIndex === 4" />
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import UserView from '@/components/UserDetail.vue';
import ArticleView from '@/components/ShareArticleDetail.vue';
import FoundView from '@/components/ShareFoundDetail.vue';
import PhotoView from '@/components/SharePhotoDetail.vue';
import MusicView from '@/components/ShareMusicDetail.vue';

const selectIndex = ref(0)
function changSelect(index) {
  selectIndex.value=index;
}
</script>

<style lang="scss" scoped>
.menu {
  display: flex;
  width: 100%;
  height: 1000px;
  margin-top: 53px;

  .menu_left {
    display: flex;
    flex-wrap: wrap;
    flex-direction: column;
    width: 10%;
    height: 100%;
    border-right: 1px solid #c8d9eb;
    background-color: #dbe2ef;

    .nav {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 100%;
      height: 30px;
      margin: 10px 0px 10px 0px;

      .icon {
        margin-right: 5px;
      }

      span {
        font-size: 14px;
      }
    }

    .nav_select {
      color: #00e0ff;
    }

    .nav:hover {
      color: #00e0ff;
    }
  }

  .menu_right {
    width: 90%;
    height: 100%;
    background-color: #f9f7f7;
  }
}
</style>
