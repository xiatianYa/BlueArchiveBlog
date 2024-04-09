<template>
  <NavView></NavView>
  <RouterView :class="routerClass" />
  <FooterView></FooterView>
  <setUpView></setUpView>
</template>
<script setup lang="ts">
import {computed, onBeforeMount, ref, watch} from 'vue'
import {RouterView, useRouter} from 'vue-router'
import NavView from '@/components/NavView.vue'
import FooterView from '@/components/FooterView.vue'
import setUpView from '@/components/SetUpView.vue'
import {useGloBalStore} from '@/store/global'

const gloBalStore = useGloBalStore()
// 使用 ref 来定义初始状态，类型推断为 boolean
const isShow = ref(true)

// 使用 computed 来根据路由状态计算 isShow 的值，避免直接修改
const routerClass = computed(() => {
  if (router.currentRoute.value.path === '/leave') {
    return !isShow.value ? '' : gloBalStore.switch ? 'bg_white' : 'bg_black'
  } else {
    return isShow.value ? '' : gloBalStore.switch ? 'bg_white' : 'bg_black'
  }
})

const router = useRouter()

// 使用 onBeforeMount 来初始化 isShow 的值，避免在 watch 中进行
onBeforeMount(() => {
  updateIsShow()
})

// 监听当前路由，更新 isShow 的值
watch(
    () => router.currentRoute.value,
    (newPath) => {
      updateIsShow()
    },
    { immediate: true }
)

// 更新 isShow 的值的函数，提高代码复用性
function updateIsShow() {
  isShow.value = router.currentRoute.value.path !== '/leave'
}

</script>
<style scoped></style>

