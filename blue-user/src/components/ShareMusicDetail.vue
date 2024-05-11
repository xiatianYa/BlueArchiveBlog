<template>
    <div class="music">
        <div class="left animate__animated animate__fadeInUp">
            <div class="music_title">
                歌单列表
            </div>
            <div class="music_menu">
                <span class="pointer" @click="openMusic">添加歌曲</span>
            </div>
            <div class="music_list">
                <div class="item pointer" v-for="musicSort in musicSortList" @click="changSort(musicSort.id)">
                    <span :class="musicSort.id === musicSortIndex ? 'select' : ''">{{ musicSort.sortName }}</span>
                </div>
            </div>
        </div>
        <div class="right">
            <MusicDetail :musicList="musicList" :startMusic="startMusic" />
            <audio id="music">
            </audio>
        </div>
        <!-- 添加修改框 -->
        <div class="music_dialog" v-if="musicShow">
            <div class="dialog">
                <div class="music_title">
                    <span>添加相册</span>
                    <svg class="icon pointer" aria-hidden="true" @click="closeMusic">
                        <use xlink:href="#icon-guanbi"></use>
                    </svg>
                </div>
                <div class="music_name">
                    <span>音乐名称</span>
                    <input type="text" v-model="music.musicName" placeholder="请输入音乐名称">
                </div>
                <div class="music_avatar">
                    <span>音乐图片</span>
                    <input type="file" @change="handleImageUpload" accept="image/*">
                </div>
                <div class="music_file">
                    <span>上传音乐</span>
                    <input type="file" @change="handleFileUpload" accept="audio/mp3">
                </div>
                <div class="button_box">
                    <button @click="addMusicSubmit">添加</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { listSort } from '@/api/musicSort'
import { bySortList, addMusic } from '@/api/music'
import { uploadImages } from "@/api/file";
import MusicDetail from "@/components/MusicDetail.vue";
import promptMsg from "@/components/PromptBoxView"
//音乐分类列表
const musicSortList = ref()
//音乐选中下标
const musicSortIndex = ref()
//音乐列表
const musicList = ref()
//添加框是否显示
const musicShow = ref(false)
//添加音乐对象
const music = ref({})
//存储上一个img图片
let BeforeImg = ref({})
onMounted(() => {
    init();
})
//初始化
function init() {
    listSort().then(res => {
        musicSortList.value = res.rows;
        //设置初始下标
        musicSortIndex.value = musicSortList.value[0].id;
        searchMusicList();
    })
}
//切换歌单
function changSort(sortId) {
    musicSortIndex.value = sortId;
    //清空正在播放的音乐
    //获取音乐标签
    let music = document.getElementById("music");
    //清除img图片的旋转样式
    BeforeImg.className = ""
    //清除BeforeImg
    BeforeImg = {};
    //停止音乐
    music.pause();
    searchMusicList();
}
//搜索音乐列表
function searchMusicList() {
    //获取匹配音乐列表
    const query = {
        sortId: musicSortIndex.value
    }
    bySortList(query).then(res => {
        musicList.value = res;
    })
}
//播放音乐
function startMusic(event, musicUrl) {
    //获取音乐标签
    let music = document.getElementById("music");
    //清除img图片的旋转样式
    BeforeImg.className = ""
    //获取点击的img
    let img = event.target;
    //判断是不是点击正在播放的音乐
    if (img === BeforeImg) {
        //清除BeforeImg
        BeforeImg = {};
        //停止音乐
        music.pause();
        //返回
        return;
    }
    //停止音乐
    music.pause();
    //设置新的音乐地址
    music.src = musicUrl
    //设置img图片旋转
    img.className = "music_img rotateAll";
    //设置ImgNBefore
    BeforeImg = img
    //开始播放音乐
    music.play();
}
//打开添加框
function openMusic() {
    musicShow.value = true;
}
//关闭添加框
function closeMusic() {
    musicShow.value = false;
    music.value = {};
}
//添加音乐
function addMusicSubmit() {
    music.value.sortId = musicSortIndex.value;
    addMusic(music.value).then(() => {
        music.value = {};
        musicShow.value = false;
        //重新初始化
        init();
        promptMsg({ type: "success", msg: "添加成功!" })
    }).catch(() => {
        promptMsg({ type: "error", msg: "添加失败" })
    })
}
//上传图片
function handleImageUpload(event) {
    const formData = new FormData();
    formData.append("file", event.target.files[0])
    uploadImages(formData).then(res => {
        music.value.imgUrl = res.data.url;
        promptMsg({ type: "success", msg: "上传图片成功!" })
    }).catch(error => {
        promptMsg({ type: "error", msg: "上传图片失败!" + error })
    })
}
//上传文件
function handleFileUpload(event) {
    const formData = new FormData();
    formData.append("file", event.target.files[0])
    uploadImages(formData).then(res => {
        music.value.musicUrl = res.data.url;
        promptMsg({ type: "success", msg: "上传文件成功!" })
    }).catch(error => {
        promptMsg({ type: "error", msg: "上传文件失败!" + error })
    })
}
</script>

