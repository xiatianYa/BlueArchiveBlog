<template>
    <div class="tool">
        <div class="tool_sort">
            <h2>
                {{ tool.sortName }}
            </h2>
        </div>
        <div class="tools">
            <div class="tool_detail pointer box_shadow animate__animated animate__slideInLeft" v-for="toolDetail in tool.blueProgramToolList"  @click="goHref(toolDetail.toolUrl)">
                <div class="tool_left">
                    <div class="tool_img">
                        <img :src="toolDetail.toolAvater" />
                    </div>
                </div>
                <div class="tool_right">
                    <div class="tool_title">
                        <h4>{{ toolDetail.toolName }}</h4>
                    </div>
                    <div class="tool_body">
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
    window.open(toolUrl, "_blank")
}
</script>

<style lang="scss" scoped>
.tool {
    width: 100%;
    padding-top: 20px;

    .tool_sort {
        display: flex;

        h2 {
            font-weight: 100;
        }
    }

    .tools {
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;

        .tool_detail {
            width: 32%;
            height: 100px;
            display: flex;
            border-radius: 10px 10px 10px 10px;
            margin-top: 20px;

            .tool_left {
                display: flex;
                align-items: center;
                height: 100%;

                img {
                    width: 70px;
                    height: 70px;
                    display: flex;
                    padding: 15px;
                    border-radius: 50%;
                    object-fit: cover;
                    transition: all 0.7s ease-in-out;
                }
            }

            .tool_right {
                display: flex;
                box-sizing: border-box;
                align-items: center;
                justify-content: center;
                flex-direction: column;
                flex-wrap: nowrap;
                overflow: hidden;
                height: 100%;

                .tool_title {
                    width: 100%;
                }

                .tool_body {
                    display: -webkit-box;
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

        .tool_detail:hover {
            .tool_left {
                display: block;

                img {
                    width: 0px;
                    height: 0px;
                    transform: scale(0);
                    object-fit: cover;
                }
            }

            .tool_right {}
        }
    }

}
</style>
