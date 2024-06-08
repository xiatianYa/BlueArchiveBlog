<template>
  <div class="found">
    <div class="banner">
      <div class="animate__animated animate__slideInDown bg">
        <div class="bg_img">
          <img :src="bgUrl">
        </div>
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
        </div>
      </div>
    </div>
    <div class="founds">
      <div class="found animate__animated animate__zoomIn">
        <div class="found_header">
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-fenlei"></use>
          </svg>
          <span v-show="type === 0">
            番剧
          </span>
          <span v-show="type === 1">
            二创
          </span>
          <span v-show="type === 2">
            编程工具
          </span>
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-icon-gengduo"></use>
          </svg>
        </div>
        <div class="found_body">
          <PixivDetail v-show="type === 0" :pixiv="pixiv" v-for="pixiv in pixivList" @click="goPixiv(pixiv.id)" />
          <ErchuangDetail v-show="type === 1" :erchuang="erchuang" v-for="erchuang in erchuangList" />
          <ToolDetail v-show="type === 2" :tool="tool" v-for="tool in toolList" />
        </div>
      </div>
    </div>
    <Loading v-show="loading" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted, ref } from 'vue'
import { listTv } from '@/api/tv'
import { listErchuang } from '@/api/erchuang'
import { listToolBySort } from '@/api/tool'
import { useRouter } from 'vue-router'
import { useBgStore } from '@/store/bg'
import Loading from '@/components/CssLoadingView01.vue'
import ErchuangDetail from "@/components/ErchuangDetail.vue"
import PixivDetail from '@/components/PixivDetail.vue'
import ToolDetail from '@/components/ToolDetail.vue'
//背景视频
const bgUrl = ref(useBgStore().GET_BGLIST_BYTYPE("1") || "http://47.113.197.48:9500/statics/2024/04/26/Untitled video - Made with Clipchamp (4)_20240426122822A006.mp4")
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
      isLastPage(res.total)
      pixivList.value = res.rows
    })
  }
}
//选择导航
function selectSort(index) {
  //点击后回到顶部
  window.scrollTo({ behavior: 'smooth', top: 0 });
  type.value = index;
  //清除加载
  loading.value = false;
  //清除到底标记
  loadingEnd.value = false;
  //清除分页数据
  queryParam.value = {
    pageNum: 1,
    pageSize: 6,
  }
  //番剧
  if (type.value === 0) {
    listTv(queryParam.value).then(res => {
      isLastPage(res.total)
      pixivList.value = res.rows
    })
    //二创
  } else if (type.value === 1) {
    listErchuang(queryParam.value).then(res => {
      isLastPage(res.total)
      erchuangList.value = res.rows
    })
    //编程工具
  } else if (type.value === 2) {
    listToolBySort().then(res => {
      toolList.value = res.rows
    })
  }
}
//跳转路由到番剧页面
function goPixiv(pixivId) {
  router.push({ path: '/pixivView', query: { pixivId: pixivId } })
}
//加载数据
function loadData() {
  if (type.value === 0) {
    //开始加载
    loading.value = true
    queryParam.value.pageNum += 1;
    listTv(queryParam.value).then(res => {
      isLastPage(res.total)
      for (const item of res.rows) {
        pixivList.value.push(item)
      }
      loading.value = false;
    })
  } else if (type.value === 1) {
    //开始加载
    loading.value = true
    queryParam.value.pageNum += 1;
    listErchuang(queryParam.value).then(res => {
      isLastPage(res.total)
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
  const foundBody = document.querySelector('.found_body') // 获取滚动容器
  const banner = document.querySelector('.banner') // 获取前面高度容器
  if (isScrolledToBottom(foundBody, banner)) {
    console.log("加载数据");
    loadData()
  }
}
//判断是否滚动到底部
function isScrolledToBottom(foundBody, banner) {
  const clientHeight = foundBody.clientHeight; // 容器的视口高度  
  const windowY = window.scrollY; // 浏览器窗口高度
  //当前窗口高度 高于滚动窗口高度 并且 loading不是加载中
  return windowY >= clientHeight - banner.clientHeight && !loading.value && !loadingEnd.value;
}
//判断是不是到最后一页了
function isLastPage(total) {
  // 计算总页数  
  var totalPages = Math.ceil(total / queryParam.value.pageSize);
  // 如果当前页码等于总页数，那么就是最后一页  
  if (queryParam.value.pageNum >= totalPages) {
    loadingEnd.value = true;
  }
}
</script>

<style lang="scss" scoped>
.found {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  padding-bottom: 40px;

  .banner {
    display: flex;
    justify-content: center;
    width: 100%;
    height: 400px;
    padding-top: 60px;

    .bg {
      width: 90%;
      height: 100%;
      border-radius: 25px;
      overflow: hidden;
      position: relative;

      .bg_img {
        width: 100%;
        height: 100%;

        img {
          width: 100%;
          height: 100%;
          object-fit: cover;
        }
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
        width: 100%;
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
        flex-grow: 1;
        justify-content: space-between;

        .found_Detail:nth-child(even) {
          flex-direction: row-reverse;
        }
      }
    }
  }
}
</style>