<style lang="scss" scoped>
.music {
    display: flex;
    width: 100%;
    margin-bottom: 40px;

    .left {
        box-sizing: border-box;
        border-radius: 10px;
        width: 15%;
        height: 100%;
        margin: 20px;
        border: 1px solid #c8d9eb;

        .music_title {
            display: flex;
            justify-content: center;
            font-size: 14px;
            margin: 10px;
        }

        .music_menu {
            display: flex;
            justify-content: center;
            font-size: 12px;
            padding: 10px;
            border-top: 1px solid #c8d9eb;

            span:hover {
                color: #00e0ff;
            }
        }

        .music_list {
            display: flex;
            flex-direction: column;

            .item {
                box-sizing: border-box;
                width: 100%;
                height: 30px;
                padding: 10px;
                font-size: 12px;
                border-top: 1px solid #c8d9eb;

                .select {
                    color: #00e0ff;
                }

                span {
                    /* 设置 span 为块级元素或内联块级元素，以便设置宽度 */
                    display: inline-block;
                    /* 设置最大宽度 */
                    max-width: 100%;
                    /* 或具体宽度值，如 200px */
                    /* 设置文本溢出时显示省略号 */
                    overflow: hidden;
                    text-overflow: ellipsis;
                    /* 防止文本换行 */
                    white-space: nowrap;
                }

                span:hover {
                    color: #00e0ff;
                }
            }
        }
    }

    .right {
        width: 70%;
        min-height: 100vh;
        margin: 0 20px;
    }

    .music_dialog {
        position: fixed;
        display: flex;
        justify-content: center;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        padding-top: 10%;
        background-color: rgba(rgb(0, 0, 0), 0.3);
        z-index: 999;

        .dialog {
            position: fixed;
            width: 420px;
            height: 350px;
            border-radius: 10px;
            background-color: #FFFFFF;

            .music_title {
                box-sizing: border-box;
                position: relative;
                display: flex;
                justify-content: left;
                width: 100%;
                margin-top: 10px;
                padding-left: 10px;

                .icon {
                    position: absolute;
                    top: 0;
                    right: 20px;
                    /* 初始状态 */
                    transition: transform 0.5s ease-in-out;
                    /* 定义过渡效果，持续时间为0.5秒，使用ease-in-out缓动函数 */
                    transform: rotate(0deg);
                    /* 初始旋转角度为0度 */
                }

                .icon:hover {
                    /* 鼠标移入状态 */
                    transform: rotate(240deg);
                    /* 旋转角度为360度，即一圈 */
                }
            }

            .music_name {
                padding-left: 10px;
                margin-top: 20px;
                display: flex;
                justify-content: left;

                span {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    font-size: 12px;
                }

                input {
                    font-size: 12px;
                    margin-left: 8px;
                    padding: 3px 5px;
                    border-radius: 3px;
                    border: 1px solid #c8d9eb;
                    transition: all 0.5s ease;
                    /* 添加过渡效果，使边框颜色变化更平滑 */
                }

                input:hover {
                    border-color: #a7b4c3;
                }

                input:focus {
                    border-color: #a1eafb;
                    /* 鼠标悬停或获得焦点时的边框颜色 */
                }
            }

            .music_avatar {
                padding-left: 10px;
                margin-top: 20px;
                display: flex;
                justify-content: left;

                span {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    font-size: 12px;
                }

                input {
                    margin-left: 8px;
                }
            }

            .music_file {
                padding-left: 10px;
                margin-top: 20px;
                display: flex;
                justify-content: left;

                span {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    font-size: 12px;
                }

                input {
                    margin-left: 8px;
                }
            }

            .button_box {
                margin: 10px 10px;

                button {
                    width: 50px;
                    padding: 3px;
                    border-radius: 5px;
                    border: none;
                    font-size: 14px;
                    cursor: pointer;
                    background-color: #74f9ff;
                }

                button:hover {
                    background-color: #00e0ff;
                }
            }
        }
    }
}
</style>