<template>
  <div class="sort-detail">
    <div class="sort-cards">
      <div class="card box-shadow  animate__animated animate__slideInUp">
        <div class="card-img">
          <img :src="article.cover">
        </div>
        <div class="card-time">
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-rili1"></use>
          </svg>
          <span>
            {{ article.createTime }}
          </span>
        </div>
        <div class="sort-title">
          {{ article.articleName }}
        </div>
        <div class="sort-info">
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
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
const props = defineProps(['article'])
</script>

<style lang="scss" scoped>
.sort-detail {
  width: 20%;
  display: flex;
  justify-content: center;
  align-items: center;

  .sort-cards {
    width: 80%;

    .card {
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
      box-sizing: border-box;
      margin-bottom: 30px;
      border-radius: 10px;

      .card-img {
        width: 100%;
        height: 150px;
        overflow: hidden;
        border-radius: 10px;

        img {
          width: 100%;
          height: 150px;
          object-fit: fill;
          transition: all .3s ease-in 0s;
        }

        img:hover {
          object-fit: fill;
          transform: scale(1.1);
          transition: all 0.3s ease-in;
        }
      }

      .card-time {
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

      .sort-title {
        width: 100%;
        display: flex;
        align-items: center;
        font-size: 14px;
        margin: 7px 8px 8px 8px;
        flex-grow: 1;
      }

      .sort-info {
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
