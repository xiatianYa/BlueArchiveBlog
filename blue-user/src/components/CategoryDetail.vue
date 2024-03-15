<template>
  <div class="category_detail box_shadow pointer">
    <div class="category_img">
      <img src="\static\images\recommend.png" alt="回忆图片">
    </div>
    <div class="category_time">
      <svg class="icon pointer" aria-hidden="true">
        <use xlink:href="#icon-rili1"></use>
      </svg>
      <span>
                2024-02-23 1:11
            </span>
    </div>
    <div class="category_title">
      碧蓝档案...........
    </div>
    <div class="category_info">
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
    <div class="category_msg">
            <span>
                基沃托斯，一片神秘而辽阔的土地，头上有着奇异光环的少女们生
                活在这里。然而，联邦理事会管理下的平静生活随着会长的不辞而
                别被打破，在群龙无首的基沃托斯，危机一触即发！
                在这样的混乱之中，会长留下的搜查社“沙勒”将扮演怎样的角色？
                从外面的世界来到基沃托斯的老师又将与少女们发生怎样的邂逅？
            </span>
    </div>
    <div class="category_target">
      <div class="bg">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-wenjianjia"></use>
        </svg>
        <span>标签1</span>
      </div>
      <div class="bg">
        <svg class="icon pointer" aria-hidden="true">
          <use xlink:href="#icon-biaoqian"></use>
        </svg>
        <span>标签2</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
</script>

<style lang="scss" scoped>
.category_detail {
  width: 30%;
  display: flex;
  flex-direction: column;
  border-radius: 10px 10px 10px 10px;

  .category_img {
    overflow: hidden;

    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      border-radius: 10px 10px 0 0;
      transition: all .3s ease-in 0s;
    }

    img:hover {
      object-fit: cover;
      transform: scale(1.1);
      transition: all 0.3s ease-in;
    }
  }

  .category_time {
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

  span {
    flex: 1;
  }

  .category_title {
    display: flex;
    align-items: center;
    font-size: 16px;
    margin: 15px 5px 15px 7px;
    flex-grow: 1;
  }

  .category_info {
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

  .category_msg {
    font-size: 12px;

    span {
      text-overflow: ellipsis;
      overflow: hidden;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
      margin: 5px 5px 5px 7px;
    }
  }

  .category_target {
    display: flex;
    padding-bottom: 5px;

    .bg {
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

    .bg:hover {
      background: #fdda09;
    }
  }
}

.category_detail:nth-child(n + 4) {
  margin-top: 20px;
}
</style>
