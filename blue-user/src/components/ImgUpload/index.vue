<template>
    <n-upload action="/api/file/upload" list-type="image-card" :custom-request="customRequest"
        v-model:file-list="fileList" :show-retry-button="true" @before-upload="beforeUpload" @remove="handleRemove"
        :max="limit" :headers="{
            'naive-info': 'hello!'
        }" :data="{
            'naive-data': 'cool! naive!'
        }">
        点击上传
    </n-upload>
</template>
<script lang="ts" setup>
import { defineProps, ref, defineEmits, onMounted } from 'vue'
import { uploadImages } from "@/api/file";
import { useMessage, NUpload, type UploadCustomRequestOptions, type UploadFileInfo } from 'naive-ui'
const emit = defineEmits();
//接收参数
const props = defineProps({
    //传递过来的值
    modelValue: [String, Array],
    // 数量限制
    limit: {
        type: Number,
        default: 1,
    },
    // 大小限制(MB)
    fileSize: {
        type: Number,
        default: 10,
    },
    fileType: {
        type: Array,
        default: () => ["image/png", "image/jpg", "image/gif"],
    },
})
//文件列表
const fileList = ref<UploadFileInfo[]>([])
//提示框
const message = useMessage()
onMounted(() => {
    if (props.modelValue) {
        // 判断是不是数组
        if (Array.isArray(props.modelValue) && props.modelValue.length) {
            for (let index = 0; index < props.modelValue.length; index++) {
                const url: string = String(props.modelValue[index])
                const previewFile: UploadFileInfo = {
                    id: url,
                    name: '预览图片',
                    status: 'finished',
                    url: url
                };
                fileList.value.push(previewFile)
            }
        } else {
            const urls: string = String(props.modelValue)
            let fileListStr = urls.split(",");
            fileListStr.forEach((item) => {
                const previewFile: UploadFileInfo = {
                    id: item,
                    name: '预览图片',
                    status: 'finished',
                    url: item
                };
                fileList.value.push(previewFile)
            })
        }
    } else {
        fileList.value = [];
        return [];
    }
})
//自定义上传文件
function customRequest({
    file,
}: UploadCustomRequestOptions) {
    uploadImages(file).then(res => {
        //给文件列表路径标识赋值
        fileList.value.forEach((fileObj) => {
            if (fileObj.batchId === file.batchId) {
                fileObj.url = res.data.url;
            }
        })
        message.success("上传图片成功")
        uploadedSuccessfully();
    }).catch(error => {
        message.error("上传图片失败:" + error)
    })
}
//限制类型大小
function beforeUpload(data: {
    file: UploadFileInfo
    fileList: UploadFileInfo[]
}) {
    const size: any = data.file.file?.size;
    const isLt = size / 1024 / 1024 < props.fileSize;
    if (!isLt) {
        message.error(`上传图片大小不能超过 ${props.fileSize} MB!`);
        return false;
    }
    if (!props.fileType.includes(data.file.file?.type)) {
        message.error("图片类型不匹配,请上传" + props.fileType + "类型的文件")
        return false;
    }
    return true
}
//上传成功处理函数
function uploadedSuccessfully() {
    emit("update:modelValue", listToString(fileList.value));
}
//文件删除函数
function handleRemove(data: { file: UploadFileInfo; fileList: UploadFileInfo[] }) {
    // 使用 filter 方法移除 batchId 相同的元素
    fileList.value = fileList.value.filter((item) => item.batchId !== data.file.batchId);
    //数据同步
    uploadedSuccessfully()
    message.success("删除成功")
}
//工具函数
function listToString(list: Array<any>, separator?: string) {
    let strs = "";
    separator = separator || ",";
    for (let index = 0; index < list.length; index++) {
        let item = list[index]
        if (!index) {
            strs += item.url
        } else {
            strs += separator + item.url
        }
    }
    return strs;
}
</script>
<style lang="scss" scope></style>