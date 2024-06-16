<template>
  <n-message-provider>
    <NavView v-if="navShow"></NavView>
    <RouterView :class="!isShow ? '' : globalStore.switch ? 'bg_white' : 'bg_black'" />
    <FooterView v-if="navShow"></FooterView>
    <setUpView></setUpView>
  </n-message-provider>
</template>
<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import { RouterView, useRouter } from 'vue-router'
import { NMessageProvider } from 'naive-ui'
let { globalStore } = useStore()
import NavView from '@/components/NavView.vue'
import FooterView from '@/components/FooterView.vue'
import setUpView from '@/components/SetUpView.vue'
import useStore from "@/store"

//全局仓库
let isShow = ref(true)
let navShow = ref(true)
let router = useRouter()
onMounted(() => {
  //阻止浏览器默认行为
  window.addEventListener('keydown', handleKeyDown);
})
// 监听当前路由
watch(
  () => router.currentRoute.value,
  (newPath) => {
    if (newPath.path == "/") {
      navShow.value = false;
    } else {
      navShow.value = true;
    }
    if (newPath.path == '/leave') {
      isShow.value = false;
    } else {
      isShow.value = true;
    }
  },
  { immediate: true }
)
function handleKeyDown(event: any) {
  if (event.key === 'F12') {
    // 阻止默认行为（但请注意，这可能不会总是有效）  
    event.preventDefault();
    // 阻止事件冒泡（可选）  
    event.stopPropagation();
  }
}
</script>
