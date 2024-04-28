<template>
    <div class="game">
        <div class="drawing animate__animated animate__fadeIn">
            <div class="left">
                <div class="title">
                    聊天室
                </div>
                <div class="chart_list">
                    <span>
                        聊天列表
                    </span>
                    <div class="container">
                        <div class="item">
                            <div class="item_avatar">
                                <img v-lazy="'https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/zi.png'">
                            </div>
                            <div class="item_info">
                                <span>用户名</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="right">
                <div class="title">
                    <div class="avatar">
                        <img v-lazy="'https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/zi.png'">
                    </div>
                    <div class="info">
                        <span>用户名</span>
                    </div>
                </div>
                <div class="body">
                    <div class="container">
                        <div class="chat_input">
                            <div class="emoji">
                                <V3Emoji :disable-group="disableGroup" @click-emoji="appendCommentChile"
                                    :options-name="optionsName" :fulldata="true" :recent="true" />
                            </div>
                            <div class="chat_box">
                                <input class="chat_txt" type="text">
                            </div>
                            <div class="send">
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

<script setup>
import V3Emoji from "vue3-emoji";
import {onMounted} from "vue"

onMounted(() => {
})
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
                        margin-top: 20px;
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
                            border: #e3fdfd 2px solid;
                        }

                        .chat_box {
                            box-sizing: border-box;
                            width: 80%;
                            height: 100%;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            flex-wrap: nowrap;
                            border: #e3fdfd 2px solid;
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
                            border: #e3fdfd 2px solid;
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
