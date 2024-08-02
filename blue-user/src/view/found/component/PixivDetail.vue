<template>
  <div class="pixiv box-shadow animate__animated animate__zoomIn">
    <div class="pixiv-left">
      <div class="left-title">{{ pixiv.pixivName }}</div>
      <div class="left-info">
        <span>{{ pixiv.pixivPlay }} 播放</span> · <span>{{ pixiv.pixivDanmaku }} 弹幕</span>
      </div>
      <div class="left-type">
        <span>{{ pixiv.typeName }}</span> · <span>{{ pixiv.pixivPublish.substring(0, 4) }}</span>
      </div>
      <div class="left-shengyou">
        <span>
          <n-ellipsis :line-clamp="1">
            声优 : {{ pixiv.pixivVoiceActor }}
          </n-ellipsis>
        </span>
      </div>
      <div class="left-score">
        <span>{{ pixiv.pixivScore }}</span><span>分</span>
      </div>
      <div class="left-synopsis">

        <span><n-ellipsis :line-clamp="3">
            简介 : {{ pixiv.pixivSynopsis }}
          </n-ellipsis>
        </span>
      </div>
    </div>
    <div class="pixiv-right">
      <div class="category-img">
        <img :src="pixiv.pixivAvater">
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
const props = defineProps(['pixiv'])
import { NEllipsis } from 'naive-ui'
</script>

<style lang="scss" scoped>
.pixiv {
  width: 100%;
  display: flex;
  flex-direction: row;
  border-radius: 10px 10px 10px 10px;
  margin-top: 30px;
  max-height: 270px;
  overflow: hidden;

  .pixiv-left {
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    padding: 20px;

    .left-title {
      width: 100%;
      font-size: 18px;
    }

    .left-info {
      font-weight: 100;
      font-size: 14px;
    }

    .left-type {
      font-weight: 100;
      font-size: 14px;
    }

    .left-shengyou {
      font-weight: 100;
      font-size: 14px;
    }

    .left-score {
      font-weight: 100;
      font-size: 14px;
    }

    .left-synopsis {
      font-weight: 100;
      font-size: 14px;
    }
  }

  .pixiv-right {
    .category-img {
      display: flex;
      justify-content: end;
      width: 100%;
      height: 100%;
      border-radius: 10px;
      overflow: hidden;

      img {
        width: 300px;
        object-fit: cover;
        transition: all .3s ease-in 0s;
      }

      img:hover {
        object-fit: cover;
        transform: scale(1.1);
        transition: all 0.3s ease-in;
      }
    }
  }
}
</style>
