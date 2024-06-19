<template>
    <div class="game no_select">
        <div class="drawing animate__animated animate__fadeIn">
            <div class="left">
                <div class="title">
                    聊天室
                </div>
                <div class="chart_list">
                    <span>
                        在线列表
                    </span>
                    <div class="container">
                        <div class="item" v-for="user in globalStore.onlineUserList"
                            v-show="user.userId !== userStore.id">
                            <div class="item_avatar">
                                <img :src="user.userAvatar">
                            </div>
                            <div class="item_info">
                                <span>{{ user.userNickName }}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="right">
                <div class="title">
                    <div class="avatar">
                        <img :src="userStore.avatar">
                    </div>
                    <div class="info">
                        <span>{{ userStore.nickName }}</span>
                    </div>
                </div>
                <div class="body">
                    <div class="container">
                        <div class="msg_list" id="scrollableDiv">
                            <div class="item" v-for="message in globalStore.chatHistory"
                                :style="userStore.id == message.fromUserId ? 'justify-content: end;flex-direction:row-reverse;' : ''">
                                <div class="avatar">
                                    <img :src="message.fromUserAvatar">
                                </div>
                                <div class="info"
                                    :style="userStore.id == message.fromUserId ? 'padding-right: 10px;' : ''">
                                    <div class="user_name"
                                        :style="userStore.id == message.fromUserId ? 'justify-content: end;' : ''">
                                        <span style="text-align: end;">
                                            {{ message.fromUserNickName }}
                                        </span>
                                    </div>
                                    <div class="send_msg">
                                        <span>
                                            {{ message.message }}
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="chat_input">
                            <div class="emoji">
                                <V3Emoji @click-emoji="appendCommentChile" :fulldata="true" :recent="true" />
                            </div>
                            <div class="chat_box">
                                <input class="chat_txt" type="text" @keydown.enter="sendMsg()" v-model="inputMsg">
                            </div>
                            <div class="send" @click="sendMsg()">
                                <svg class="icon pointer" aria-hidden="true">
                                    <use xlink:href="#icon-fasong"></use>
                                </svg>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import V3Emoji from "vue3-emoji";
import useStore from "@/store"
import chatEnum from "@/utils/chatEnum"
import { onMounted, ref } from "vue"
import { useMessage } from 'naive-ui'
let { globalStore, userStore } = useStore()
//提示框
const message = useMessage()
//输入框消息
const inputMsg = ref("")
onMounted(() => {
})
//处理表情
function appendCommentChile(emajor: any) {
    inputMsg.value += emajor.emoji;
}
//发送消息
function sendMsg() {
    //消息数据
    const data = {
        fromUserId: userStore.id,
        fromUserAvatar: userStore.avatar,
        fromUserNickName: userStore.nickName,
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
</script>

<style lang="scss" scoped>
.game {
    display: flex;
    justify-content: center;
    padding-top: 64px;
    padding-bottom: 40px;

    .drawing {
        display: flex;
        width: 90%;
        max-height: 85vh;
        min-height: 85vh;
        border-radius: 20px;
        overflow: hidden;
        background-color: #282A39;
        padding: 20px;

        .left {
            display: flex;
            flex-direction: column;
            box-sizing: border-box;
            width: 25%;

            .title {
                display: flex;
                align-items: center;
                justify-content: center;
                width: 100%;
                height: 10%;
                font-size: 24px;
                color: #fff;
            }

            .chart_list {
                box-sizing: border-box;
                width: 100%;
                height: 90%;
                margin-top: 20px;
                overflow-y: auto;

                span {
                    padding-left: 10px;
                    color: #757989;
                }

                .container {
                    box-sizing: border-box;
                    width: 100%;
                    padding: 5px 10px 20px 10px;

                    .item {
                        display: flex;
                        flex-direction: row;
                        padding: 10px 0;
                        width: 100%;
                        margin-top: 5px;
                        background-color: #323647;
                        border-radius: 10px;

                        .item_avatar {
                            padding-left: 10px;
                            overflow: hidden;

                            img {
                                min-width: 50px;
                                max-width: 50px;
                                max-height: 50px;
                                min-height: 50px;
                                object-fit: cover;
                                border-radius: 50%;
                            }
                        }

                        .item_info {
                            padding-left: 10px;
                            display: flex;
                            align-items: center;
                            flex-grow: 1;

                            span {
                                color: #fff;
                            }
                        }
                    }

                    .item:hover {
                        background-color: #128BFB;
                    }
                }
            }
        }

        .right {
            display: flex;
            flex-grow: 1;
            flex-direction: column;
            width: 70%;
            height: 100%;

            .title {
                display: flex;
                flex-direction: row;
                align-items: center;
                width: 100%;
                height: 10%;
                color: #fff;

                .avatar {
                    padding-left: 10px;
                    overflow: hidden;

                    img {
                        min-width: 50px;
                        max-width: 50px;
                        max-height: 50px;
                        min-height: 50px;
                        object-fit: cover;
                        border-radius: 50%;
                    }
                }

                .info {
                    padding-left: 10px;
                }
            }

            .body {
                box-sizing: border-box;
                width: 100%;
                height: 90%;
                margin-left: 10px;
                margin-top: 20px;
                padding: 0px 10px;

                .container {
                    width: 100%;
                    height: 100%;
                    border-radius: 20px;
                    background-color: #323647;
                    position: relative;

                    .msg_list {
                        box-sizing: border-box;
                        width: 100%;
                        height: 90%;
                        padding: 10px;
                        overflow-y: auto;

                        .item {
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

                                .user_name {
                                    display: flex;
                                    font-size: 12px;
                                    padding-bottom: 5px;
                                    color: #808080;

                                    span {
                                        text-align: left;
                                    }
                                }

                                .send_msg {
                                    font-size: 14px;
                                    padding: 10px;
                                    border-radius: 5px;
                                    color: #fff;
                                    background-color: #262626;
                                }
                            }
                        }
                    }

                    .chat_input {
                        width: 80%;
                        height: 40px;
                        position: absolute;
                        left: 50%;
                        bottom: 0%;
                        transform: translate(-50%, -50%);
                        display: flex;
                        justify-content: center;
                        align-items: center;

                        .emoji {
                            box-sizing: border-box;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            padding: 5px;
                            height: 100%;
                            border-radius: 7px;
                            margin-right: 10px;
                            background-color: #434765;
                            border: #e3fdfd 1px solid;
                        }

                        .emoji:hover {
                            background-color: #128BFB;
                        }

                        .chat_box {
                            box-sizing: border-box;
                            width: 80%;
                            height: 100%;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            flex-wrap: nowrap;
                            border: #e3fdfd 1px solid;
                            border-radius: 7px;

                            .chat_txt {
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
                            box-sizing: border-box;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            padding: 5px;
                            width: 40px;
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
    }
}
</style>
