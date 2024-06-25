<template>
    <div class="music">
        <div class="left animate__animated animate__fadeInUp">
            <div class="music-title">
                歌单列表
            </div>
            <div class="music-menu">
                <span class="pointer" @click="openMusic">添加歌曲</span>
            </div>
            <div class="music-list">
                <div class="item pointer" v-for="item in sortOptions">
                    <span @click="changSort(item.value)" style="flex: 1;">
                        <n-ellipsis :line-clamp="1" :style="item.value == musicSortIndex ? 'color: #00e0ff;' : ''">
                            {{ item.label }}
                        </n-ellipsis>
                    </span>
                </div>
            </div>
        </div>
        <div class="right animate__animated animate__fadeInRight">
            <MusicDetail :musicList="musicList" :startMusic="startMusic" />
            <audio id="music">
            </audio>
        </div>
        <!-- 添加修改框 -->
        <n-modal v-model:show="MusicShow" transform-origin="center">
            <n-card style="width: 600px" :title="title" :bordered="false" size="huge" role="dialog" aria-modal="true">
                <n-form ref="formRef" :model="music" :rules="rules" label-placement="left" label-width="auto"
                    require-mark-placement="right-hanging" size="medium" :style="{
                        maxWidth: '640px'
                    }">
                    <n-form-item label="音乐名称" path="musicName">
                        <n-input v-model:value="music.musicName" placeholder="请输入音乐名称" />
                    </n-form-item>
                    <n-form-item label="音乐分类" path="sortId">
                        <n-select v-model:value="music.sortId" :options="sortOptions" placeholder="请选择文章分类" clearable />
                    </n-form-item>
                    <n-form-item label="音乐封面" path="imgUrl">
                        <ImgUpload v-model="music.imgUrl"></ImgUpload>
                    </n-form-item>
                    <n-form-item label="音乐文件" path="musicUrl">
                        <FileUpload v-model="music.musicUrl"></FileUpload>
                    </n-form-item>
                </n-form>
                <template #footer>
                    <n-space>
                        <n-button secondary round @click="MusicShow = false">
                            取消
                        </n-button>
                        <n-button type="info" secondary round @click="musicSubmit">
                            提交
                        </n-button>
                    </n-space>
                </template>
            </n-card>
        </n-modal>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { listSort } from '@/api/musicSort'
import { bySortList, addMusic } from '@/api/music'
import { useMessage, NModal, NCard, NButton, NSpace, NInput, NForm, NFormItem, NEllipsis, NSelect, type FormInst } from 'naive-ui'
import MusicDetail from "@/components/MusicDetail.vue";
import ImgUpload from '@/components/imgUpload/index.vue'
import FileUpload from '@/components/fileUpload/index.vue'
// 定义Music的类型  
interface MusicType {
    id: number | null;
    imgUrl: string;
    musicName: string;
    musicUrl: string;
    sortId: number | null;
}
const rules = ref({
    musicName: {
        required: true,
        trigger: ['blur', '音乐名称'],
        message: '请输入音乐名称'
    },
    imgUrl: {
        required: true,
        trigger: ['blur', '音乐封面'],
        message: '请上传音乐封面'
    },
    musicUrl: {
        required: true,
        trigger: ['blur', '音乐文件'],
        message: '请上传音乐文件'
    },
    sortId: {
        required: true,
        trigger: ['blur', '音乐分类'],
        message: '请选择音乐分类',
        validator(rule: any, value: number) {
            return !value ? false : true;
        }
    },
})

//标题
const title = ref("")
//表单
const formRef = ref<FormInst>()
//提示框
const message = useMessage()
//音乐分类列表
const sortOptions = ref()
//音乐选中下标
const musicSortIndex = ref()
//音乐列表
const musicList = ref()
//添加框是否显示
const MusicShow = ref(false)
//添加音乐对象
const music = ref<MusicType>({
    id: null,
    imgUrl: "",
    musicName: "",
    musicUrl: "",
    sortId: null
})
//存储上一个img图片
let BeforeImg: any = ref({})

onMounted(() => {
    init();
})
//初始化
function init() {
    listSort().then((res:any) => {
        sortOptions.value = res.rows.map((item: any) => {
            return {
                value: item.id,
                label: item.sortName
            }
        });
        //设置初始下标
        musicSortIndex.value = sortOptions.value[0].value;
        searchMusicList();
    })
}
//切换歌单
function changSort(sortId: any) {
    musicSortIndex.value = sortId;
    //清空正在播放的音乐
    //获取音乐标签
    let music: any = document.getElementById("music");
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
function startMusic(event: any, musicUrl: any) {
    //获取音乐标签
    let music: any = document.getElementById("music");
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
    img.className = "music-img rotateAll";
    //设置ImgNBefore
    BeforeImg = img
    //开始播放音乐
    music.play();
}
//打开添加框
function openMusic() {
    music.value = {
        id: null,
        imgUrl: "",
        musicName: "",
        musicUrl: "",
        sortId: null
    }
    MusicShow.value = true;
}
//提交操作
function musicSubmit() {
    formRef.value?.validate((errors) => {
        if (!errors) {
            addMusic(music.value).then((res) => {
                message.success("添加成功")
                MusicShow.value = false;
            }).catch(() => {
                message.error("添加失败")
            })
        } else {
            message.warning('请检查参数')
        }
    })
}

</script>

<style lang="scss" scoped>
.music {
    display: flex;
    width: 100%;
    margin-bottom: 40px;

    .left {
        border-radius: 10px;
        width: 15%;
        height: 100%;
        margin: 20px;
        border: 1px solid #c8d9eb;

        .music-title {
            display: flex;
            justify-content: center;
            font-size: 14px;
            margin: 10px;
        }

        .music-menu {
            display: flex;
            justify-content: center;
            font-size: 12px;
            padding: 10px;
            border-top: 1px solid #c8d9eb;

            span:hover {
                color: #00e0ff;
            }
        }

        .music-list {
            display: flex;
            flex-direction: column;

            .item {
                display: flex;
                justify-content: start;
                align-items: center;
                width: 100%;
                height: 30px;
                padding: 5px;
                font-size: 12px;
                border-top: 1px solid #c8d9eb;

                span {
                    display: flex;
                    align-items: center;
                    padding: 5px;
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
}
</style>