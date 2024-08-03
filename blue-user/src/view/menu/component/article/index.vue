<template>
    <div class="article">
        <div class="left animate__animated animate__fadeInUp">
            <div class="article-title">
                我的文章
            </div>
            <div class="article-menu">
                <span class="pointer" @click="handleArticleAdd">添加文章</span>
                <span class="pointer" @click="handleArticleExamine">提交审核</span>
                <span class="pointer" @click="handleArticleDelete">删除文章</span>
            </div>
            <div class="article-list">
                <div class="item pointer" v-for="item in ArticleList">
                    <input type="checkbox" v-model="deleteArticleList" name="article" :value="item.id">
                    <span @click="changeArticle(item)" style="flex: 1;">
                        <n-ellipsis :line-clamp="1" style="max-width: 50px;"
                            :style="item.id == ArticleIndex.id ? 'color: #00e0ff;' : ''">
                            {{ item.articleName }}
                        </n-ellipsis>
                    </span>
                    <span>{{
                        item.status === 0 ? '审核中' : item.status === 1 ? '审核中' : item.status === 2 ? '审核通过' : '审核未通过' }}
                    </span>
                    <span class="pointer" @click="handleArticleUpdate(item.id)">修改文章</span>
                </div>
            </div>
        </div>
        <div class="right animate__animated animate--fadeInRight">
            <v-md-editor v-model="ArticleIndex.content" :include-level="[2, 3, 4]" mode="editable" height="100vh"
                style="background: #ECEBEC;" @save="saveArticle" :disabled-menus="[]"
                left-toolbar="undo  redo h bold italic strikethrough quote ul ol table hr link image code save todo-list emoji tip"
                right-toolbar="preview toc sync-scroll" @upload-image="handleUploadImage"
                @copy-code-success="handleCopyCodeSuccess"></v-md-editor>
        </div>
        <!-- 添加修改框 -->
        <n-modal v-model:show="ArticleShow" transform-origin="center">
            <resuse-form ref="formRef" class="formClass" :formData="article" :formOption="formOption"
                :formItemOption="selectOption" :rules="rules" labelPosition="right" labelWidth="140">
                <template #ImgUpload>
                    <ImgUpload v-model="article.cover"></ImgUpload>
                </template>
                <template #FileUpload>
                    <FileUpload v-model="article.videoUrl"></FileUpload>
                </template>
                <template #Footer>
                    <n-space>
                        <n-button secondary round @click="ArticleShow = false">
                            取消
                        </n-button>
                        <n-button type="info" secondary round @click="articleSubmit">
                            提交
                        </n-button>
                    </n-space>
                </template>
            </resuse-form>
        </n-modal>
        <!-- 删除框 -->
        <n-modal v-model:show="ArticleDeleteShow" transform-origin="center">
            <n-card style="width: 600px" :title="title" :bordered="false" size="huge" role="dialog" aria-modal="true">
                <template #footer>
                    <n-space style="padding-bottom:20px">
                        是否删除文章名称为:{{ mappedArticleNamesByIds }}的数据
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
        <!-- 提交审核框 -->
        <n-modal v-model:show="ArticleExamineShow" transform-origin="center">
            <resuse-form ref="formRef" class="formClass" :formOption="examineOption" :formItemOption="selectOption"
                :rules="examineRules" labelPosition="right" labelWidth="140">
                <template #Footer>
                    <n-space>
                        <n-button secondary round @click="ArticleShow = false">
                            取消
                        </n-button>
                        <n-button type="info" secondary round @click="examineSubmit">
                            提交
                        </n-button>
                    </n-space>
                </template>
            </resuse-form>
        </n-modal>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watch, nextTick, reactive, computed } from 'vue'
