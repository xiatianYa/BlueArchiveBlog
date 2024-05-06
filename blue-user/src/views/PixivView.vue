<template>
    <div class="pixiv" :class="gloBalStore.switch ? 'bg_white' : 'bg_black'">
        <div class="pixiv_left">
            <Artplayer @get-instance="getInstance" :pixivId="pixivId" :chaptersIndex="chaptersIndex" :option="option"
                :style="style" style="overflow: hidden" />
            <div class="pixiv_detail" v-if="pixiv">
                <div class="found_left">
                    <div class="left_title">{{ pixiv.pixivName }}</div>
                    <div class="left_info">
                        <span>{{ pixiv.pixivPlay }}播放</span> · <span>876.6万弹幕</span> · <span>872.3万点赞</span>
                    </div>
                    <div class="left_type">
                        <span>{{ pixiv.typeName }}</span> · <span>{{ pixiv.createTime.substring(0, 10) }}</span>
                    </div>
                    <div class="left_shengyou">
                        <span>声优 : {{ pixiv.pixivVoiceActor }}</span>
                    </div>
                    <div class="left_score">
                        <span>{{ pixiv.pixivScore }}</span><span>分</span>
                    </div>
                    <div class="left_synopsis">
                        <span>简介 : {{ pixiv.pixivSynopsis }}</span>
                    </div>
                </div>
                <div class="found_right">
                    <div class="category_img">
                        <img v-lazy="pixiv.pixivAvater">
                    </div>
                </div>
            </div>
            <CommentDetail :comment-type="1" :common-id="pixivId" />
        </div>
        <div class="pixiv_right">
            <div class="pixiv_msg" :class="gloBalStore.switch ? 'color_white' : 'color_black'">
                <div class="title">
                    <span>
                        弹幕列表
                    </span>
                    <svg class="icon pointer" aria-hidden="true" v-show="!msgIsShow" @click="changMsgShow">
                        <use xlink:href="#icon-up01"></use>
                    </svg>
                    <svg class="icon pointer" aria-hidden="true" v-show="msgIsShow" @click="changMsgShow">
                        <use xlink:href="#icon-down01"></use>
                    </svg>
                </div>
                <div class="container" :style="msgIsShow ? '' : 'max-height:0;overflow:hidden;margin:0;'">
                    <div class="title">
                        <span>
                            时间
                        </span>
                        <span>
                            弹幕内容
                        </span>
                        <span>
                            发送时间
                        </span>
                    </div>
                    <div class="msg_list">
                        <div class="item" v-for="msg in leaveMessageList">
                            <span class="time">
                                {{ msg.time }}
                            </span>
                            <span class="content">
                                {{ msg.text }}
                            </span>
                            <span class="time">
                                {{ msg.createTime }}
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <div style="border-radius: 10px;padding: 10px;" :class="gloBalStore.switch ? 'color_white' : 'color_black'">
                <div class="pixiv_title">
                    <span>正片 ({{ chaptersIndex + '/' + episodeList.length }})</span>
                    <svg class="icon pointer" aria-hidden="true" @click="changSort" v-show="sort">
                        <use xlink:href="#icon-paixu-jiangxu"></use>
                    </svg>
                    <svg class="icon pointer" aria-hidden="true" @click="changSort" v-show="!sort">
                        <use xlink:href="#icon-paixu-shengxu"></use>
                    </svg>
                </div>
                <div class="pixiv_episodes">
                    <div class="episode" :class="chaptersIndex === episode.pixivChapters ? 'selectEpisode' : ''"
                        v-for="episode in episodeList" @click="selectChapters(episode)">
                        <span>{{ episode.pixivChapters }}</span>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div>
    </div>
</template>

<script setup>
import { onMounted, ref } from "vue"
import { useRouter } from 'vue-router'
import { getTv } from '@/api/tv'
import { listEpisode } from '@/api/episode'
import { useGloBalStore } from '@/store/global'
import { listMessage } from '@/api/pixivMessage'
import Artplayer from "@/components/Artplayer.vue";
import CommentDetail from '@/components/CommentDetail.vue';

