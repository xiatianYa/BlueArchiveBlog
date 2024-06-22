<template>
  <div class="preview select">
    <div class="preview-info">
      <div class="title">
        {{ article.articleName }}
      </div>
      <div class="info">
        <span>
          作者 : {{ article.userName }}
        </span>
        <span>
          发表时间 : {{ article.createTime }}
        </span>
      </div>
      <div class="count">
        <span>
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-remen"></use>
          </svg>
          {{ article.hot }} 热度
        </span>
        <span>
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-pinglun"></use>
          </svg>
          {{ article.comment }} 评论
        </span>
        <span>
          <svg class="icon pointer" aria-hidden="true" @click="addArticleLike">
            <use xlink:href="#icon-dianzan"></use>
          </svg>
          {{ article.like }} 点赞
        </span>
      </div>
      <div class="tags">
        <div class="target" v-for="tag in article.tagList" @click="goSort(article.sortId, tag.tagId)">
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-wenjianjia"></use>
          </svg>
          <span>{{ tag.tagName }}</span>
        </div>
      </div>
    </div>
    <div class="preview-navigation">
      <div class="navigation">
        <n-anchor affix :show-rail="showRail" :show-background="showBackground" style="width: 100%;">
          <div class="title">
            <svg class="icon pointer" aria-hidden="true">
              <use xlink:href="#icon-shu1"></use>
            </svg>
            <span>目录导航</span>
          </div>
          <n-anchor-link :title="anchor.title" v-for="anchor in titles" @click="handleAnchorClick(anchor)" />
        </n-anchor>
      </div>
    </div>
    <div class="preview-box">
      <video class="video" controls v-if="article.videoUrl">
        <source :src="article.videoUrl" type="video/mp4">
      </video>
      <v-md-editor class="md" v-model="article.content" :include-level="[2]" mode="preview"></v-md-editor>
    </div>
    <CommentDetail style="width: 90%;margin: 0 auto;" :comment-type="2" :common-id="article.id" />
  </div>

</template>

<script setup>
import { nextTick, onMounted, ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getArticle, addLike } from '@/api/article'
import CommentDetail from '@/components/CommentDetail.vue';
import { useMessage, NAnchor, NAnchorLink } from 'naive-ui'
//提示框
const message = useMessage()
//路由信息
const route = useRoute()
//路由导航
const router = useRouter()
//锚点列表  
const titles = ref([])
//文章对象  
const article = ref({
  content: ""
})
//导航栏对象
const showRail = ref(true)
const showBackground = ref(true)


onMounted(async () => {
  init();
})
//初始化
function init() {
  //初始话文章ID
  article.value.id = route.query.articleId
  getArticle(route.query.articleId).then(res => {
    if (res.data.content) {
      article.value = res.data
    } else {
      message.warning("暂无内容!")
    }
  })
}
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
    indent: ['H2', 'H3'].indexOf(heading.tagName.toUpperCase())
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
//文章点赞
function addArticleLike() {
  addLike(article.value.id).then(res => {
    message.success(res.msg)
    //数据初始化
    init()
  }).catch((error) => {
    message.error(error)
  })
}
//前往分类浏览页
function goSort(sortId, tagId) {
  router.push({ path: '/sort', query: { sortId: sortId, tagId: tagId } })
}
// 监听article的变化，当article改变时获取锚点列表  
watch(article, async (newVal, oldVal) => {
  if (newVal !== oldVal) {
    await nextTick() // 确保DOM更新后再获取锚点  
    getAnchors()
  }
}, { deep: true })
</script>
<style lang="scss" scoped>
.preview {
  display: flex;
  height: 100%;
  padding-top: 60px;
  padding-bottom: 40px;
  min-height: 100vh;
  flex-wrap: wrap;

  .preview-info {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 200px;

    .title {
      font-weight: 100;
      font-size: 24px;
    }

    .info {
      margin-top: 15px;
      font-size: 14px;

      span {
        margin-right: 5px;
      }
    }

    .count {
      margin-top: 15px;
      font-size: 14px;

      span {
        margin-right: 5px;
      }
    }

    .tags {
      display: flex;
      margin-top: 10px;

      .target {
        display: flex;
        justify-content: center;
        align-items: center;
        background: #bfbfbf;
        margin: 5px 5px 5px 8px;
        border-radius: 4px;
        padding: 2px;
        font-size: 12px;
        transition: 0.5s ease;

        span {
          padding: 0 3px 0 3px;
        }

        .icon {
          width: 1em;
          height: 1em;
          vertical-align: -0.15em;
          fill: currentColor;
          overflow: hidden;
          font-size: 16px;
        }
      }

      .target:hover {
        background: #fdda09;
      }
    }
  }

  .preview-navigation {
    width: 20%;
    box-sizing: border-box;

    .navigation {
      width: 100%;
      height: 100%;
      padding: 40px;
      display: flex;
      justify-content: center;

      .title {
        display: flex;
        justify-content: center;
        align-items: center;
      }
    }
  }

  .preview-box {
    box-sizing: border-box;
    width: 80%;

    .video {
      width: 100%;
      border-radius: 20px;
      margin-bottom: 20px;
    }
  }
}
</style>
