<template>
    <div class="tool">
        <div class="tool-sort animate__animated animate__slideInLeft">
            <h2>
                {{ tool.sortName }}
            </h2>
        </div>
        <div class="tools">
            <div class="tool-detail pointer box-shadow animate__animated animate__slideInRight"
                v-for="toolDetail in tool.blueProgramToolList" @click="goHref(toolDetail.toolUrl)">
                <div class="tool-left">
                    <div class="tool-img">
                        <img :src="toolDetail.toolAvater" />
                    </div>
                </div>
                <div class="tool-right">
                    <div class="tool-title">
                        <h4>{{ toolDetail.toolName }}</h4>
                    </div>
                    <div class="tool-body">
                        <span>
                            {{ toolDetail.toolDescribe }}
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
const props = defineProps(['tool'])
function goHref(toolUrl: string) {
    window.open(toolUrl, "-blank")
}
</script>

<style lang="scss" scoped>
.tool {
    width: 100%;
    padding-top: 20px;

    .tool-sort {
        display: flex;

        h2 {
            font-weight: 100;
        }
    }

    .tools {
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;

        .tool-detail {
            width: 200px;
            height: 100px;
            display: flex;
            border-radius: 10px 10px 10px 10px;
            margin-top: 20px;

            .tool-left {
                display: flex;
                align-items: center;
                height: 100%;

                img {
                    width: 80px;
                    height: 80px;
                    padding: 0 15px;
                    border-radius: 50%;
                    object-fit:cover;
                    transition: all 0.7s ease-in-out;
                }
            }

            .tool-right {
                display: flex;
                box-sizing: border-box;
                align-items: center;
                justify-content: center;
                flex-direction: column;
                flex-wrap: nowrap;
                overflow: hidden;
                height: 100%;

                .tool-title {
                    width: 100%;
                }

                .tool-body {
                    -webkit-line-clamp: 2;
                    -webkit-box-orient: vertical;
                    overflow: hidden;
                    text-overflow: ellipsis;
                    position: relative;
                    line-height: 1.5;

                    span {
                        font-size: 12px;
                    }
                }
            }
        }

        .tool-detail:hover {
            .tool-left {
                display: block;

                img {
                    width: 0px;
                    height: 0px;
                    transform: scale(0);
                    object-fit: cover;
                }
            }
        }
    }

}
</style>
