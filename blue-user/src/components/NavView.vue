<template>
  <transition name="fade">
    <div v-show="NavShow" class="header-box header-fixed">
      <div class="pc-menu">
        <div class="header-log" @click="go('/home')">
          <img src="https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/logo.png" />
        </div>
        <div class="header-menu">
          <ul class="menus">
            <router-link :class="globalStore.switch ? 'color-white' : 'color-black'" class="li" to="/home">
              <svg aria-hidden="true" class="icon pointer">
                <use xlink:href="#icon-shouye"></use>
              </svg>
              <span class="pointer" :class="routerPath == '/home' ? 'select' : ''">首页</span>
            </router-link>
            <router-link :class="globalStore.switch ? 'color-white' : 'color-black'" class="li" to="/found">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-faxian"></use>
              </svg>
              <span class="pointer" :class="routerPath == '/found' ? 'select' : ''">发现</span>
            </router-link>
            <router-link :class="globalStore.switch ? 'color-white' : 'color-black'" class="li" to="/sort">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-fenlei"></use>
              </svg>
              <span class="pointer" :class="routerPath == '/sort' ? 'select' : ''">分类</span>
            </router-link>
            <router-link :class="globalStore.switch ? 'color-white' : 'color-black'" class="li" to="/photo">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-xiangce"></use>
              </svg>
              <span class="pointer" :class="routerPath == '/photo' ? 'select' : ''">相册</span>
            </router-link>
            <router-link :class="globalStore.switch ? 'color-white' : 'color-black'" class="li" to="/friend">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-jiqiren"></use>
              </svg>
              <span class="pointer" :class="routerPath == '/friend' ? 'select' : ''">友链</span>
            </router-link>
            <router-link v-show="userStore.token" :class="globalStore.switch ? 'color-white' : 'color-black'" class="li"
              to="/chat">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-xiaoxi"></use>
              </svg>
              <span class="pointer" :class="routerPath == '/chat' ? 'select' : ''">聊天室</span>
            </router-link>
            <router-link :class="globalStore.switch ? 'color-white' : 'color-black'" class="li" to="/leave">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-tongzhi"></use>
              </svg>
              <span class="pointer" :class="routerPath == '/leave' ? 'select' : ''">留言</span>
            </router-link>
            <router-link :class="globalStore.switch ? 'color-white' : 'color-black'" class="li" to="/music">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-erji"></use>
              </svg>
              <span class="pointer" :class="routerPath == '/music' ? 'select' : ''">听歌</span>
            </router-link>
            <router-link v-show="!userStore.token" :class="globalStore.switch ? 'color-white' : 'color-black'"
              class="li" to="/user">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-wode"></use>
              </svg>
              <span class="pointer" :class="routerPath == '/user' ? 'select' : ''">登录</span>
            </router-link>
            <div v-show="userStore.token" class="nav-item">
              <a :class="globalStore.switch ? 'color-white' : 'color-black'" class="alink" href="#">
                <img :src="userStore.avatar">
                <span>{{ userStore.nickName }}</span>
              </a>
              <div class="nav-drop-down-wrapper">
                <div :class="globalStore.switch ? 'color-white' : 'color-black'" class="nav-drop-down">
                  <div class="down-item" @click="go('/menu')">
                    <div class="down-item-wrapper">
                      <span>进入菜单</span>
                    </div>
                  </div>
                  <div class="down-item-end">
                    <div class="down-item-wrapper" @click="logOut()">
                      <span>退出登录</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </ul>
        </div>
      </div>
    </div>
  </transition>
</template>
<script setup lang="ts">
import { onBeforeMount, onMounted, reactive, ref, watch } from 'vue'
import { useMessage } from 'naive-ui'
import { useRouter } from "vue-router";
import useStore from "@/store"
let { globalStore, userStore } = useStore()
//提示框
const message = useMessage()
//路由
const router = useRouter()
const scrollPosition = reactive({ x: 0, y: 0 })
const NavShow = ref(true)
const routerPath = ref("")
onMounted(() => {
  window.addEventListener("scroll", updateScrollPosition);
})
onBeforeMount(() => {
  window.removeEventListener("scroll", updateScrollPosition);
})

