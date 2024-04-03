<template>
    <div class="pixiv">
        <div class="pixiv_left">
    
        </div>
        <div class="pixiv_right"></div>
    </div>
</template>

<script setup>
import {onMounted} from "vue"
import {useRouter} from 'vue-router'

const router = useRouter()
onMounted(() => {
    //获取从路由传递过来的参数
    if (router.currentRoute.value.query.pixiv) {
        sortIndex.value = router.currentRoute.value.query.pixiv
    }
})
</script>

<style lang="scss" scoped>
.pixiv {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    padding: 60px 0 40px 0;
    height: 1000px;

    .pixiv_left {
        width: 50%;
        height: 100%;
        background-color: red;
    }

    .pixiv_right {
        width: 30%;
        height: 100%;
        background-color: blue;
    }
}
</style>
