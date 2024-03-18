<template>
    <div class="prompt_box">
        <div class="box">
            你好 我是一个提示框
        </div>
    </div>
</template>

<script setup>
import { } from "vue"
</script>

<style lang="scss" scoped>
.prompt_box {
    width: 200px;
    height: 100px;
    z-index: 10;
    position: fixed;
    top: 25%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: red;
    animation-name: moveTop;
    animation-duration: 1s;
    animation-fill-mode:initial;
    .box{
        display: flex;
        justify-content: center;
        align-items: center;
    }
}
</style>
