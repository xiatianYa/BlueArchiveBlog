<template>
  <div class="photo">
    <div class="banner">
      <div class="animate__animated animate__slideInDown banner_video">
        <video autoplay loop>
          <source
              src="https://outin-eeb8d5f20db011eebcc500163e1c60dc.oss-cn-shanghai.aliyuncs.com/f0084257dcb971eeacf96733a68f0102/0b08c99240c44092ba518281c55e322e-a3a62dda6c4fa75ad613f959a3f67689-fd.mp4?Expires=1709843649&OSSAccessKeyId=LTAIxSaOfEzCnBOj&Signature=bUijZmQDS22FrEDuIpkamXiM6h8%3D"
              type="video/mp4">
        </video>
        <div class="banner_nav">
          <div class="top">
            <span class="top_link">
              www.baidu.com
            </span>
            <span class="top_title">
              记忆照片
            </span>
            <span class="top_msg">
              时光定格美好,记忆永存快乐
            </span>
          </div>
          <div class="botton">
            <div class="target pointer scaleDraw">
              <svg class="icon pointer" aria-hidden="true" @click="goDown">
                <use xlink:href="#icon-sekuaibiaoqian"></use>
              </svg>
              <span>
                美好时刻
              </span>
            </div>
            <div class="target pointer">
              <svg class="icon pointer" aria-hidden="true" @click="goDown">
                <use xlink:href="#icon-sekuaibiaoqian"></use>
              </svg>
              <span>
                美好时刻
              </span>
            </div>
            <div class="target pointer">
              <svg class="icon pointer" aria-hidden="true" @click="goDown">
                <use xlink:href="#icon-sekuaibiaoqian"></use>
              </svg>
              <span>
                美好时刻
              </span>
            </div>
            <div class="target pointer">
              <svg class="icon pointer" aria-hidden="true" @click="goDown">
                <use xlink:href="#icon-sekuaibiaoqian"></use>
              </svg>
              <span>
                美好时刻
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="photos animate__animated animate__zoomIn">
      <div class="photo box_shadow pointer">
        <div class="photo_img">
          <img class="box_shadow" src="\static\images\recommend.png" alt="">
        </div>
        <div class="photo_msg">
          <span>
            长沙
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            2024-03-07
          </span>
        </div>
      </div>
      <div class="photo box_shadow">
        <div class="photo_img">
          <img class="box_shadow" src="\static\images\recommend.png" alt="">
        </div>
        <div class="photo_msg">
          <span>
            长沙
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            2024-03-07
          </span>
        </div>
      </div>
      <div class="photo box_shadow">
        <div class="photo_img">
          <img class="box_shadow" src="\static\images\recommend.png" alt="">
        </div>
        <div class="photo_msg">
          <span>
            长沙
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            2024-03-07
          </span>
        </div>
      </div>
      <div class="photo box_shadow">
        <div class="photo_img">
          <img class="box_shadow" src="\static\images\recommend.png" alt="">
        </div>
        <div class="photo_msg">
          <span>
            长沙
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            2024-03-07
          </span>
        </div>
      </div>
      <div class="photo box_shadow">
        <div class="photo_img">
          <img class="box_shadow" src="\static\images\recommend.png" alt="">
        </div>
        <div class="photo_msg">
          <span>
            长沙
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            2024-03-07
          </span>
        </div>
      </div>
      <div class="photo box_shadow">
        <div class="photo_img">
          <img class="box_shadow" src="\static\images\recommend.png" alt="">
        </div>
        <div class="photo_msg">
          <span>
            长沙
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            2024-03-07
          </span>
        </div>
      </div>
      <div class="photo box_shadow">
        <div class="photo_img">
          <img class="box_shadow" src="\static\images\recommend.png" alt="">
        </div>
        <div class="photo_msg">
          <span>
            长沙
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            2024-03-07
          </span>
        </div>
      </div>
      <div class="photo box_shadow">
        <div class="photo_img">
          <img class="box_shadow" src="\static\images\recommend.png" alt="">
        </div>
        <div class="photo_msg">
          <span>
            长沙
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            2024-03-07
          </span>
        </div>
      </div>
      <div class="photo box_shadow">
        <div class="photo_img">
          <img class="box_shadow" src="\static\images\recommend.png" alt="">
        </div>
        <div class="photo_msg">
          <span>
            长沙
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            2024-03-07
          </span>
        </div>
      </div>
      <div class="photo box_shadow">
        <div class="photo_img">
          <img class="box_shadow" src="\static\images\recommend.png" alt="">
        </div>
        <div class="photo_msg">
          <span>
            长沙
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            2024-03-07
          </span>
        </div>
      </div>
      <div class="photo box_shadow">
        <div class="photo_img">
          <img class="box_shadow" src="\static\images\recommend.png" alt="">
        </div>
        <div class="photo_msg">
          <span>
            长沙
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            2024-03-07
          </span>
        </div>
      </div>
      <div class="photo box_shadow">
        <div class="photo_img">
          <img class="box_shadow" src="\static\images\recommend.png" alt="">
        </div>
        <div class="photo_msg">
          <span>
            长沙
          </span>
        </div>
        <div class="photo_time">
          <svg class="icon pointer" aria-hidden="true" @click="goDown">
            <use xlink:href="#icon-shalou"></use>
          </svg>
          <span>
            2024-03-07
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {} from "vue"
</script>

