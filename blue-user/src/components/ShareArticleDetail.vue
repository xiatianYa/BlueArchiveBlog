<template>
    <div class="article">
        <div class="left animate__animated animate__fadeInUp">
            <div class="article_title">
                我的文章
            </div>
            <div class="article_menu">
                <span class="pointer" @click="openArticleAdd">添加文章</span>
                <span class="pointer" @click="openArticleDelete">删除文章</span>
            </div>
            <div class="article_list">
                <div class="item pointer" v-for="article in ArticleList">
                    <input type="checkbox" v-model="deleteArticleList" name="article" :value="article.id">
                    <span @click="changeArticle(article)">{{ article.articleName }}</span>
                    <span>{{
                        article.status === 0 ? '审核中' : article.status === 1 ? '审核通过' : '审核为通过' }}</span>
                    <span class="pointer" @click="openArticleUpdate(article.id)">修改文章</span>
                </div>
            </div>
        </div>
        <div class="right">
            <div class="animate__animated animate__fadeInRight">
                <v-md-editor v-model="ArticleIndex.content" :include-level="[2, 3, 4]" mode="editable" height="100vh"
                    style="background: #ECEBEC;" @save="saveArticle" :disabled-menus="[]"
                    left-toolbar="undo  redo h bold italic strikethrough quote ul ol table hr link image code save todo-list emoji tip"
                    right-toolbar="preview toc sync-scroll" @upload-image="handleUploadImage"
                    @copy-code-success="handleCopyCodeSuccess"></v-md-editor>
            </div>
        </div>
        <n-modal v-model:show="ArticleShow" transform-origin="center">
            <n-card style="width: 600px" title="添加文章" :bordered="false" size="huge" role="dialog" aria-modal="true">
                <n-form ref="formRef" :model="Article" :rules="rules" label-placement="left" label-width="auto"
                    require-mark-placement="right-hanging" size="medium" :style="{
                        maxWidth: '640px'
                    }">
                    <n-form-item label="文章名称" path="articleName">
                        <n-input v-model:value="Article.articleName" placeholder="请输入文章名称" />
                    </n-form-item>
                    <n-form-item label="文章描述" path="articleDescribe">
                        <n-input v-model:value="Article.articleDescribe" placeholder="请输入文章描述" />
                    </n-form-item>
                    <n-form-item label="文章分类" path="sortId">
                        <n-select v-model:value="Article.sortId" :options="sortOptions" placeholder="请输入文章分类"
                            clearable />
                    </n-form-item>
                    <n-form-item label="文章标签" path="tagList">
                        <n-tree-select multiple cascade checkable check-strategy="parent" :options="tagOptions"
                            placeholder="请输入文章标签" :default-value="Article.tagList" @update:value="handleUpdateValue" />
                    </n-form-item>
                    <n-form-item label="文章封面" path="cover">
                        <ImgUpload @onSuccess="uploadImageOnSuccess"></ImgUpload>
                    </n-form-item>
                    <n-form-item label="文章视频" path="videoUrl">
                        <FileUpload :fileType="['video/mp4']" @onSuccess="uploadFileOnSuccess"></FileUpload>
                    </n-form-item>
                </n-form>
                <template #footer>
                    <n-space>
                        <n-button secondary round @click="ArticleShow = false">
                            取消
                        </n-button>
                        <n-button type="info" secondary round @click="addArticleSubmit">
                            提交
                        </n-button>
                    </n-space>
                </template>
            </n-card>
        </n-modal>
        <!-- 删除框 -->
        <n-modal v-model:show="ArticleDeleteShow" transform-origin="center">
            <n-card style="width: 600px" title="删除文章" :bordered="false" size="huge" role="dialog" aria-modal="true">
                <template #footer>
                    <n-space style="padding-bottom:20px">
                        删除数据ID为:{{ deleteArticleList }}的数据
                    </n-space>
                    <n-space>
                        <n-button secondary round @click="ArticleDeleteShow = false">
                            取消
                        </n-button>
                        <n-button type="info" secondary round @click="deleteArticleSubmit">
                            提交
                        </n-button>
                    </n-space>
                </template>
            </n-card>
        </n-modal>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watch, nextTick } from 'vue'
