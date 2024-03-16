<template>
  <LoadingView></LoadingView>
  <NavView></NavView>
  <RouterView v-show="!gloBalStore.loading" :class="gloBalStore.switch ? 'bg_white' : 'bg_black'"/>
  <FooterView v-show="isShow && !gloBalStore.loading"></FooterView>
  <setUpView></setUpView>
</template>
<script setup lang="ts">
import {ref, watch} from 'vue'
import {RouterView, useRouter} from 'vue-router'
import NavView from '@/views/NavView.vue'
import LoadingView from '@/components/LoadingView.vue'
import FooterView from '@/components/FooterView.vue'
import setUpView from '@/components/SetUpView.vue'
import {useGloBalStore} from '@/store/global'

let isShow: any = ref(true)
let router = useRouter()
// 监听当前路由
watch(
    () => router.currentRoute.value,
    (newPath: any) => {
      if (newPath.href == '/leave') {
        isShow.value = false;
      } else {
        isShow.value = true;
      }
    },
    {immediate: true}
)

const gloBalStore = useGloBalStore()
</script>
<style scoped>
</style>@/store/global
