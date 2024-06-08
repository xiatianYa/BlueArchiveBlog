<template>
  <div class="pixiv box_shadow animate__animated animate__zoomIn">
    <div class="pixiv_left">
      <div class="left_title">{{ pixiv.pixivName }}</div>
      <div class="left_info">
        <span>{{ pixiv.pixivPlay }} 播放</span> · <span>{{ pixiv.pixivDanmaku }} 弹幕</span>
      </div>
      <div class="left_type">
        <span>{{ pixiv.typeName }}</span> · <span>{{ pixiv.pixivPublish.substring(0, 4) }}</span>
      </div>
      <div class="left_shengyou">
        <span>声优 : {{ pixiv.pixivVoiceActor }}</span>
      </div>
      <div class="left_score">
        <span>{{ pixiv.pixivScore }}</span><span>分</span>
      </div>
      <div class="left_synopsis">
        <span>简介 : {{ pixiv.pixivSynopsis }}</span>
      </div>
    </div>
    <div class="pixiv_right">
      <div class="category_img">
        <img v-lazy="pixiv.pixivAvater">
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
const props = defineProps(['pixiv'])
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

  .pixiv_left {
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    padding: 20px;

    .left_title {
      width: 100%;
      font-size: 18px;
    }

    .left_info {
      font-weight: 100;
      font-size: 14px;
    }

    .left_type {
      font-weight: 100;
      font-size: 14px;
    }

    .left_shengyou {
      font-weight: 100;
      font-size: 14px;
    }

    .left_score {
      font-weight: 100;
      font-size: 14px;
    }

    .left_synopsis {
      font-weight: 100;
      font-size: 14px;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
      text-overflow: ellipsis;
      overflow: hidden;
    }
  }

  .pixiv_right {


    .category_img {
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
