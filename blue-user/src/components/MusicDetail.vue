<template>
    <div class="container" v-for="item in musicList">
        <div class="title">
            <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-xiaofengche"></use>
            </svg>
            <span>{{ item.sortName }}</span>
        </div>
        <div class="musics" v-for="music in item.musicList" :key="music.id">
            <div class="music">
                <div class="music_img" @click="startMusic($event, music.musicUrl)">
                    <img :src="music.imgUrl">
                </div>
                <div class="music_name">
                    <span>{{ music.musicName }}</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
const props = defineProps(['musicList', 'startMusic'])
</script>

<style lang="scss" scoped>
.container {
    width: 90%;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    flex-wrap: wrap;
    padding-top: 20px;
    margin: 0 auto;

    .title {
        width: 100%;

        .icon {
            font-size: 24px;
            animation-name: rotateAll;
            /*关键帧名称*/
            animation-timing-function: linear;
            /*动画的速度曲线*/
            animation-iteration-count: infinite;
            /*动画所花费的时间*/
            animation-duration: 2s;
        }

        span {
            padding-left: 5px;
            font-size: 18px;
        }
    }


    .musics {
        display: flex;
        flex-wrap: wrap;
        max-width: 150px;
        flex: 1;

        .title {
            width: 100%;
            display: flex;
            align-items: center;
            padding: 20px 0px 0px 20px;
        }

        .music {
            display: flex;
            flex-direction: column;
            align-items: center;
            flex-wrap: wrap;
            padding-right: 30px;
            padding-top: 30px;

            .music_img {
                width: 100px;
                height: 100px;
                overflow: hidden;
                flex: 5;

                img {
                    border-radius: 50%;
                    max-width: 100%;
                    height: auto;
                    object-fit: cover;
                    transition: all 1s ease-in;
                }

                img:hover {
                    object-fit: cover;
                    transform: rotate(360deg);
                    transition: all 1s ease-in;
                }
            }

            .music_name {
                display: flex;
                align-items: center;
                justify-content: center;
                padding-top: 10px;
                display: -webkit-box;
                -webkit-line-clamp: 1;
                -webkit-box-orient: vertical;
                text-overflow: ellipsis;
                overflow: hidden;
            }
        }
    }

}
</style>