function updateScrollPosition() {
  scrollPosition.x = window.scrollX;
  scrollPosition.y = window.scrollY;
  if (scrollPosition.y >= 10) {
    NavShow.value = false
  } else {
    NavShow.value = true
  }
}
//用户退出登录
function logOut() {
  //清除本地缓存
  userStore.logOut()
  //清除Socket连接
  globalStore.closeSocket()
  message.success("退出成功")
}
//跳转路由
function go(newPath: string) {
  let oldPath = router.currentRoute.value;
  if (oldPath.path === newPath) {
    message.info("别点了,你已经在当前页面了")
  }
  router.push({ path: newPath })
}
//监听路由
watch(
  () => router.currentRoute.value,
  (newPath) => {
    routerPath.value = newPath.path
  },
  { immediate: true }
)
</script>
<style lang="scss" scoped>
.color-white {
  color: rgb(51, 54, 57) !important;
}

.color-black {
  color: rgba(255, 255, 255, 0.9) !important;
}

.header-box {
  z-index: 1;
  width: 100%;
  height: 53px;
  transition-duration: 1s;
  background-color: rgba(0, 0, 0, 0.1);

  .pc-menu {
    display: flex;
    justify-content: space-between;
    width: 100%;
    height: 100%;

    .header-log {
      padding-left: 2rem;
      display: flex;
      flex-grow: 7;
    }

    .header-menu {
      flex-grow: 3;

      .menus {
        display: flex;
        width: 100%;
        height: 100%;
        /* 默认有边距，都要清除 */
        margin: 0;
        padding: 0;

        .nav-item {
          min-width: 100px;
          display: flex;
          cursor: pointer;
          font-weight: 800;
          position: relative;
          display: inline-block;

          &:hover {
            animation: jump 0.3s ease-in-out;

            .nav-drop-down-wrapper {
              display: block;
              opacity: 0;
              animation: show 0.4s ease-in-out;
              animation-delay: 0.1s;
              animation-fill-mode: forwards;
            }
          }

          .alink {
            widows: 100%;
            height: 100%;
            padding-right: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            text-decoration: none;
            display: flex;
            flex-wrap: nowrap;

            img {
              width: 35px;
              height: 35px;
              padding-right: 5px;
              border-radius: 50%;
              object-fit: cover;
            }
          }

          .nav-drop-down-wrapper {
            padding-top: 10px;
            width: 100%;
            position: absolute;
            display: none;
            right: 5px;


            .nav-drop-down {
              background-color: #fff;
              border-radius: 10px;
              box-sizing: border-box;
              display: flex;
              flex-direction: column;
              justify-content: center;
              align-items: center;
              font-size: 14px;
              font-weight: 100;
              box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);

              .down-item {
                margin: 5px 5px 0px 5px;
                box-sizing: border-box;
                width: 90%;
                border-radius: 10px;
                color: #000000;

                &:hover {
                  background-color: rgb(255, 229, 241);
                  color: #727272;
                }

                .down-item-wrapper {
                  padding: 5px;

                  img {
                    width: 20px;
                    height: 20px;
                    vertical-align: middle;
                  }

                  span {
                    vertical-align: middle;
                  }
                }
              }

              .down-item-end {
                margin: 5px 5px 5px 5px;
                box-sizing: border-box;
                width: 90%;
                border-radius: 10px;
                color: #000000;

                &:hover {
                  background-color: rgb(255, 229, 241);
                  color: #727272;
                }

                .down-item-wrapper {
                  padding: 5px;

                  img {
                    width: 20px;
                    height: 20px;
                    vertical-align: middle;
                  }

                  span {
                    vertical-align: middle;
                  }
                }
              }
            }
          }
        }

        .li {
          display: flex;
          align-items: center;
          justify-content: center;
          text-decoration: none;
          position: relative;
          width: 100%;
          height: 100%;

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
            flex-grow: 1;
            flex: 2;
          }

          .select {
            color: #00e0ff;
          }

          span:hover {
            color: #00e0ff;
          }
        }
      }
    }
  }
}

.header-box:hover {
  transition-duration: 1s;
  background-color: rgba(0, 0, 0, 0.2);
}

.header-fixed {
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
