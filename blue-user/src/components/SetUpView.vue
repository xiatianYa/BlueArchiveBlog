<template>
  <div class="setup">
    <svg class="icon pointer" aria-hidden="true">
      <use xlink:href="#icon-shezhi"></use>
    </svg>
    <transition name="fadenum">
      <div class="setView">
        <svg class="icon pointer" aria-hidden="true" @click="goTop">
          <use xlink:href="#icon-huojian"></use>
        </svg>
        <svg class="icon pointer" aria-hidden="true" @click="openChart()" v-show="userStore.token">
          <use xlink:href="#icon-tongzhi"></use>
        </svg>
        <svg class="icon pointer" aria-hidden="true" v-show="globalStore.switch"
          @click="changSwitch(!globalStore.switch)">
          <use xlink:href="#icon-deng"></use>
        </svg>
        <svg class="icon pointer" aria-hidden="true" v-show="!globalStore.switch"
          @click="changSwitch(!globalStore.switch)">
          <use xlink:href="#icon-dengpao"></use>
        </svg>
      </div>
    </transition>
    <!-- 聊天框 -->
    <n-drawer v-model:show="chartShow" :width="750" placement="left">
      <n-drawer-content title="聊天室" closable>
        <div class="chat">
          <div class="chat-left">
            <div class="online-title">
              在线列表
            </div>
            <div class="online-user" v-for="user in globalStore.onlineUserList" v-show="user.userId !== userStore.id">
              <div class="online-avatar">
                <img :src="user.userAvatar">
              </div>
              <div class="online-info">
                <span>{{ user.userNickName }}</span>
              </div>
            </div>
          </div>
          <div class="chat-right">
            <div class="online-user">
              <div class="online-avatar">
                <img :src="userStore.avatar">
              </div>
              <div class="online-info">
                <span>{{ userStore.nickName }}</span>
              </div>
            </div>
            <div class="chat-container">
              <div class="chat-list" id="scrollableDiv">
                <div class="msg" v-for="message in globalStore.chatHistory"
                  :style="userStore.id == message.fromUserId ? 'justify-content: end;flex-direction:row-reverse;' : ''">
                  <div class="avatar">
                    <img :src="message.fromUserAvatar">
                  </div>
                  <div class="info" :style="userStore.id == message.fromUserId ? 'padding-right: 10px;' : ''">
                    <div class="user-name" :style="userStore.id == message.fromUserId ? 'justify-content: end;' : ''">
                      <span style="text-align: end;">
                        {{ message.fromUserNickName }}
                      </span>
                    </div>
                    <div class="send-msg">
                      <span>
                        {{ message.data }}
                      </span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="chat-input">
                <div class="emoji">
                  <V3Emoji @click-emoji="appendCommentChile" :fulldata="true" :recent="true" />
                </div>
                <div class="chat-box">
                  <input class="chat-txt" type="text" @keydown.enter="sendMsgAll()" v-model="inputMsg">
                </div>
                <div class="send" @click="sendMsgAll()">
                  <svg class="icon pointer" aria-hidden="true">
                    <use xlink:href="#icon-fasong"></use>
                  </svg>
                </div>
              </div>
            </div>
          </div>
        </div>
      </n-drawer-content>
    </n-drawer>
  </div>
</template>
<script setup lang="ts">
import useStore from "@/store";
import chatEnum from "@/utils/chatEnum";
import V3Emoji from "vue3-emoji";
let { globalStore, userStore } = useStore()
import { ref, watch, nextTick, onMounted } from 'vue'
import { NDrawer, NDrawerContent, useMessage } from 'naive-ui'
//聊天室是否显示
const chartShow = ref(false)
//输入框信息
const inputMsg = ref()
//消息对象
const message = useMessage()

onMounted(() => {
  //第一次进入聊天框下拉到底部
  nextTick(() => {
    var scrollableDiv: any = document.getElementById('scrollableDiv');
    scrollableDiv.scrollTop = scrollableDiv.scrollHeight;
  })
})

//打开聊天室
function openChart() {
  chartShow.value = true
}

//处理表情
function appendCommentChile(emajor: any) {
  inputMsg.value += emajor.emoji;
}
//发送消息 
function sendMsgAll() {
  //消息数据
  const data = {
    fromUserId: userStore.id,
    data: inputMsg.value,
    type: chatEnum.ChatGroupType
  }
  //校验数据
  if (inputMsg.value.length <= 50 && inputMsg.value.length) {
    globalStore.sendMessage(JSON.stringify(data));
  } else {
    message.warning("消息长度不合规")
  }
  //清空输入框
  inputMsg.value = "";
}

//切换模式
function changSwitch(value: boolean) {
  globalStore.changSwitch(value)
}

