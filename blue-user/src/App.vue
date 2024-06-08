<template>
  <n-message-provider>
    <NavView v-if="navShow"></NavView>
    <RouterView :class="!isShow ? '' : gloBalStore.switch ? 'bg_white' : 'bg_black'" />
    <FooterView v-if="navShow"></FooterView>
    <setUpView></setUpView>
  </n-message-provider>
</template>
<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import { RouterView, useRouter } from 'vue-router'
import { useGloBalStore } from '@/store/global'
import NavView from '@/components/NavView.vue'
import FooterView from '@/components/FooterView.vue'
import setUpView from '@/components/SetUpView.vue'
import { NMessageProvider } from 'naive-ui'

//全局仓库
const gloBalStore = useGloBalStore()
let isShow = ref(true)
let navShow = ref(true)
let router = useRouter()
onMounted(() => {
  window.addEventListener('keydown', handleKeyDown);
})
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
function handleKeyDown(event) {
  if (event.key === 'F12') {
    // 阻止默认行为（但请注意，这可能不会总是有效）  
    event.preventDefault();
    // 阻止事件冒泡（可选）  
    event.stopPropagation();
  }
}
</script>
