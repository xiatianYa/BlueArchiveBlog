<template>
  <div class="preview select">
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
      <video class="video" controls v-if="article.videoUrl">
        <source :src="article.videoUrl" type="video/mp4">
      </video>
      <v-md-editor class="md" v-model="article.content" :include-level="[2]" mode="preview"
        style="background: #ECEBEC;"></v-md-editor>
    </div>
  </div>

</template>

<script setup>
import {nextTick, onMounted, ref, watch} from 'vue'
import {useRoute} from 'vue-router'
import {getArticle} from '@/api/article'
import promptMsg from "@/components/PromptBoxView"
//路由  
const route = useRoute()
//锚点列表  
const titles = ref([])
//文章对象  
const article = ref({
  content: ""
})

onMounted(async () => {
  const res = await getArticle(route.query.articleId)
  if (res.data.content) {
    article.value = res.data
  } else {
    promptMsg({ type: "warn", msg: "暂无内容!" })
  }
})

// 监听article的变化，当article改变时获取锚点列表  
watch(article, async (newVal, oldVal) => {
  if (newVal !== oldVal) {
    await nextTick() // 确保DOM更新后再获取锚点  
    getAnchors()
  }
}, { deep: true })

//获取锚点列表  
function getAnchors() {
  // 选择所有的标题元素  
  const allHeadings = document.querySelectorAll('h2,h3,h4');

  // 过滤和映射标题到所需的对象格式  
  const titlesWithValue = Array.from(allHeadings).filter((heading) => {
    const trimmedText = heading.innerText.trim();
    return trimmedText !== '';
  }).map((heading) => ({
    title: heading.innerText.trim(),
    lineIndex: heading.getAttribute('data-v-md-line'),
    indent: ['H2', 'H3','H4'].indexOf(heading.tagName.toUpperCase())
  }));

  // 设置titles.value为找到的标题数组或空数组  
  titles.value = titlesWithValue;
}

//点击锚点滚动到指定窗口高度  
function handleAnchorClick(anchor) {
  const { lineIndex } = anchor;
  const heading = document.querySelector(`[data-v-md-line="${lineIndex}"]`);
  if (heading) {
    const headingRect = heading.getBoundingClientRect();
    const offset = window.scrollY; // 使用window.scrollY获取当前滚动位置  
    window.scrollTo({
      top: headingRect.top + offset - (window.innerHeight / 2), // 滚动到标题中间位置  
      behavior: 'smooth'
    });
  }
}  
</script>
<style lang="scss" scoped>
.preview {
  height: 100%;
  padding-top: 60px;
  padding-bottom: 40px;
  min-height: 100vh;
  display: flex;

  .preview_navigation {
    width: 30%;
    padding: 20px 10px 10px 10px;
    font-size: 15px;
    border-radius: 10px;

    div {
      a {
        padding: 0px 5px 0px 5px;
        border-radius: 10px;
      }

      a:hover {
        background-color: #6EFEB9;
      }
    }

    .title {
      width: 100%;
      display: flex;
      align-items: center;
      justify-content: center;

      .icon {
        font-size: 20px;
      }
    }
  }

  .preview_content {
    width: 70%;

    .video {
      width: 100%;
      border-radius: 20px;
      margin-bottom: 20px;
    }
  }
}
</style>
