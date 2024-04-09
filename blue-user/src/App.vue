<template>
  <NavView></NavView>
  <RouterView :class="!isShow ? '' : gloBalStore.switch ? 'bg_white' : 'bg_black'" />
  <FooterView></FooterView>
  <setUpView></setUpView>
</template>
<script setup lang="ts">
import {ref, watch} from 'vue'
import {RouterView, useRouter} from 'vue-router'
import NavView from '@/components/NavView.vue'
import FooterView from '@/components/FooterView.vue'
import setUpView from '@/components/SetUpView.vue'
import {useGloBalStore} from '@/store/global'

const gloBalStore = useGloBalStore()
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
    { immediate: true }
)

</script>
<style scoped></style>

