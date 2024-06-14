<template>
    <n-upload action="/api/file/upload" list-type="image-card" :custom-request="customRequest"
        @before-upload="beforeUpload" @on-error="onError" :max="limit" :headers="{
            'naive-info': 'hello!'
        }" :data="{
            'naive-data': 'cool! naive!'
        }">
        点击上传
    </n-upload>
</template>
<script lang="ts" setup>
import { defineEmits, defineProps } from 'vue'
import { uploadImages } from "@/api/file";
import { useMessage, NUpload, type UploadCustomRequestOptions, type UploadFileInfo } from 'naive-ui'
//接收参数
const props = defineProps({
    // 数量限制
    limit: {
        type: Number,
        default: 1,
    },
    // 大小限制(MB)
    fileSize: {
        type: Number,
        default: 5,
    },
    fileType: {
        type: Array,
        default: () => ["image/png", "image/jpg", "image/gif"],
    },
})

const emit = defineEmits(['onSuccess'])
//提示框
const message = useMessage()
//上传文件函数
function customRequest({
    file,
}: UploadCustomRequestOptions) {
    uploadImages(file).then(res => {
        onSuccess(res.data)
        message.success("上传图片成功")
    }).catch(error => {
        message.error("上传图片失败" + error)
    })
}
//限制上传文件类型和文件大小
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
//上传成功后的函数
function onSuccess(data: any) {
    emit('onSuccess', data)
}
//上传失败的函数
function onError() {
    message.error("上传图片失败")
}
</script>
<style lang="scss" scope>
</style>