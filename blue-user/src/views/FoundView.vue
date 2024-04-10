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
          <span v-if="type === 2">
            编程工具
          </span>
          <span v-if="type === 3">
            小游戏
          </span>
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-icon-gengduo"></use>
          </svg>
        </div>
        <div class="found_body">
          <PixivDetail v-if="type === 0" :pixiv="pixiv" v-for="pixiv in pixivList" @click="goPixiv(pixiv.id)" />
          <ErchuangDetail v-if="type === 1" :erchuang="erchuang" v-for="erchuang in erchuangList"></ErchuangDetail>
          <ToolDetail v-if="type === 2" :tool="tool" v-for="tool in toolList"></ToolDetail>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, onUnmounted, ref} from 'vue'
import {useBgStore} from '@/store/bg'
import {listTv} from '@/api/tv'
import {listErchuang} from '@/api/erchuang'
import {listToolBySort} from '@/api/tool'
import {useRouter} from 'vue-router'
import ErchuangDetail from "@/components/ErchuangDetail.vue"
import PixivDetail from '@/components/PixivDetail.vue'
import ToolDetail from '@/components/ToolDetail.vue'
//背景视频
const bgUrl = ref(useBgStore().GET_BGLIST_BYTYPE("1"))
//路由
const router = useRouter()
//番剧列表
const pixivList = ref()
//二创列表
const erchuangList = ref()
//工具列表
const toolList = ref()
//当前发现类型 0追番 1二创 2编程工具 3小游戏
const type = ref(0)
// 查询参数
const queryParam = ref({
  pageNum: 1,
  pageSize: 6,
})
//是否滚动到底部
const loadingEnd = ref(false)
//是否加载中
const loading = ref(false)
//加载数据
const loadData = () => {
  loading.value = true
  if (type.value === 0 && !loadingEnd.value) {
    queryParam.value.pageNum += 1;
    listTv(queryParam.value).then(res => {
      if (isLastPage(res.total)) {
        loadingEnd.value = true
      }
      for (const item of res.rows) {
        pixivList.value.push(item)
      }
      loading.value = false;
    })
  } else if (type.value === 1 && !loadingEnd.value) {
    queryParam.value.pageNum += 1;
    listErchuang(queryParam.value).then(res => {
      if (isLastPage(res.total)) {
        loadingEnd.value = true
      }
      for (const item of res.rows) {
        erchuangList.value.push(item)
      }
      loading.value = false;
    })
  }
}
//滚动事件
const handleScroll = () => {
  // 监听滚动事件
  const container = document.querySelector('.found_body') // 获取滚动容器
  if (isScrolledToBottom(container)) {
    loadData()
  }
}
//判断是否滚动到底部
function isScrolledToBottom(container) {
  const clientHeight = container.clientHeight; // 容器的视口高度  
  const windowY = window.scrollY; // 浏览器窗口高度
  //当前窗口高度 高于滚动窗口高度 并且 loading不是加载中
  return windowY >= clientHeight - 50 && !loading.value;
}
//判断是不是到最后一页了
function isLastPage(total) {
  // 计算总页数  
  var totalPages = Math.ceil(total / queryParam.value.pageSize);
  console.log(totalPages);
  // 如果当前页码等于总页数，那么就是最后一页  
  return queryParam.value.pageNum === totalPages;
}
onMounted(() => {
  init();
  window.addEventListener('scroll', handleScroll)
})
onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll)
})
//初始化数据
function init() {
  if (type.value === 0) {
    listTv(queryParam.value).then(res => {
      pixivList.value = res.rows
    })
  }
}
//选择导航
function selectSort(index) {
  type.value = index
  if (type.value === 0) {
    listTv(queryParam.value).then(res => {
      pixivList.value = res.rows
    })
  } else if (type.value === 1) {
    listErchuang(queryParam.value).then(res => {
      erchuangList.value = res.rows
    })
  } else if (type.value === 2) {
    listToolBySort().then(res => {
      toolList.value = res.rows
    })
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