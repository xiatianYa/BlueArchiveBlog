<template>
  <div class="loading" v-show="gloBalStore.loading">
    <div class="alona">
      <img :src="AlonaUrl">
      <div class="wrapper">
        <h1>CONNECTIING...</h1>
        <div>{{ Percentages }}%</div>
      </div>
    </div>
  </div>
</template>
<script setup>
import {useGloBalStore} from '@/store/global'
import {onMounted, ref} from 'vue'
import {useRouter} from "vue-router"
import {useBgStore} from '@/store/bg'
import {listBg} from '@/api/bg'
//路由
const router = useRouter()
//加载阿罗那图片
const AlonaList = ref(['https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/Alona01.png', 'https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/Alona02.png', 'https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/Alona03.png', 'https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/Alona04.png'])
//当前是张图片
const AlonaUrl = ref("https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/Alona01.png")
//加载进度
const Percentages = ref(0)
//最大加载进度
const Maxpercentages = ref(0)
//定时器列表
const timer = ref([])
//全局仓库
const gloBalStore = useGloBalStore()
//背景仓库
const BgStore = useBgStore()
onMounted(() => {
  //如果没有背景则去查询背景
  if (BgStore.bgList.length <= 1) {
    listBg().then((res) => {
      BgStore.SET_BGLIST(res)
    })
    Maxpercentages.value = 100;
  } else {
    Maxpercentages.value = 20;
  }
  //定时切换阿罗那图片 1s
  timer.value.push(setInterval(() => {
    var rand = Math.random() * AlonaList.value.length | 0
    var newAlonaUrl = AlonaList.value[rand]
    if (AlonaUrl.value != newAlonaUrl) {
      AlonaUrl.value = newAlonaUrl
    } else {
      rand = Math.random() * AlonaList.value.length | 0
      AlonaUrl.value = AlonaList.value[rand]
    }
  }, 1000))
  //进度加载状态
  timer.value.push(setInterval(() => {
    if (Percentages.value < Maxpercentages.value) {
      Percentages.value += 1;
    }
    if (Percentages.value >= 100) {
      gloBalStore.changLoading(false)
      for (const timerElement of timer.value) {
        clearInterval(timerElement)
      }
      timer.value = []
      router.push({ path: "/home" })
    }
  }, 50))
})
</script>
<style lang="scss" scoped>
.loading {
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
  background: url("/static/images/loading_bg.png") no-repeat;
  background-size: 100% 100%;
  background-attachment: fixed;

  .alona {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    height: 35vh;

    img {
      width: auto;
      height: 16.6666666667rem;
      margin-bottom: 20px;
      object-fit: cover;
      animation-name: Alonadupown;
      animation-duration: 1s;
      animation-iteration-count: infinite;
    }

    .wrapper {
      display: flex;
      flex-direction: column;
      align-items: center;
      font-size: 1.2rem;
      color: #1289f9;

      h1 {
        font-size: 1.8rem;
        color: #1289f9;
      }
    }
  }
}
</style>