<template>
  <div class="category-detail box-shadow pointer" @click="goArticlePreview(article.id)">
    <div class="category-img">
      <img :src="article.cover">
    </div>
    <div class="category-title">
      <n-ellipsis :line-clamp="1">
        {{ article.articleName }}
      </n-ellipsis>
    </div>
    <div class="category-msg">
      <span>
        <n-ellipsis :line-clamp="1">
          {{ article.articleDescribe }}
        </n-ellipsis>
      </span>
      <span>
        <n-ellipsis :line-clamp="1">
          作者 : {{ article.userName }}
        </n-ellipsis>
      </span>
    </div>
    <div class="category-time">
      <svg class="icon pointer" aria-hidden="true">
        <use xlink:href="#icon-rili2"></use>
      </svg>
      <span>
        <n-ellipsis :line-clamp="1">
          {{ article.createTime }}
        </n-ellipsis>
      </span>
    </div>
    <div class="category-info">
      <div class="info">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-remen"></use>
        </svg>
        <span>
          {{ article.hot }} 热度
        </span>
      </div>
      <div class="info">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-pinglun"></use>
        </svg>
        <span>
          {{ article.comment }} 评论
        </span>
      </div>
      <div class="info">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-dianzan1"></use>
        </svg>
        {{ article.like }} 点赞
      </div>
    </div>
    <div class="category-target">
      <div class="target" v-for="tag in article.tagList" @click.stop="goSort(article.sortId, tag.tagId)">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-wenjianjia"></use>
        </svg>
        <span>{{ tag.tagName }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { NEllipsis } from 'naive-ui'
const props = defineProps(['article'])
//路由
const router = useRouter()
//前往分类浏览页
function goSort(sortId: any, tagId: any) {
  router.push({ path: '/sort', query: { sortId: sortId, tagId: tagId } })
}
//前往文章浏览页
function goArticlePreview(articleId: any) {
  router.push({ path: '/editPreView', query: { articleId: articleId } })
}
</script>

<style lang="scss" scoped>
.category-detail {
  width: 30%;
  display: flex;
  flex-direction: column;
  border-radius: 10px 10px 10px 10px;

  .category-img {
    width: 100%;
    height: 170px;
    border-radius: 10px 10px 0 0;
    overflow: hidden;

    img {
      width: 100%;
      height: 170px;
      object-fit: fill;
      transition: all .3s ease-in 0s;
    }

    img:hover {
      object-fit: fill;
      transform: scale(1.1);
      transition: all 0.3s ease-in;
    }
  }

  .category-time {
    display: flex;
    flex-direction: row;
    align-items: center;
    font-size: 0.6rem;
    margin: 8px 5px 5px 5px;

    .icon {
      width: 1em;
      height: 1em;
      vertical-align: -0.15em;
      fill: currentColor;
      overflow: hidden;
      font-size: 18px;
    }

    span {
      padding-left: 3px;
    }
  }

  span {
    flex: 1;
  }

  .category-title {
    display: flex;
    align-items: center;
    font-size: 14px;
    margin: 5px 5px 5px 7px;
    flex-grow: 1;
  }

  .category-info {
    display: flex;
    flex-direction: row;
    align-items: center;
    margin: 5px 5px 5px 7px;

    .info {
      display: flex;
      align-items: center;
      justify-content: left;
      font-size: 10px;
      flex: 1;

      .icon {
        width: 1em;
        height: 1em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
        font-size: 16px;
      }
    }
  }

  .category-msg {
    display: flex;
    font-size: 12px;

    span {
      text-overflow: ellipsis;
      overflow: hidden;
      display: -webkit-box;
      -webkit-line-clamp: 1;
      -webkit-box-orient: vertical;
      margin: 5px 5px 5px 7px;
    }
  }

  .category-target {
    display: flex;
    padding-bottom: 5px;

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

.category-detail:nth-child(n + 4) {
  margin-top: 20px;
}
</style>
