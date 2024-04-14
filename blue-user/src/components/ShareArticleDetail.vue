<template>
    <div class="article">
        <div class="left">
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
            <div class="article_info">
                <div class="article_img">
                    <img :src="ArticleIndex.cover">
                </div>
                <div class="article_video">
                    <ArticleVideo @get-instance="getInstance" :style="style"
                        style="overflow: hidden; padding: 10px 5px;box-sizing: border-box;"
                        v-show="ArticleIndex.videoUrl" />
                </div>
            </div>
            <v-md-editor v-model="ArticleIndex.content" mode="editable" height="100vh" style="background: #ECEBEC;"
                @save="saveArticle" :disabled-menus="[]" @upload-image="handleUploadImage"></v-md-editor>
        </div>
        <!-- 添加修改框 -->
        <div class="article_dialog" v-show="ArticleShow">
            <div class="dialog">
                <div class="article_title">
                    <span v-show="!Article.id">添加文章</span>
                    <span v-show="Article.id">修改文章</span>
                    <svg class="icon pointer" aria-hidden="true" @click="closeArticleAdd">
                        <use xlink:href="#icon-guanbi"></use>
                    </svg>
                </div>
                <div class="article_name">
                    <span>文章名称</span>
                    <input type="text" v-model="Article.articleName" placeholder="请输入文章名称">
                </div>
                <div class="article_describe">
                    <span>文章描述</span>
                    <input type="text" v-model="Article.articleDescribe" placeholder="请输入文章描述">
                </div>
                <div class="article_sort">
                    <span>文章分类</span>
                    <select id="sort" name="sort" v-model="Article.sortId">
                        <option :value="sort.id" v-for="sort in SortList">{{ sort.sortName }}</option>
                    </select>
                </div>
                <div class="article_tag" v-show="Article.sortId">
                    <span>文章标签</span>
                    <label v-for="tag in tagList" :key="tag.id">
                        <input type="checkbox" v-model="Article.tagList" checked name="tags" :value="tag.id">
                        {{ tag.tagName }}
                    </label>
                </div>
                <div class="article_avatar">
                    <span>上传封面</span>
                    <input type="file" @change="handleImageUpload" accept="image/*">
                </div>
                <div class="article_file">
                    <span>上传视频</span>
                    <input type="file" @change="handleFileUpload" accept="video/*">
                </div>
                <div class="button_box">
                    <button @click="addArticleSubmit" v-show="!Article.id">添加</button>
                    <button @click="updateArticleSubmit" v-show="Article.id">修改</button>
                </div>
            </div>
        </div>
        <!-- 删除框 -->
        <div class="article_delete_dialog" v-show="ArticleDeleteShow">
            <div class="dialog">
                <div class="article_title">
                    <span>删除文章</span>
                    <svg class="icon pointer" aria-hidden="true" @click="closeArticleDelete">
                        <use xlink:href="#icon-guanbi"></use>
                    </svg>
                </div>
                <div class="article_body">
                    删除文章ID为: {{ deleteArticleList }} 的数据
                </div>
                <div class="button_box">
                    <button @click="deleteArticleSubmit">确认删除</button>
                    <button @click="closeArticleDelete">取消删除</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import {onMounted, ref, watch} from 'vue'
