<template>
    <div class="article">
        <div class="left">
            <div class="article_title">
                我的文章
            </div>
            <div class="article_list">
                <div class="item pointer" v-for="article in ArticleList" @click="changeArticle(article)">
                    <span>{{ article.articleName }}</span>
                </div>
            </div>
        </div>
        <div class="right">
            <v-md-editor v-model="Article.content" mode="editable" height="100vh" style="background: #ECEBEC;"
                @save="saveArticle" left-toolbar="undo redo | image" :disabled-menus="[]"
                @upload-image="handleUploadImage"></v-md-editor>
        </div>
    </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import {getArticle, listArticleByUser, updateArticle} from '@/api/article'
import {uploadImages} from "@/api/file";
import promptMsg from "@/components/PromptBoxView"

const ArticleList = ref()
//被选中的文章
const Article = ref({
    content: ''
}
)
onMounted(() => {
    listArticleByUser().then(res => {
        //获取列表
        ArticleList.value = res.rows
        //获取第一条数据ID
        Article.value = res.rows[0]
    })
})
//切换文章
function changeArticle(article) {
    getArticle(article.id).then(res => {
        //防止文章内容为空
        if (!res.data.content) {
            res.data.content = ''
        }
        Article.value = res.data;
    })

}
//保存文章
function saveArticle() {
    updateArticle(Article.value).then(res => {
        promptMsg({ type: "success", msg: "保存成功" })
    }).catch(error => {
        promptMsg({ type: "success", msg: error })
    })
}
//上传图片
function handleUploadImage(event, insertImage, file) {
    const formData = new FormData();
    formData.append("file", file[0])
    uploadImages(formData).then(res => {
        insertImage({
            url: res.data.url,
            desc: res.data.name,
            width: '100%',
            height: 'auto',
        })
        promptMsg({ type: "success", msg: "上传图片成功!" })
    }).catch(error => {
        promptMsg({ type: "error", msg: "上传图片失败!" + error })
    })
}
</script>

<style lang="scss" scoped>
.article {
    display: flex;
    width: 100%;

    .left {
        box-sizing: border-box;
        width: 12%;
        height: 100%;
        margin: 20px;
        border: 1px solid #c8d9eb;

        .article_title {
            display: flex;
            justify-content: center;
            font-size: 14px;
            margin: 10px;
        }

        .article_list {
            display: flex;
            flex-direction: column;

            .item {
                display: flex;
                justify-content: center;
                width: 100%;
                padding: 10px 0;
                font-size: 12px;
                border-top: 1px solid #c8d9eb;
            }

            .item:hover {
                color: #00e0ff;
            }
        }
    }

    .right {
        flex-grow: 1;
        height: 100%;
        margin: 20px;
    }
}
</style>
