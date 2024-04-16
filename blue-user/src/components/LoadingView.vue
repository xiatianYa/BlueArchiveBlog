<template>
  <div class="loading" v-show="gloBalStore.loading">
    <div class="alona">
      <img v-lazy="AlonaUrl">
      <div class="wrapper">
        <h1>CONNECTIING...</h1>
        <div>{{ Percentages }}%</div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import {useGloBalStore} from '@/store/global'
import {onMounted, ref} from 'vue'
import {useRouter} from "vue-router";
//路由
const router = useRouter()
let AlonaList = ref(['/static/images/Alona01.png', '/static/images/Alona02.png', '/static/images/Alona03.png', '/static/images/Alona04.png'])
let AlonaUrl = ref("/static/images/Alona01.png")
let Percentages = ref(0)
const timer: any = ref([])
const gloBalStore = useGloBalStore()
onMounted(() => {
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
  timer.value.push(setInterval(() => {
    if (Percentages.value >= 100) {
      gloBalStore.changLoading(false)
      for (const timerElement of timer.value) {
        clearInterval(timerElement)
      }
      timer.value = []
      router.push({ path: "/home" })
    }
    Percentages.value += 1;
  }, 10))
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