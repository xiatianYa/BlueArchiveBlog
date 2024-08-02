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
                <div class="item pointer" v-for="item in selectOption.sortOptions">
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
        <n-modal v-model:show="musicShow" transform-origin="center">
            <resuse-form ref="formRef" class="formClass" :formData="music" :formOption="formOption"
                :formItemOption="selectOption" :rules="rules" labelPosition="right" labelWidth="140">
                <template #ImgUpload>
                    <ImgUpload v-model="music.imgUrl"></ImgUpload>
                </template>
                <template #FileUpload>
                    <FileUpload v-model="music.musicUrl"></FileUpload>
                </template>
                <template #Footer>
                    <n-space>
                        <n-button secondary round @click="musicShow = false">
                            取消
                        </n-button>
                        <n-button type="info" secondary round @click="musicSubmit">
                            提交
                        </n-button>
                    </n-space>
                </template>
            </resuse-form>
        </n-modal>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref, reactive } from 'vue'
import { listSort } from '@/api/musicSort'
import { bySortList, addMusic } from '@/api/music'
import { useMessage, NModal, NButton, NSpace, NEllipsis, type FormInst } from 'naive-ui'
import resuseForm from '@/components/reuseForm/index.vue'
import MusicDetail from "@/view/music/component/MusicDetail.vue";
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
//下拉框配置项
const selectOption: any = reactive({
    sortOptions: [] //音乐分类列表
})
//表单配置项
const formOption = reactive([
    {
        type: "input", props: "musicName", label: "音乐名称", placeholder: "请输入音乐名称"
    },
    {
        type: "select", selectProps: "sortOptions", props: "sortId", label: "分类名称", placeholder: "请选择分类名称"
    },
    {
        type: "slot", slotName: "ImgUpload", props: "imgUrl", label: "音乐封面"
    },
    {
        type: "slot", slotName: "FileUpload", props: "musicUrl", label: "音乐文件"
    },
    {
        type: "slot", slotName: "Footer"
    }
]);
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
const formRef = ref<any | null>(null)
//提示框
const message = useMessage()
//音乐选中下标
const musicSortIndex = ref()
//音乐列表
const musicList = ref()
//添加框是否显示
const musicShow = ref(false)
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
    listSort().then((res: any) => {
        selectOption.sortOptions = res.rows.map((item: any) => {
            return {
                value: item.id,
                label: item.sortName
            }
        });
        //设置初始下标
        musicSortIndex.value = selectOption.sortOptions[0].value;
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
    musicShow.value = true;
}
//提交操作
function musicSubmit() {
    formRef.value?.ruleFormRef().validate((errors: any) => {
        if (!errors) {
            addMusic(music.value).then((res) => {
                message.success("添加成功")
                musicShow.value = false;
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
        flex: 1;
        width: 70%;
        min-height: 100vh;
        margin: 0 20px;
    }
}
</style>