import ArticleVideo from '@/components/ArticleVideo.vue'
import {addArticle, delArticle, getArticle, listArticleByUser, updateArticle} from '@/api/article'
import {listSort} from '@/api/sort/sort'
import {uploadImages} from "@/api/file";
import promptMsg from "@/components/PromptBoxView"
//文章列表
const ArticleList = ref()
//添加文章框是否显示
const ArticleShow = ref(false)
//被选中的文章
const ArticleIndex = ref({
    content: ''
})
//添加文章对象
const Article = ref({
})
//分类列表
const SortList = ref()
//标签列表
const tagList = ref()
//删除列表
const deleteArticleList = ref([])
//删除提示框
const ArticleDeleteShow = ref(false)
//视频播放器对象
const artInstance = ref()
//视频播放器样式
const style = ref({
    width: '100%',
    height: '200px',
})
onMounted(() => {
    //获取视频播放示例对象
    getInstance()
    //初始化数据
    init();
})
//修改文章
function openArticleUpdate(articleId) {
    getArticle(articleId).then(res => {
        Article.value = res.data;
        ArticleShow.value = true;
    })
}
//获取视频播放实例
function getInstance(art) {
    artInstance.value = art;
}
//数据初始化
function init() {
    listArticleByUser().then(res => {
        //获取列表
        ArticleList.value = res.data;
        //获取第一条数据ID
        ArticleIndex.value = ArticleList.value[0];
        //初始化视频
        artInstance.value.url = ArticleIndex.value.videoUrl;
    })
    listSort().then(res => {
        SortList.value = res.rows;
    })
}
//切换文章
function changeArticle(article) {
    getArticle(article.id).then(res => {
        //防止文章内容为空
        if (!res.data.content) {
            res.data.content = ''
        }
        ArticleIndex.value = res.data;
        artInstance.value.url = ArticleIndex.value.videoUrl;
        //播放
        artInstance.value.play()
    })

}
//打开新增文章
function openArticleAdd() {
    ArticleShow.value = true;
}
//打开删除文章
function openArticleDelete() {
    ArticleDeleteShow.value = true;
}
//关闭删除文章框
function closeArticleDelete() {
    deleteArticleList.value = []
    ArticleDeleteShow.value = false;
}
//关闭添加文章框
function closeArticleAdd() {
    Article.value = {
        tagList: []
    }
    ArticleShow.value = false;
}
//修改文章
function updateArticleSubmit() {
    if (!Article.value.articleName) {
        promptMsg({ type: "warn", msg: "请添加文章标题" })
        return;
    }
    if (!Article.value.articleDescribe) {
        promptMsg({ type: "warn", msg: "请添加文章描述" })
        return;
    }
    if (!Article.value.cover) {
        promptMsg({ type: "warn", msg: "请添加文章封面" })
        return;
    }
    if (!Article.value.sortId) {
        promptMsg({ type: "warn", msg: "请选择文章分类" })
        return;
    }
    if (!Article.value.tagList || Article.value.tagList.length === 0) {
        promptMsg({ type: "warn", msg: "请选择标签列表" })
        return;
    }
    //设置标签列表
    const tagList = [];
    for (const tagId of Article.value.tagList) {
        tagList.push({ tagId: tagId })
    }
    Article.value.tagList = tagList;
    updateArticle(Article.value).then(res => {
        promptMsg({ type: "success", msg: "修改成功" })
        init();
    }).catch(error => {
        promptMsg({ type: "error", msg: "修改失败" })
    })
    closeArticleAdd();
}
//新增文章
function addArticleSubmit() {
    if (!Article.value.articleName) {
        promptMsg({ type: "warn", msg: "请添加文章标题" })
        return;
    }
    if (!Article.value.articleDescribe) {
        promptMsg({ type: "warn", msg: "请添加文章描述" })
        return;
    }
    if (!Article.value.cover) {
        promptMsg({ type: "warn", msg: "请添加文章封面" })
        return;
    }
    if (!Article.value.sortId) {
        promptMsg({ type: "warn", msg: "请选择文章分类" })
        return;
    }
    if (!Article.value.tagList || Article.value.tagList.length === 0) {
        promptMsg({ type: "warn", msg: "请选择标签列表" })
        return;
    }
    //设置标签列表
    const tagList = [];
    for (const tagId of Article.value.tagList) {
        tagList.push({ tagId: tagId })
    }
    Article.value.tagList = tagList;
    addArticle(Article.value).then(res => {
        promptMsg({ type: "success", msg: "新增成功" })
        init();
    }).catch(error => {
        promptMsg({ type: "error", msg: "新增失败" })
    })
    closeArticleAdd();
}
//删除文章
function deleteArticleSubmit() {
    delArticle(deleteArticleList.value).then(res => {
        promptMsg({ type: "success", msg: "删除成功" })
        init();
    }).catch(error => {
        promptMsg({ type: "error", msg: "删除失败" })
    })
    closeArticleDelete();
}
//保存文章
function saveArticle() {
    updateArticle(Article.value).then(res => {
        promptMsg({ type: "success", msg: "保存成功" })
    }).catch(error => {
        promptMsg({ type: "error", msg: "保存失败" })
    })
}
//文章编辑器上传图片
function handleUploadImage(event, insertImage, file) {
    const formData = new FormData();
    formData.append("file", file[0])
    uploadImages(formData).then(res => {
        insertImage({
            url: res.data.url,
            desc: res.data.name,
            width: '300px',
            height: '300px',
        })
        promptMsg({ type: "success", msg: "上传图片成功!" })
    }).catch(error => {
        promptMsg({ type: "error", msg: "上传图片失败!" + error })
    })
}
//上传图片
function handleImageUpload(event) {
    const formData = new FormData();
    formData.append("file", event.target.files[0])
    uploadImages(formData).then(res => {
        Article.value.cover = res.data.url;
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
        Article.value.videoUrl = res.data.url;
        promptMsg({ type: "success", msg: "上传文件成功!" })
    }).catch(error => {
        promptMsg({ type: "error", msg: "上传文件失败!" + error })
    })
}
watch(
    // 监听 Article.sortId 的变化 
    () => Article.value.sortId,
    (newVal, oldVal) => {
        Article.value.tagList = [];
        if (!newVal) {
            return;
        }
        const Sort = SortList.value.filter(item => item.id === newVal)
        tagList.value = Sort[0].tagList;
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
                    height: 100%;
                    flex-grow: 1;
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

        .article_info {
            display: flex;
            width: 100%;

            .article_img {
                box-sizing: border-box;
                padding: 10px 5px;
                width: 50%;
                height: 200px;
                object-fit: cover;

                img {
                    width: 100%;
                    height: 100%;
                    object-fit: cover;
                }
            }

            .article_video {
                width: 50%;
                height: 200px
            }
        }
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
                justify-content: left;

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
