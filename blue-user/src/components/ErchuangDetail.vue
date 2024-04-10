<template>
    <div class="erchuang pointer box_shadow animate__animated animate__zoomIn" @click="goHref(erchuang.ecUrl)">
        <div class="erchuang_img">
            <img v-lazy="erchuang.ecAvater">
        </div>
        <div class="erchuang_title">
            <span>{{ erchuang.ecName }}</span>
        </div>
        <div class="erchuang_info">
            <span class="erchuang_author">{{ erchuang.ecAuthor }}</span>&nbsp;·&nbsp;<span class="erchuang_time">{{
                erchuang.ecPublish }}</span>
        </div>
    </div>
</template>

<script setup lang="ts">
const props = defineProps(['erchuang'])
function goHref(url: string) {
    window.open(url, "_blank")
}
</script>

<style lang="scss" scoped>
.erchuang {
    width: 29%;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    flex-direction: row;
    border-radius: 10px 10px 10px 10px;
    margin-top: 30px;

    .erchuang_img {
        max-width: 300px;
        max-height: 300px;
        border-radius: 10px 10px 0 0;
        overflow: hidden;

        img {
            width: 100%;
            height: 100%;
            transition: all 0.3s ease-in-out;
            object-fit: cover;
        }

        img:hover {
            transform: scale(1.1);
        }
    }

    .erchuang_title {
        width: 100%;
        padding: 10px 7px 0px 7px;
        font-size: 13px;

        span {
            display: -webkit-box;
            -webkit-line-clamp: 2;
            /* 设置最大行数 */
            -webkit-box-orient: vertical;
            overflow: hidden;
            /* 隐藏超出部分 */
            text-overflow: ellipsis;
            /* 在文本溢出时显示省略号 */
        }
    }

    .erchuang_title:hover {
        color: #3498DB;
    }

    .erchuang_info {
        width: 100%;
        display: flex;
        align-items: flex-end;
        padding: 10px 7px 7px 7px;

        span {
            font-weight: 100;
            font-size: 13px;
        }
    }

    .erchuang_info:hover {
        color: #3498DB;
    }
}
</style>