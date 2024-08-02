<template>
    <div class="erchuang pointer box-shadow animate__animated animate__zoomIn" @click="goHref(erchuang.ecUrl)">
        <div class="erchuang-img">
            <img :src="erchuang.ecAvater">
        </div>
        <div class="erchuang-title">
            <span><n-ellipsis :line-clamp="1">
                    {{ erchuang.ecName }}
                </n-ellipsis>
            </span>
        </div>
        <div class="erchuang-info">
            <span class="erchuang-author">{{ erchuang.ecAuthor }}</span>&nbsp;·&nbsp;<span class="erchuang-time">{{
                erchuang.ecPublish }}</span>
        </div>
    </div>
</template>

<script setup lang="ts">
import { NEllipsis } from 'naive-ui'
const props = defineProps(['erchuang'])
function goHref(url: string) {
    window.open(url, "-blank")
}
</script>

<style lang="scss" scoped>
.erchuang {
    width: 32%;
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    flex-direction: row;
    border-radius: 10px 10px 10px 10px;
    margin-top: 30px;

    .erchuang-img {
        border-radius: 10px 10px 0 0;
        overflow: hidden;
        width: 100%;

        img {
            width: 100%;
            min-height: 170px;
            max-height: 170px;
            transition: all 0.3s ease-in-out;
            object-fit: cover;
        }

        img:hover {
            transform: scale(1.1);
        }
    }

    .erchuang-title {
        width: 100%;
        padding: 10px 7px 0px 7px;
        font-size: 13px;
    }

    .erchuang-title:hover {
        color: #3498DB;
    }

    .erchuang-info {
        width: 100%;
        display: flex;
        align-items: flex-end;
        padding: 10px 7px 7px 7px;

        span {
            font-weight: 100;
            font-size: 13px;
        }
    }

    .erchuang-info:hover {
        color: #3498DB;
    }
}
</style>