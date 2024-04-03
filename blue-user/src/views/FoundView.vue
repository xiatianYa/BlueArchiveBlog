<template>
  <div class="found">
    <div class="banner">
      <div class="animate__animated animate__slideInDown banner_video">
        <video autoplay loop muted>
          <source :src="bgUrl" type="video/mp4">
        </video>
        <div class="menus">
          <div class="menu pointer" :class="type === 0 ? 'selectMenu' : ''" @click="selectSort(0)">
            <span class="title">
              追番
            </span>
          </div>
          <div class="menu pointer" :class="type === 1 ? 'selectMenu' : ''" @click="selectSort(1)">
            <span class="title">
              二创
            </span>
          </div>
          <div class="menu pointer" :class="type === 2 ? 'selectMenu' : ''" @click="selectSort(2)">
            <span class="title">
              编程工具
            </span>
          </div>
          <div class="menu pointer" :class="type === 3 ? 'selectMenu' : ''" @click="selectSort(3)">
            <span class="title">
              小游戏
            </span>
          </div>
        </div>
      </div>
    </div>
    <div class="founds">
      <div class="found animate__animated animate__zoomIn">
        <div class="found_header">
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-fenlei"></use>
          </svg>
          <span v-if="type === 0">
            番剧
          </span>
          <span v-if="type === 1">
            二创
          </span>
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-icon-gengduo"></use>
          </svg>
        </div>
        <div class="found_body">
          <PixivDetail :pixiv="pixiv" v-for="pixiv in pixivList" v-if="type === 0" @click="goPixiv(pixiv.id)" />
          <ErchuangDetail :erchuang="erchuang" v-if="type === 1" v-for="erchuang in erchuangList"></ErchuangDetail>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import {useBgStore} from '@/store/bg'
import {listTv} from '@/api/tv'
import {listErchuang} from '@/api/erchuang'
import {useRouter} from 'vue-router'
import ErchuangDetail from "@/components/ErchuangDetail.vue"
import PixivDetail from '@/components/PixivDetail.vue'

const bgUrl = ref(useBgStore().GET_BGLIST_BYTYPE("1"))
const router = useRouter()
const pixivList = ref()
const erchuangList = ref()
//当前发现类型 0追番 1二创 2编程工具 3小游戏
const type = ref(0)
onMounted(() => {
  init();
})
//初始化数据
function init() {
  if (type.value === 0) {
    listTv().then(res => {
      pixivList.value = res.rows
    })
  }
}
//选择导航
function selectSort(index) {
  type.value = index
  if (type.value === 0) {
    listTv().then(res => {
      pixivList.value = res.rows
    })
  } else if (type.value === 1) {
    listErchuang().then(res=>{
      erchuangList.value=res.rows
    })
  } else if (type.value === 2) {

  } else {

  }
}
//跳转路由到番剧页面
function goPixiv(pixivId) {
  router.push({ path: '/pixivView', query: { pixivId: pixivId } })
}
</script>

<style lang="scss" scoped>
.found {
  display: flex;
  flex-direction: column;
  padding-bottom: 40px;

  .banner {
    display: flex;
    justify-content: center;
    width: 100%;
    height: 400px;
    padding-top: 60px;

    .banner_video {
      width: 90%;
      height: 100%;
      border-radius: 25px;
      overflow: hidden;
      position: relative;

      video {
        width: 100%;
        height: 400px;
        object-fit: cover;
        z-index: -1;
      }

      .menus {
        position: absolute;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 30%;
        top: 60%;
        border-radius: 25px;

        .menu {
          flex: 1;
          display: flex;
          justify-content: center;
          align-items: center;
          margin: 20px;
          height: 100%;
          background-color: rgba(34, 40, 49, 0.4);
          border-radius: 25px;
          color: #cbf1f5;

          .title {
            font-size: 22px;
          }
        }

        .selectMenu {
          color: #69EACB;
        }

        .menu:hover {
          color: #69EACB;
          transition: all 1s ease;
          transform: translateY(-20px);
        }
      }
    }
  }

  .founds {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    margin-top: 30px;

    .found {
      width: 65%;
      height: 100%;

      .found_header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-bottom: 5px;
        border-bottom: 2px dashed #c9d6df;

        .icon {
          font-size: 32px;
          vertical-align: -0.15em;
          fill: currentColor;
          overflow: hidden;
        }

        span {
          font-size: 18px;
        }
      }

      .found_body {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;

        .found_Detail:nth-child(even) {
          flex-direction: row-reverse;
        }
      }
    }
  }
}
</style>