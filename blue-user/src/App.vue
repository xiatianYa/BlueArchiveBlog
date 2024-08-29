<template>
  <n-config-provider :locale="zhCN" :date-locale="dateZhCN" :theme="globalStore.switch ? lightTheme : darkTheme">
    <n-message-provider>
      <n-dialog-provider>
        <RouterView />
      </n-dialog-provider>
      <n-global-style />
    </n-message-provider>
  </n-config-provider>
</template>
<script setup lang="ts">
import useStore from "@/store"
let { globalStore, userStore } = useStore()
import { ref, watch, onMounted, onUnmounted } from 'vue'
import { RouterView, useRouter } from 'vue-router'
import { NMessageProvider, NConfigProvider, NDialogProvider, zhCN, dateZhCN, darkTheme, lightTheme, NGlobalStyle } from 'naive-ui'
import { getUserList } from '@/api/chat'

//全局仓库
let isShow = ref(true)
//是否显示头部导航
let navShow = ref(true)
let router = useRouter()
let timer = null;
onMounted(() => {
  //阻止浏览器默认行为
  window.addEventListener('keydown', handleKeyDown);
  //如果用户已登录 并且socket未连接 则重新连接 没有则开始定时器刷新在线用户
  if (userStore.token) {
    globalStore.initSocket()
  } else {
    //创建定时任务 每15s刷新一次在线用户
    setInterval(() => {
      getUserList().then(res => {
        globalStore.onlineUserList = res.data;
      })
    }, 5000)
  }
})
onUnmounted(() => {
  if (timer) {
    clearInterval(timer)
  }
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
    // 阻止默认行为  
    event.preventDefault();
    // 阻止事件冒泡（可选）  
    event.stopPropagation();
  }
}
</script>