<style lang="scss" scoped>
.photo {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-bottom: 40px;

  .banner {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 400px;
    padding-top: 60px;

    .banner_video {
      width: 90%;
      height: 100%;
      border-radius: 25px;
      overflow: hidden;
      position: relative;

      video {
        width: 100%;
        height: 400px;
        object-fit: cover;
        z-index: -1;
      }
    }

    .banner_nav {
      position: absolute;
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
      width: 25%;
      height: 100%;
      right: 0;
      top: 0%;

      .top {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        align-items: center;
        color: #222831;

        .top_link {
          font-size: 14px;
        }

        .top_title {
          font-size: 26px;
        }

        .top_msg {
          font-size: 14px;
        }

        span {
          width: 100%;
        }
      }

      .botton {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: space-between;

        .target {
          box-sizing: border-box;
          display: flex;
          justify-content: center;
          width: 40%;
          max-height: 30px;
          border-radius: 5px;
          background-color: #f08a5d;

          .icon {
            vertical-align: -0.15em;
            fill: currentColor;
            overflow: hidden;
            font-size: 26px;
            height: 100%;
          }

          span {
            display: flex;
            align-items: center;
          }
        }

        .target:hover {
          transition: all ease 0.5s;
          background-color: #e23e57;
        }
      }
    }
  }

  .photos {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    width: 90%;
    margin-top: 30px;

    .photo {
      box-sizing: border-box;
      width: 23%;
      height: 400px;
      border-radius: 10px;
      margin-bottom: 30px;

      .photo_msg {
        display: flex;
        align-items: center;
        width: 80%;
        height: 10%;

        span {
          font-size: 16px;
        }
      }

      .photo_img {
        display: flex;
        justify-content: center;
        width: 80%;
        height: 80%;
        overflow: hidden;
        border-radius: 10px;
        margin-top: 10px;

        img {
          width: 100%;
          height: 100%;
          object-fit: cover;
          transition: all .3s ease-in 0s;
          border-radius: 10px;
        }

        img:hover {
          object-fit: cover;
          transform: scale(1.1);
          transition: all 0.3s ease-in;
        }
      }

      .photo_time {
        .icon {
          font-size: 14px;
        }

        display: flex;
        align-items: center;
        width: 80%;
        height: 10%;
        font-size: 14px;
        color: #bfbfbf;
      }
    }
  }
}
</style>