import { addArticle, delArticle, getArticle, listArticleByUser, updateArticle } from '@/api/article'
import { listSort } from '@/api/sort/sort'
import { uploadImages } from "@/api/file";
import { useMessage, NModal, NCard, NButton, NSpace, NInput, NForm, NFormItem, NSelect, NTreeSelect } from 'naive-ui'
import ImgUpload from '@/components/ImgUpload/index.vue'
import FileUpload from '@/components/FileUpload/index.vue'
function handleUpdateValue(value: Array<number>, option: any) {
    Article.value.tagList = value
}
const rules = ref({
    articleName: {
        required: true,
        trigger: ['blur', '文章名称'],
        message: '请输入文章名称'
    },
    articleDescribe: {
        required: true,
        trigger: ['blur', '文章描述'],
        message: '请输入文章描述'
    },
    sortId: {
        required: true,
        trigger: ['blur', '文章分类'],
        message: '请输入文章分类',
        validator(rule: any, value: number) {
            return !value ? false : true;
        }
    },
    tagList: {
        required: true,
        trigger: ['blur', '文章标签'],
        message: '请输入文章标签',
        validator(rule: any, value: number[]) {
            return !value.length ? false : true;
        }
    },
    cover: {
        required: true,
        trigger: ['blur', '文章封面'],
        message: '请上传文章封面'
    }
})
//提示框
const message = useMessage()
//文章列表
const ArticleList = ref<ArticleType[]>([])
//添加文章框是否显示
const ArticleShow = ref(false)
//被选中的文章
const ArticleIndex = ref<any>({ content: "" })

// 定义Article的类型  
interface ArticleType {
    id: string;
    content?: string;
    status?: number;
    articleName: string;
    articleDescribe: string;
    sortId: number | undefined;
    tagList: any[];
    cover: string;
    videoUrl: string;
}
//添加文章对象
const Article = ref<ArticleType>({
    id: "",
    articleName: "",
    articleDescribe: "",
    sortId: undefined,
    tagList: [],
    cover: "",
    videoUrl: ""
})
//分类配置
const sortOptions = ref()
//标签配置
const tagOptions = ref()
//删除列表
const deleteArticleList = ref([])
//删除提示框
const ArticleDeleteShow = ref(false)
onMounted(() => {
    //初始化数据
    init();
})
//复制代码
function handleCopyCodeSuccess() {
    message.success("复制成功")
}
//修改文章
function openArticleUpdate(articleId: any) {
    getArticle(articleId).then(res => {
        Article.value = res.data;
        let ResultTagList: any = Article.value.tagList;
        nextTick(() => {
            //清空标签列表
            Article.value.tagList = [];
            //给标签列表赋值
            for (const tag of ResultTagList) {
                Article.value.tagList.push(tag.tagId)
            }
            ArticleShow.value = true;
        })
    })
}
//数据初始化
function init() {
    listArticleByUser().then((res: any) => {
        //获取列表
        ArticleList.value = res.data;
        //防止文章内容为空
        if (!ArticleList.value[0].content) {
            ArticleList.value[0].content = ''
        }
        //获取第一条数据ID
        ArticleIndex.value = ArticleList.value[0];
    })
    listSort().then((res: any) => {
        sortOptions.value = res.rows.map((item: any) => {
            return {
                value: item.id,
                label: item.sortName,
                tagList: item.tagList
            }
        });
    })
}
//切换文章
function changeArticle(article: any) {
    getArticle(article.id).then(res => {
        //防止文章内容为空
        if (!res.data.content) {
            res.data.content = ''
        }
        ArticleIndex.value = res.data;
    })

}
//打开新增文章
function openArticleAdd() {
    //清空
    Article.value = {
        id: "",
        articleName: "",
        articleDescribe: "",
        sortId: undefined,
        tagList: [],
        cover: "",
        videoUrl: ""
    }
    ArticleShow.value = true;
}
//打开删除文章
function openArticleDelete() {
    ArticleDeleteShow.value = true;
}
//新增文章
function addArticleSubmit() {
    //设置标签列表
    const copyTagList = [];
    for (const item of Article.value.tagList) {
        copyTagList.push({ tagId: item })
    }
    Article.value.tagList = copyTagList;
    addArticle(Article.value).then(() => {
        ArticleShow.value = false;
        message.success("新增成功");
        init();
    }).catch(() => {
        message.error("新增失败")
    })
}
//删除文章
function deleteArticleSubmit() {
    delArticle(deleteArticleList.value).then(() => {
        message.success("删除成功")
        init();
    }).catch((error) => {
        message.error(error)
    })
}
//保存文章
function saveArticle() {
    updateArticle(ArticleIndex.value).then(res => {
        message.success("保存成功")
    }).catch(() => {
        message.error("保存失败")
    })
}
//文章编辑器上传图片
function handleUploadImage(insertImage: any, file: any) {
    const formData = new FormData();
    formData.append("file", file[0])
    uploadImages(formData).then(res => {
        insertImage({
            url: res.data.url,
            desc: res.data.name,
            width: '100%',
            height: '300px',
        })
        message.success("上传图片成功")
    }).catch(() => {
        message.error("上传图片失败")
    })
}
//图片上传成功的函数
function uploadImageOnSuccess(data: any) {
    //设置文章封面地址
    Article.value.cover = data.url;
}
//文件上传成功的函数
function uploadFileOnSuccess(data: any) {
    //设置文章封面地址
    Article.value.videoUrl = data.url;
}
// 监听分类变化,实时获取标签列表
watch(
    () => Article.value.sortId,
    (newVal) => {
        Article.value.tagList = [];
        const sortResult = sortOptions.value.filter((item: { value: any }) => item.value === newVal)
        if (!newVal || !sortResult) {
            return;
        }
        tagOptions.value = sortResult[0].tagList.map((item: any) => {
            return {
                key: item.id,
                label: item.tagName
            }
        })
    },
    {
        deep: false,
        immediate: false,
    }
);
</script>