const gloBalStore = useGloBalStore()
const router = useRouter()
//番剧ID
const pixivId = ref()
//番剧信息
const pixiv = ref()
//番剧集列表
const episodeList = ref([])
//当前番剧集ID
const chaptersIndex = ref()
//当前集弹幕列表
const leaveMessageList = ref()
//播放组件实例对象
const artInstance = ref()
//播放组件样式
const style = ref({
    width: '100%',
    height: '400px',
})
//排序方案 true 降序 false 升序
const sort = ref(true)
//弹幕列表是否隐藏 false 隐藏 true显示
const msgIsShow = ref(false);
onMounted(() => {
    init();
})
//初始化
function init() {
    //获取视频播放示例对象
    getInstance()
    //获取从路由传递过来的参数
    if (router.currentRoute.value.query.pixivId) {
        pixivId.value = router.currentRoute.value.query.pixivId
    }
    //获取番剧信息
    getTv(pixivId.value).then(res => {
        pixiv.value = res.data
        //获取当前番剧中的番剧集列表
        const query = {
            pixivId: pixiv.value.id,
            pageNum: 1,
            pageSize: 999,
        }
        listEpisode(query).then(res => {
            episodeList.value = res.rows
            chaptersIndex.value = episodeList.value[0].pixivChapters
            //设置播放组件传递视频地址 封面
            artInstance.value.url = episodeList.value[0].pixivUrl
            artInstance.value.poster = pixiv.value.pixivAvater;
            getLeaveMessageList();
        })
    })
}
//选择第几集
function selectChapters(episode) {
    //重新设置下标
    chaptersIndex.value = episode.pixivChapters
    //向组件传递当前的集数
    artInstance.value.url = episode.pixivUrl
    //获取弹幕列表
    getLeaveMessageList();
    //播放
    artInstance.value.play()
}
//选择集排序方案 默认是降序
function changSort() {
    sort.value = !sort.value
    //切换成升序
    if (sort.value) {
        episodeList.value = episodeList.value.sort((x, y) => x.pixivChapters - y.pixivChapters);
    } else {
        //切换成降序
        episodeList.value = episodeList.value.sort((x, y) => y.pixivChapters - x.pixivChapters);
    }
}
//切换弹幕列表是否显示
function changMsgShow() {
    msgIsShow.value = !msgIsShow.value
}
//获取视频播放实例
function getInstance(art) {
    artInstance.value = art
}
//获取弹幕列表
function getLeaveMessageList() {
    const query = {
        pageNum: 0,
        pageSize: 9999,
        pixivId: pixivId.value,
        episodeId: chaptersIndex.value,
    }
    //获取弹幕列表
    listMessage(query).then(res => {
        leaveMessageList.value = res.rows;
    })
}
</script>

<style lang="scss" scoped>
.color_white {
    background-color: #e6e8ea;
    color: #000000;
}

.color_black {
    background-color: rgb(27, 27, 27, 0.9);
    color: #FFFFFF;
}

.pixiv {
    display: flex;
    justify-content: center;
    width: 100%;
    padding: 60px 0 40px 0;

    .pixiv_left {
        width: 50%;
        height: 100%;

        .pixiv_detail {
            width: 100%;
            display: flex;
            flex-direction: row;
            border-radius: 10px 10px 10px 10px;

            .found_left {
                width: 70%;
                margin: 30px 15px 30px 15px;

                .left_title {
                    width: 100%;
                    font-size: 18px;
                }

                .left_info {
                    font-weight: 100;
                    font-size: 14px;
                    padding-top: 15px;
                }

                .left_type {
                    font-weight: 100;
                    font-size: 14px;
                    padding-top: 15px;
                }

                .left_shengyou {
                    font-weight: 100;
                    font-size: 14px;
                    padding-top: 15px;
                }

                .left_score {
                    font-weight: 100;
                    font-size: 14px;
                    padding-top: 15px;
                }

                .left_synopsis {
                    font-weight: 100;
                    font-size: 14px;
                    padding-top: 15px;
                }
            }

            .found_right {
                width: 30%;
                margin: 30px 15px 30px 15px;

                .category_img {
                    width: 100%;
                    height: 100%;
                    border-radius: 10px;
                    overflow: hidden;

                    img {
                        width: 100%;
                        height: 100%;
                        object-fit: cover;
                        transition: all .3s ease-in 0s;
                    }

                    img:hover {
                        object-fit: cover;
                        transform: scale(1.1);
                        transition: all 0.3s ease-in;
                    }
                }
            }
        }
    }

    .pixiv_right {
        width: 30%;
        height: 100%;
        margin: 0 0 0 15px;
        min-height: 500px;

        .pixiv_msg {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            padding: 10px;
            margin-bottom: 5px;
            border-radius: 10px;

            .title {
                width: 100%;
                display: flex;
                justify-content: space-between;

                span {
                    font-weight: 100;
                    font-size: 14px;
                }

                .icon {
                    font-size: 20px;
                }
            }

            .container {
                width: 100%;
                margin-top: 10px;
                max-height: 400px;
                overflow: auto;
                transition: all 0.2s ease-in-out;

                .title {
                    margin: 0;

                    span {
                        flex: 1;
                        font-size: 12px;
                    }
                }

                .msg_list {
                    width: 100%;

                    .item {
                        display: flex;
                        justify-content: space-between;
                        margin-top: 5px;

                        span {
                            flex: 1;
                            font-size: 12px
                        }
                    }
                }
            }
        }

        .pixiv_title {
            display: flex;
            justify-content: space-between;
            align-items: center;

            .icon {
                font-size: 20px;
                padding-right: 20px;
            }

            span {
                font-weight: 100;
                font-size: 14px;
            }
        }

        .pixiv_episodes {
            display: flex;
            flex-wrap: wrap;
            justify-content: flex-start;
            max-height: 450px;
            overflow: auto;

            .episode {
                box-sizing: border-box;
                justify-content: center;
                align-items: center;
                display: flex;
                width: 18%;
                height: 65px;
                margin: 10px 1%;
                border-radius: 5px;
                background-color: #393e46;
            }

            .episode:hover {
                border: 1px #00A1D6 solid;
            }

            .selectEpisode {
                background-color: #00A1D6;
            }
        }

        /* 可选：滑块样式 */
        .pixiv_episodes::-webkit-scrollbar {
            width: 6px;
            height: 6px;
        }

        .pixiv_episodes::-webkit-scrollbar-track {
            background: #f1f1f1;
        }

        .pixiv_episodes::-webkit-scrollbar-thumb {
            background: #C8CBCF;
        }

        .pixiv_episodes::-webkit-scrollbar-thumb:hover {
            background: #555;
        }
    }
}
</style>
