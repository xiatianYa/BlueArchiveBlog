<template>
  <div class="preview">
    <div class="preview_navigation">
      <div class="title">
        <svg class="icon pointer" aria-hidden="true">
              <use xlink:href="#icon-shu1"></use>
            </svg>
        <span>目录导航</span>
      </div>
      <div v-for="anchor in titles" :style="{ padding: `10px 0 10px ${anchor.indent * 20}px` }"
        @click="handleAnchorClick(anchor)">
        <a style="cursor: pointer">{{ anchor.title }}</a>
      </div>
    </div>

    <div class="preview_content">
      <v-md-editor v-model="article.content" mode="preview" height="100%" style="background: #ECEBEC;"></v-md-editor>
    </div>
  </div>

</template>

<script setup>
import {onMounted, onUpdated, ref} from 'vue'
import {useRoute} from 'vue-router'
import {getArticle} from '@/api/article'

//路由
const route = useRoute()
//锚点列表
const titles = ref([])
//文章对象
const article = ref({
  content: ""
})
onMounted(() => {
  //获取文章信息
  getArticle(route.query.sortId).then(res => {
    article.value = res.data
  }).catch(error => {

  })
})
onUpdated(() => {
  //获取文章信息
  getAnchors()
})
//获取锚点列表
function getAnchors() {
  //获取锚点操作
  const anchors = document.querySelectorAll('h1,h2,h3,h4,h5,h6');
  const titleList = Array.from(anchors).filter((title) => !!title.innerText.trim());

  if (!titleList.length) {
    titles.value = [];
    return;
  }

  const hTags = Array.from(new Set(titleList.map((title) => title.tagName))).sort();

  titles.value = titleList.map((el) => ({
    title: el.innerText,
    lineIndex: el.getAttribute('data-v-md-line'),
    indent: hTags.indexOf(el.tagName),
  }));
}
//点击锚点 到指定窗口高度
function handleAnchorClick(anchor) {
  const { lineIndex } = anchor
  const heading = document.querySelector(`[data-v-md-line="${lineIndex}"]`)
  if (heading) {
    const headingRect = heading.getBoundingClientRect();
    const offset = 0;
    window.scrollTo({
      top: headingRect.top + window.scrollX + offset,
      behavior: 'smooth'
    });
  }
}
</script>
<style lang="scss" scoped>
.preview {
  width: 100%;
  height: 100%;
  padding-top: 60px;
  padding-bottom: 40px;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  flex-wrap: nowrap;

  .preview_navigation {
    position: fixed;
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    align-items: center;
    width: 180px;
    height: auto;
    top: 100px;
    left: 40px;
    margin-left:20px;
    margin-top: 10px;
    padding: 20px 10px 10px 10px;
    font-size: 15px;
    background-color: #ECEBEC;
    border-radius: 10px;
    div{
      a{
        padding: 0px 5px 0px 5px;
        border-radius: 10px;
      }
      a:hover{
        background-color: #6EFEB9;
      }
    }
    .title{
      width: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      .icon{
        font-size: 20px;
      }
    }
  }
  .preview_content {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 60%;
    height: 100%;
    margin-top: 20px;
    border-radius: 10px;
    overflow: hidden;
  }
}
</style>
