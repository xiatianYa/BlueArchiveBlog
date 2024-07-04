<template>
  <div class="footer">
    <div class="title">眼前所见 , 皆为奇迹</div>
    <div class="filings">
      <span>Copyright © 2024 XiaTianYa</span>
      <img src="@/assets/images/badge.png">
      <span>湘ICP备2024066741号-1</span>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style lang="scss" scoped>
.footer {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  width: 100%;
  height: 45px;
  border-radius: 5px 5px 0 0;
  animation: LinearGradient 15s infinite;

  .title {
    width: 100%;
    text-align: center;
  }

  .filings {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;

    img {
      padding: 0 5px 0 10px;
      width: 15px;
      height: 15px;
    }
  }
}
</style>