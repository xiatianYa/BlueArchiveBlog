<template>
  <div class="setup">
    <svg class="icon pointer" aria-hidden="true">
      <use xlink:href="#icon-shezhi"></use>
    </svg>
    <transition name="fadenum">
      <div class="setView">
        <svg class="icon pointer" aria-hidden="true" @click="goTop">
          <use xlink:href="#icon-huojian"></use>
        </svg>
        <svg class="icon pointer" aria-hidden="true" v-show="globalStore.switch"
          @click="changSwitch(!globalStore.switch)">
          <use xlink:href="#icon-deng"></use>
        </svg>
        <svg class="icon pointer" aria-hidden="true" v-show="!globalStore.switch"
          @click="changSwitch(!globalStore.switch)">
          <use xlink:href="#icon-dengpao"></use>
        </svg>
      </div>
    </transition>
  </div>
</template>
<script setup lang="ts">
import useStore from "@/store"
let { globalStore } = useStore()

function changSwitch(value: boolean) {
  globalStore.changSwitch(value)
}

function goTop() {
  window.scrollTo({ behavior: 'smooth', top: 0 });
}
</script>
<style lang="scss" scoped>
.setup {
  position: fixed;
  right: 30px;
  bottom: 40px;
  z-index: 99;

  .icon {
    width: 1em;
    height: 1em;
    vertical-align: -0.15em;
    fill: currentColor;
    overflow: hidden;
    font-size: 32px;
    animation: revolve 2s linear infinite;
  }

  .setView {
    position: fixed;
    display: flex;
    flex-direction: column;
    width: 2rem;
    right: 30px;
    bottom: 75px;
    z-index: 1;
    opacity: 0;
    transition: opacity 0.4s;
    -webkit-transition: opacity 0.4s;

    .icon {
      width: 1em;
      height: 1em;
      vertical-align: -0.15em;
      fill: currentColor;
      overflow: hidden;
      font-size: 32px;
      animation: none;
    }
  }
}

.setup:hover .setView {
  opacity: 1;
}
</style>