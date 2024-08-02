<template>
    <div class="photo box-shadow animate__animated animate__zoomIn">
        <div class="photo-img">
            <n-image width="100%" :src="photo.photoUrl" />
        </div>
        <div class="photo-msg">
            <span>
                {{ photo.photoName }}
            </span>
        </div>
        <div class="photo-time">
            <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-shalou"></use>
            </svg>
            <span>
                {{ photo.createTime }}
            </span>
        </div>
    </div>
</template>
<script setup lang="ts">
import { NImage } from 'naive-ui'
const props = defineProps(['photo'])
</script>
<style lang="scss" scoped>
.photo {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 20% !important;
    margin: 0 2%;
    max-height: 300px;
    border-radius: 10px;
    padding-bottom: 0;

    .photo-img {
        display: flex;
        justify-content: center;
        width: 80%;
        min-height: 60%;
        max-height: 60%;
        overflow: hidden;
        border-radius: 10px;
        margin: 10px;

        img {
            width: 100%;
            object-fit: cover;
            transition: all .3s ease-in 0s;
            border-radius: 10px;
        }

        img:hover {
            object-fit: cover;
            transform: scale(1.1);
            transition: all 0.3s ease-in;
        }
    }

    .photo-msg {
        display: flex;
        width: 80%;
        margin: 5px 0;

        span {
            font-size: 12px;
        }
    }



    .photo-time {
        width: 80%;

        .icon {
            font-size: 14px;
        }

        span {
            font-size: 10px;
        }
    }
}
</style>