<style lang="scss" scoped>
.article {
    display: flex;
    width: 100%;

    .left {
        box-sizing: border-box;
        border-radius: 10px;
        width: 15%;
        height: 100%;
        margin: 20px;
        border: 1px solid #c8d9eb;

        .article_title {
            display: flex;
            justify-content: center;
            font-size: 14px;
            margin: 10px;
        }

        .article_menu {
            display: flex;
            justify-content: space-between;
            font-size: 12px;
            padding: 10px;
            border-top: 1px solid #c8d9eb;

            span:hover {
                color: #00e0ff;
            }
        }

        .article_list {
            display: flex;
            flex-direction: column;

            .item {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100%;
                height: 30px;
                padding: 10px 0;
                font-size: 12px;
                border-top: 1px solid #c8d9eb;

                input {
                    float: left;
                    margin: 0 10px;
                }

                span {
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    flex-grow: 1;
                }

                span:first-of-type {
                    max-width: 50%;
                    min-width: 50%;
                    display: -webkit-box;
                    -webkit-line-clamp: 2;
                    -webkit-box-orient: vertical;
                    text-overflow: ellipsis;
                    overflow: hidden;
                }

                span:last-child {
                    display: flex;
                    justify-content: end;
                    align-items: right;
                    margin-right: 5px;
                }

                span:hover {
                    color: #00e0ff;
                }
            }
        }
    }

    .right {
        flex-grow: 1;
        height: 100%;
        margin: 20px;
    }

    .article_dialog {
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

            .article_title {
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

            .article_name {
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

            .article_describe {
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

            .article_sort {
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

                select {
                    margin-left: 8px;
                    font-size: 12px;
                }
            }

            .article_tag {
                padding-left: 10px;
                margin-top: 20px;
                display: flex;
                flex-wrap: wrap;
                justify-content: left;
                align-items: center;

                span {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    font-size: 12px;
                }

                label {
                    display: flex;
                    margin-left: 8px;
                    font-size: 12px;
                }
            }

            .article_avatar {
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

            .article_file {
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

    .article_delete_dialog {
        position: fixed;
        display: flex;
        justify-content: center;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        padding-top: 10%;
        background-color: rgba(rgb(0, 0, 0), 0.3);

        .dialog {
            position: fixed;
            width: 420px;
            border-radius: 10px;
            background-color: #FFFFFF;

            .article_title {
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

            .article_body {
                margin: 10px;
                font-size: 14px;
            }

            .button_box {
                display: flex;
                margin: 10px;

                button {
                    padding: 3px;
                    margin-right: 10px;
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