//前往窗口顶部
function goTop() {
  window.scrollTo({ behavior: 'smooth', top: 0 });
}

//监听聊天数据变化 自动调整下拉框
watch(globalStore.chatHistory, (newValue) => {
  //滑动到底部
  nextTick(() => {
    var scrollableDiv: any = document.getElementById('scrollableDiv');
    scrollableDiv.scrollTop = scrollableDiv.scrollHeight;
  })
})
</script>
<style lang="scss" scoped>
.chat {
  width: 100%;
  height: 100%;
  display: flex;
  background-color: #282A39;

  .chat-left {
    width: 25%;
    height: 100%;
    padding: 10px;

    .online-title {
      width: 100%;
      height: 10%;
      display: flex;
      align-items: center;
      justify-content: center;
      color: #fff;
    }


    .online-user {
      width: 100%;
      height: 50px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      background-color: #323647;
      border-radius: 10px;
      margin-top: 5px;

      .online-avatar {
        width: 30px;
        height: 30px;
        padding-left: 5px;

        img {
          width: 30px;
          height: 30px;
          border-radius: 50%;
          object-fit: cover;
        }
      }

      .online-info {
        width: 100%;
        padding-left: 10px;
        color: #fff;
      }
    }
  }

  .chat-right {
    width: 75%;
    height: 100%;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    padding: 10px;

    .online-user {
      width: 100%;
      height: 10%;
      display: flex;
      align-items: center;

      .online-avatar {
        width: 30px;
        height: 30px;
        padding-left: 5px;

        img {
          width: 30px;
          height: 30px;
          border-radius: 50%;
          object-fit: cover;
        }
      }

      .online-info {
        width: 100%;
        padding-left: 10px;
        color: #fff;
      }
    }

    .chat-container {
      width: 100%;
      height: 90%;
      background-color: #323647;
      border-radius: 15px;

      .chat-list {
        width: 100%;
        height: 90%;
        padding: 10px;
        overflow-y: auto;

        .msg {
          margin-bottom: 15px;
          display: flex;
          align-items: center;
          width: 100%;

          .avatar {
            overflow: hidden;

            img {
              min-width: 40px;
              max-width: 40px;
              max-height: 40px;
              min-height: 40px;
              object-fit: cover;
              border-radius: 50%;
            }
          }

          .info {
            display: flex;
            flex-direction: column;
            height: 100%;
            padding-left: 10px;

            .user-name {
              display: flex;
              font-size: 12px;
              padding-bottom: 5px;
              color: #808080;

              span {
                text-align: left;
              }
            }

            .send-msg {
              font-size: 14px;
              padding: 10px;
              border-radius: 5px;
              color: #fff;
              background-color: #262626;
            }
          }
        }
      }

      .chat-input {
        width: 80%;
        height: 40px;
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 0 auto;


        .emoji {
          display: flex;
          justify-content: center;
          align-items: center;
          padding: 5px;
          width: 10%;
          height: 100%;
          border-radius: 7px;
          margin-right: 10px;
          background-color: #434765;
          border: #e3fdfd 1px solid;
        }

        .emoji:hover {
          background-color: #128BFB;
        }

        .chat-box {
          width: 80%;
          height: 100%;
          display: flex;
          justify-content: center;
          align-items: center;
          flex-wrap: nowrap;
          border: #e3fdfd 1px solid;
          border-radius: 7px;

          .chat-txt {
            width: 100%;
            margin-left: 10px;
            float: right;
          }

          input {
            font-size: 12px;
            color: #fff;
          }
        }

        .send {
          display: flex;
          justify-content: center;
          align-items: center;
          padding: 5px;
          width: 10%;
          height: 100%;
          border-radius: 7px;
          margin-left: 10px;
          background-color: #434765;
          border: #e3fdfd 1px solid;
        }

        .send:hover {
          background-color: #128BFB;
        }
      }
    }
  }
}

.setup {
  position: fixed;
  right: 30px;
  bottom: 40px;
  z-index: 99;

  .icon {
    width: 1em;
    height: 1em;
    vertical-align: -0.15em;
    fill: currentColor;
    overflow: hidden;
    font-size: 32px;
    animation: revolve 2s linear infinite;
  }

  .setView {
    position: fixed;
    display: flex;
    flex-direction: column;
    width: 2rem;
    right: 30px;
    bottom: 75px;
    z-index: 1;
    opacity: 0;
    transition: opacity 0.4s;
    -webkit-transition: opacity 0.4s;

    .icon {
      width: 1em;
      height: 1em;
      vertical-align: -0.15em;
      fill: currentColor;
      overflow: hidden;
      font-size: 32px;
      animation: none;
    }
  }
}

.setup:hover .setView {
  opacity: 1;
}
</style>