import { addArticle, delArticle, getArticle, listArticleByUser, updateArticle, dexamineArticle } from '@/api/article'
import { listSort } from '@/api/sort/sort'
import { uploadImages } from "@/api/file";
import { useMessage, NModal, NCard, NButton, NSpace, NEllipsis } from 'naive-ui'
import resuseForm from '@/components/reuseForm/index.vue'
import ImgUpload from '@/components/imgUpload/index.vue'
import FileUpload from '@/components/fileUpload/index.vue'

//下拉框配置项
const selectOption: any = reactive({
    sortTypeList: [], //分类列表
    tagTypeList: [], //标签列表
    examineList: [] //文章审核下拉列表
})
//表单配置项
const formOption = reactive([
    {
        type: "input", props: "articleName", label: "文章名称", placeholder: "请输入文章名称"
    },
    {
        type: "input", props: "articleDescribe", label: "文章描述", placeholder: "请输入文章描述"
    },
    {
        type: "select", selectProps: "sortTypeList", props: "sortId", label: "文章分类", placeholder: "请选择文章分类"
    },
    {
        type: "mredio", selectProps: "tagTypeList", props: "tagList", handleUpdate: handleUpdate, label: "文章标签", placeholder: "请选择文章标签"
    },
    {
        type: "slot", slotName: "ImgUpload", props: "cover", label: "文章图片"
    },
    {
        type: "slot", slotName: "FileUpload", props: "videoUrl", label: "文章视频"
    },
    {
        type: "slot", slotName: "Footer"
    }
]);
const examineOption = reactive([
    {
        type: "mredio", selectProps: "examineList", props: "articleExamineList", handleUpdate: handleExamineUpdate, label: "文章标签", placeholder: "请选择文章标签"
    },
    {
        type: "slot", slotName: "Footer"
    }
])
//表单
const formRef = ref<any | null>(null)
// 定义Article的类型  
interface ArticleType {
    id?: number | null;
    content?: string;
    status?: number;
    articleName: string;
    articleDescribe: string;
    sortId: number | null;
    tagList: any[];
    cover: string;
    videoUrl: string;
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
        message: '请选择文章分类',
        validator(rule: any, value: number) {
            return !value ? false : true;
        }
    },
    tagList: {
        required: true,
        trigger: ['blur', '文章标签'],
        message: '请选择文章标签',
        validator(rule: any, value: number[]) {
            return !value.length ? false : true;
        }
    },
    cover: {
        required: true,
        trigger: ['blur', '文章封面'],
        message: '请上传文章封面'
    },
    videoUrl: {
        required: false,
        trigger: ['blur', '文章视频'],
        message: '请上传文章视频'
    }
})
const examineRules = ref({
    articleExamineList: {
        required: true,
        trigger: ['blur', '审核列表'],
        message: '请选择要审核的文章',
        validator(rule: any, value: number[]) {
            return !value.length ? false : true;
        }
    },
})
//提示框标签
const title = ref()
//提示框
const message = useMessage()
//文章列表
const ArticleList = ref<ArticleType[]>([])
//添加文章框是否显示
const ArticleShow = ref(false)
//提交审核框
const ArticleExamineShow = ref(false)
//被选中的文章
const ArticleIndex = ref<any>({ content: "" })
//添加文章对象
const article = ref<ArticleType>({
    articleName: "",
    articleDescribe: "",
    sortId: null,
    tagList: [],
    cover: "",
    videoUrl: ""
})
//提交审核文章列表
const articleExamineList = ref([])
//多选
function handleUpdate(val: any) {
    article.value.tagList = val;
}
//多选
function handleExamineUpdate(val: any) {
    articleExamineList.value = val;
}
//删除列表
const deleteArticleList = ref([])
//删除提示框
const ArticleDeleteShow = ref(false)
//删除文章的名称列表
const mappedArticleNamesByIds = computed(() => {
    return deleteArticleList.value.map((id: number) => {
        const article: any = ArticleList.value.find((article) => article.id === id);
        return article.articleName
    })
})
onMounted(() => {
    //初始化数据
    init();
})
//提交审核
function handleArticleExamine() {
    //获取未审核的文章列表
    selectOption.examineList = ArticleList.value.filter((item: any) => {
        return item.status === 0 || item.status === 3;
    }).map((item: any) => {
        let { id, articleName } = item;
        return {
            key: id,
            label: articleName
        };
    });
    ArticleExamineShow.value = true;
}
//复制代码
function handleCopyCodeSuccess() {
    message.success("复制成功")
}
//打开修改文章
function handleArticleUpdate(articleId: any) {
    title.value = "修改文章"
    getArticle(articleId).then(res => {
        article.value = res.data;
        let ResultTagList: any = article.value.tagList;
        nextTick(() => {
            //清空标签列表
            article.value.tagList = [];
            //给标签列表赋值
            for (const tag of ResultTagList) {
                article.value.tagList.push(tag.tagId)
            }
            ArticleShow.value = true;
        })
    })
}
//数据初始化
function init() {
    //设置子组件ref
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
        selectOption.sortTypeList = res.rows.map((item: any) => {
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
function handleArticleAdd() {
    title.value = "新增文章"
    //清空
    article.value = {
        id: null,
        articleName: "",
        articleDescribe: "",
        sortId: null,
        tagList: [],
        cover: "",
        videoUrl: ""
    }
    ArticleShow.value = true;
}
//打开删除文章
function handleArticleDelete() {
    if (deleteArticleList.value.length) {
        ArticleDeleteShow.value = true;
    } else {
        message.warning("请勾选需要删除的文章")
    }
}
//文章提交
function articleSubmit() {
    formRef.value?.ruleFormRef().validate((errors: any) => {
        if (!errors) {
            //设置标签列表
            const copyTagList = [];
            for (const item of article.value.tagList) {
                copyTagList.push({ tagId: item })
            }
            article.value.tagList = copyTagList;
            if (article.value.id != null) {
                updateArticle(article.value).then(() => {
                    message.success("修改成功")
                    ArticleShow.value = false;
                    init();
                }).catch((error) => {
                    message.error("修改失败:" + error)
                })
            } else {
                addArticle(article.value).then(() => {
                    message.success("新增成功");
                    ArticleShow.value = false;
                    init();
                }).catch(() => {
                    message.error("新增失败")
                })
            }
        } else {
            message.warning('请检查参数')
        }
    })

}
//删除文章
function deleteArticleSubmit() {
    delArticle(deleteArticleList.value).then(() => {
        deleteArticleList.value = []
        ArticleDeleteShow.value = false;
        message.success("删除成功")
        init();
    }).catch((error) => {
        message.error(error)
    })
}
//提交审核文章
function examineSubmit() {
    dexamineArticle(articleExamineList.value).then(() => {
        articleExamineList.value = []
        ArticleExamineShow.value = false;
        message.success("提交成功")
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
function handleUploadImage(event: any, insertImage: any, files: any) {
    const formData = new FormData();
    formData.append("file", files[0])
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
// 监听分类变化,实时获取标签列表
watch(
    () => article.value.sortId,
    (newVal) => {
        article.value.tagList = [];
        const sortResult = selectOption.sortTypeList.filter((item: { value: any }) => item.value === newVal)
        if (!newVal || !sortResult) {
            return;
        }
        selectOption.tagTypeList = sortResult[0].tagList.map((item: any) => {
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
    margin-bottom: 40px;

    .left {
        border-radius: 10px;
        width: 15%;
        height: 100%;
        margin: 20px;
        border: 1px solid #c8d9eb;

        .article-title {
            display: flex;
            justify-content: center;
            font-size: 14px;
            margin: 10px;
        }

        .article-menu {
            display: flex;
            justify-content: space-between;
            font-size: 12px;
            padding: 10px;
            border-top: 1px solid #c8d9eb;

            span:hover {
                color: #00e0ff;
            }
        }

        .article-list {
            display: flex;
            flex-wrap: wrap;
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
        flex-grow: 1;
        height: 100%;
        margin: 20px;
    }
}
</style>
