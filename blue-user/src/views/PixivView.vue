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
        <div class="pixiv_right" :class="gloBalStore.switch ? 'color_white' : 'color_black'">
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
    <div>
    </div>
</template>

<script setup>
import {onMounted, ref} from "vue"
import {useRouter} from 'vue-router'
import {getTv} from '@/api/tv'
import {listEpisode} from '@/api/episode'
import Artplayer from "@/components/Artplayer.vue";
import CommentDetail from '@/components/CommentDetail.vue';
import {useGloBalStore} from '@/store/global'

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
//播放组件实例对象
const artInstance = ref()
const style = ref({
    width: '100%',
    height: '400px',
})
//排序方案 true 降序 false 升序
const sort = ref(true)
onMounted(() => {
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
            pageNum:1,
            pageSize: 999,
        }
        listEpisode(query).then(res => {
            episodeList.value = res.rows
            chaptersIndex.value = episodeList.value[0].pixivChapters
            //设置播放组件传递视频地址 封面
            artInstance.value.url = episodeList.value[0].pixivUrl
            artInstance.value.poster = pixiv.value.pixivAvater;
        })
    })
})
//选择第几集
function selectChapters(episode) {
    //重新设置下标
    chaptersIndex.value = episode.pixivChapters
    //向组件传递当前的集数
    artInstance.value.url = episode.pixivUrl
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
//获取视频播放实例
function getInstance(art) {
    artInstance.value = art
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
        border-radius: 10px;

        .pixiv_title {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin: 10px;

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
            margin: 10px;
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
