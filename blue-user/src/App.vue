<template>
  <NavView v-show="navShow"></NavView>
  <RouterView :class="!isShow ? '' : gloBalStore.switch ? 'bg_white' : 'bg_black'" />
  <FooterView></FooterView>
  <setUpView></setUpView>
</template>
<script setup>
import {ref, watch} from 'vue'
import {RouterView, useRouter} from 'vue-router'
import {useGloBalStore} from '@/store/global'
import NavView from '@/components/NavView.vue'
import FooterView from '@/components/FooterView.vue'
import setUpView from '@/components/SetUpView.vue'

//全局仓库
const gloBalStore = useGloBalStore()
let isShow = ref(true)
let navShow = ref(true)
let router = useRouter()
// 监听当前路由
watch(
  () => router.currentRoute.value,
  (newPath) => {
    if (newPath.href == "/") {
      navShow.value = false;
    } else {
      navShow.value = true;
    }
    if (newPath.href == '/leave') {
      isShow.value = false;
    } else {
      isShow.value = true;
    }
  },
  { immediate: true }
)
</script>
