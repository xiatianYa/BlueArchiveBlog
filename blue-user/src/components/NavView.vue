<template>
  <transition name="fade">
    <div v-show="NavShow" class="header_box header_fixed">
      <div class="pc_menu">
        <div class="header_log">
          <img v-lazy="'/static/images/logo.png'" />
        </div>
        <div class="header_menu">
          <ul class="menus">
            <router-link :class="gloBalStore.switch ? 'color_white' : 'color_black'" class="li" to="/home">
              <svg aria-hidden="true" class="icon pointer">
                <use xlink:href="#icon-shouye"></use>
              </svg>
              <span class="pointer">首页</span>
            </router-link>
            <router-link :class="gloBalStore.switch ? 'color_white' : 'color_black'" class="li" to="/found">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-faxian"></use>
              </svg>
              <span class="pointer">发现</span>
            </router-link>
            <router-link :class="gloBalStore.switch ? 'color_white' : 'color_black'" class="li" to="/sort">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-fenlei"></use>
              </svg>
              <span class="pointer">分类</span>
            </router-link>
            <router-link :class="gloBalStore.switch ? 'color_white' : 'color_black'" class="li" to="/photo">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-xiangce"></use>
              </svg>
              <span class="pointer">相册</span>
            </router-link>
            <router-link :class="gloBalStore.switch ? 'color_white' : 'color_black'" class="li" to="/friend">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-jiqiren"></use>
              </svg>
              <span class="pointer">友链</span>
            </router-link>
            <router-link :class="gloBalStore.switch ? 'color_white' : 'color_black'" class="li" to="/leave">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-xiaoxi"></use>
              </svg>
              <span class="pointer">留言</span>
            </router-link>
            <router-link :class="gloBalStore.switch ? 'color_white' : 'color_black'" class="li" to="/song">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-jita"></use>
              </svg>
              <span class="pointer">听歌</span>
            </router-link>
            <router-link v-show="!UserStore.token" :class="gloBalStore.switch ? 'color_white' : 'color_black'"
              class="li" to="/user">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-wode"></use>
              </svg>
              <span class="pointer">登录</span>
            </router-link>
            <div v-show="UserStore.token" class="nav-item">
              <a :class="gloBalStore.switch ? 'color_white' : 'color_black'" class="alink" href="#">
                <img :src="UserStore.avatar">
                <span>{{ UserStore.nickName }}</span>
              </a>
              <div class="nav-drop-down-wrapper">
                <div :class="gloBalStore.switch ? 'color_white' : 'color_black'" class="nav-drop-down">
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
<script setup>
import {onBeforeMount, onMounted, reactive, ref} from 'vue'
import {useGloBalStore} from '@/store/global'
import {useUserStore} from '@/store/user'
import promptMsg from "@/components/PromptBoxView"
import {useRouter} from "vue-router";
//路由
const router = useRouter()
const UserStore = useUserStore()
const gloBalStore = useGloBalStore()
const scrollPosition = reactive({ x: 0, y: 0 })
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
  if (scrollPosition.y >= 53) {
    NavShow.value = false
  } else {
    NavShow.value = true
  }
}
//用户退出登录
function logOut() {
  UserStore.LogOut()
  promptMsg({ type: "success", msg: "退出成功" })
}
//跳转路由
function go(path) {
  router.push({ path: path })
}
</script>
<style lang="scss" scoped>
.color_white {
  color: #2b2b2b;
}

.color_black {
  color: #FFFFFF;
}

.header_box {
  z-index: 1;
  width: 100%;
  height: 53px;
  transition-duration: 1s;
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
          width: 100%;
          height: 100%;
          align-items: center;
          background: none;
          text-decoration: none;
          position: relative;


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
          }

          span:hover {
            color: #00e0ff;
          }
        }
      }
    }
  }
}

.header_box:hover {
  transition-duration: 1s;
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
