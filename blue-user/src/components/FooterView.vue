<template>
  <div class="footer">
    <div class="title">眼前所见 , 皆为奇迹</div>
    <div class="filings">
      <span>Copyright © 2024 XiaTianYa</span>
      <span>湘ICP备2023024567号</span>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style lang="scss" scoped>
.footer {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  width: 100%;
  height: 45px;
  animation: LinearGradient 15s infinite;
  border-radius: 10px 10px 0px 0px;

  .title {
    font-size: 14px;
    padding-bottom: 3px;
  }

  .filings {
    font-size: 12px;
  }

  .filings span:first-child {
    padding-right: 5px;
  }

  .filings span:last-child {
    padding-left: 5px;
  }
}
</style>
