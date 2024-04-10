<template>
  <div class="menu">
    <div class="menu_left">
      <div class="nav">
        <span>导航一</span>
      </div>
      <div class="nav">
        <span>导航二</span>
      </div>
      <div class="nav">
        <span>导航三</span>
      </div>
      <div class="nav">
        <span>导航四</span>
      </div>
    </div>
    <div class="menu_right"></div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped>
.menu{
  display: flex;
  width: 100%;
  height: 1000px;
  background-color: red;
  margin-top: 53px;
  .menu_left{
    display: flex;
    flex-wrap: wrap;
    flex-direction: column;
    width: 10%;
    height: 100%;
    background-color: aqua;
    .nav{
      display: flex;
      align-items: center;
      justify-content: center;
      width: 100%;
      height: 30px;
    }
  }
  .menu_right{
    width: 90%;
    height: 100%;
    background-color: azure;
  }
}
</style>
