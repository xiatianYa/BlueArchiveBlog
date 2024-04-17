<template>
  <div class="sort_detail">
    <div class="sort_cards">
      <div class="card box_shadow  animate__animated animate__slideInUp" @click="goArticlePreview(article.id)">
        <div class="card_img">
          <img v-lazy="article.cover">
        </div>
        <div class="card_time">
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-rili1"></use>
          </svg>
          <span>
            {{ article.createTime }}
          </span>
        </div>
        <div class="sort_title">
          {{ article.articleName }}
        </div>
        <div class="sort_info">
          <div class="info">
            <svg class="icon pointer" aria-hidden="true">
              <use xlink:href="#icon-remen"></use>
            </svg>
            <span>
              0 热度
            </span>
          </div>
          <div class="info">
            <svg class="icon pointer" aria-hidden="true">
              <use xlink:href="#icon-pinglun"></use>
            </svg>
            <span>
              0 评论
            </span>
          </div>
          <div class="info">
            <svg class="icon pointer" aria-hidden="true">
              <use xlink:href="#icon-dianzan1"></use>
            </svg>
            0 点赞
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps(['article'])
import {useRouter} from 'vue-router'

const router = useRouter()
function goArticlePreview(sortId) {
  router.push({ path: '/editPreView', query: { sortId: sortId } })
}
</script>

<style lang="scss" scoped>
.sort_detail {
  width: 20%;
  display: flex;
  justify-content: center;
  align-items: center;

  .sort_cards {
    width: 80%;

    .card {
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
      box-sizing: border-box;
      margin-bottom: 30px;
      border-radius: 10px;

      .card_img {
        width: 100%;
        height: 50%;
        overflow: hidden;
        border-radius: 10px;

        img {
          width: 100%;
          height: 100%;
          object-fit: cover;
          transition: all .3s ease-in 0s;
        }

        img:hover {
          object-fit: cover;
          transform: scale(1.1);
          transition: all 0.3s ease-in;
        }
      }

      .card_time {
        width: 100%;
        display: flex;
        flex-direction: row;
        align-items: center;
        font-size: 0.6rem;
        margin: 5px;

        .icon {
          width: 1em;
          height: 1em;
          vertical-align: -0.15em;
          fill: currentColor;
          overflow: hidden;
          font-size: 22px;
        }
      }

      .sort_title {
        width: 100%;
        display: flex;
        align-items: center;
        font-size: 16px;
        margin: 7px 8px 8px 8px;
        flex-grow: 1;
      }

      .sort_info {
        width: 100%;
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
    }
  }
}
</style>
