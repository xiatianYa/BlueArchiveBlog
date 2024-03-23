<template>
  <div class="found_Detail box_shadow">
    <div class="found_left">
      <div class="category-time">
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
    <div class="found_right">
      <div class="category_img">
        <img src="\static\images\recommend.png" alt="回忆图片">
      </div>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style lang="scss" scoped>
.found_Detail {
  width: 100%;
  display: flex;
  flex-direction: row;
  border-radius: 10px 10px 10px 10px;
  margin-top: 30px;

  .found_left {
    display: flex;
    flex-direction: column;
    justify-content: center;
    flex: 1;

    .category-time {
      display: flex;
      flex: 1;
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

    .category_title {
      margin: 10px;
      flex: 1;
      display: flex;
      align-items: center;
      font-size: 16px;
    }

    .category_info {
      flex: 1;
      display: flex;
      flex-direction: row;
      align-items: center;
      margin: 10px;

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
      flex: 3;
      font-size: 14px;
      margin: 10px;

      span {
        text-overflow: ellipsis;
        overflow: hidden;
        display: -webkit-box;
        -webkit-line-clamp: 5;
        -webkit-box-orient: vertical;
      }
    }

    .category_target {
      display: flex;
      justify-content: space-around;
      margin: 10px;

      .bg {
        display: flex;
        justify-content: center;
        align-items: center;
        background: #bfbfbf;
        padding: 2px 10px 2px 10px;
        border-radius: 4px;
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
          font-size: 20px;
        }
      }

      .bg:hover {
        background: #fdda09;
      }
    }
  }

  .found_right {
    flex: 1;

    .category_img {
      width: 100%;
      height: 100%;
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

  }


}
</style>
