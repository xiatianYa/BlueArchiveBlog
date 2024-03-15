<template>
  <transition name="fade">
    <div class="header_box header_fixed" v-show="!gloBalStore.loading && NavShow">
      <div class="pc_menu">
        <div class="header_log">
          <img src="\static\images\logo.png" alt="碧蓝档案log"/>
        </div>
        <div class="header_menu">
          <ul class="menus">
            <router-link to="/" class="li">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-shouye"></use>
              </svg>
              <span class="pointer">首页</span>
            </router-link>
            <router-link to="/found" class="li">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-faxian"></use>
              </svg>
              <span class="pointer">发现</span>
            </router-link>
            <router-link to="/sort" class="li">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-fenlei"></use>
              </svg>
              <span class="pointer">分类</span>
            </router-link>
            <router-link to="/photo" class="li">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-xiangce"></use>
              </svg>
              <span class="pointer">相册</span>
            </router-link>
            <router-link to="/friend" class="li">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-jiqiren"></use>
              </svg>
              <span class="pointer">友链</span>
            </router-link>
            <router-link to="/leave" class="li">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-xiaoxi"></use>
              </svg>
              <span class="pointer">留言</span>
            </router-link>
            <router-link to="/song" class="li">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-jita"></use>
              </svg>
              <span class="pointer">听歌</span>
            </router-link>
            <router-link to="/user" class="li">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-wode"></use>
              </svg>
              <span class="pointer">登录</span>
            </router-link>
          </ul>
        </div>
      </div>
    </div>
  </transition>
</template>
<script setup lang="ts">
import {ref, reactive, onMounted, onBeforeMount} from 'vue'
import {useGloBalStore} from '@/stores/global'

const gloBalStore = useGloBalStore()
const scrollPosition = reactive({x: 0, y: 0})
const NavShow = ref(true)
onMounted(() => {
  window.addEventListener("scroll", updateScrollPosition);
})
onBeforeMount(() => {
  window.removeEventListener("scroll", updateScrollPosition);
})

function updateScrollPosition() {
  scrollPosition.x = window.scrollX;
  scrollPosition.y = window.scrollY;
  NavShow.value = scrollPosition.y <= 53;
}
</script>
<style lang="scss" scoped>
.header_box {
  z-index: 1;
  width: 100%;
  height: 53px;
  background-color: rgba(0, 0, 0, 0.1);

  .pc_menu {
    display: flex;
    justify-content: space-between;
    width: 100%;
    height: 100%;

    .header_log {
      padding-left: 2rem;
      display: flex;
      flex-grow: 7;
    }

    .header_menu {
      flex-grow: 3;

      .menus {
        display: flex;
        width: 100%;
        height: 100%;
        /* 默认有边距，都要清除 */
        margin: 0;
        padding: 0;

        .li {
          display: flex;
          width: 100%;
          height: 100%;
          align-items: center;
          background: none;
          text-decoration: none;

          .icon {
            flex: 1;
            width: 1em;
            height: 1em;
            vertical-align: -0.15em;
            fill: currentColor;
            overflow: hidden;
            font-size: 24px;
          }

          span {
            display: flex;
            align-items: center;
            height: 100%;
            flex: 2;
            color: #212121;
          }

          span:hover {
            color: #08d9d6;
          }
        }
      }
    }
  }
}

.header_box:hover {
  transition-duration: 0.5s;
  background-color: rgba(0, 0, 0, 0.2);
}

.header_fixed {
  position: fixed;
  top: 0;
  left: 0;
}

.fade-leave-active {
  transition: opacity 0.5s
}

.fade-leave-to {
  opacity: 0
}
</style>
