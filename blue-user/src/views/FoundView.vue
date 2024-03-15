<template>
  <div class="found">
    <div class="banner">
      <div class="animate__animated animate__slideInDown banner_video">
        <video autoplay loop>
          <source
              src="https://outin-eeb8d5f20db011eebcc500163e1c60dc.oss-cn-shanghai.aliyuncs.com/50bd1172d7e271ee802c5017f0f90102/025e209ea69efabe1cd56a1c73b90866-fd.mp4?Expires=1709576469&OSSAccessKeyId=LTAIxSaOfEzCnBOj&Signature=vCdvO3F%2Bwye5uSKxez3PYYplDPc%3D"
              type="video/mp4">
        </video>
        <div class="menus">
          <div class="menu pointer">
                        <span class="title">
                            追番
                        </span>
          </div>
          <div class="menu pointer">
                        <span class="title">
                            二创
                        </span>
          </div>
          <div class="menu pointer">
                        <span class="title">
                            编程工具
                        </span>
          </div>
          <div class="menu pointer">
                        <span class="title">
                            小游戏
                        </span>
          </div>
        </div>
      </div>
    </div>
    <div class="founds">
      <div class="found animate__animated animate__zoomIn">
        <div class="found_header">
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-fenlei"></use>
          </svg>
          <span>
                        学习人生
                    </span>
          <svg class="icon pointer" aria-hidden="true">
            <use xlink:href="#icon-icon-gengduo"></use>
          </svg>
        </div>
        <div class="found_body">
          <FoundDetail/>
          <FoundDetail/>
          <FoundDetail/>
          <FoundDetail/>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import FoundDetail from '@/components/FoundDetail.vue'
</script>

<style lang="scss" scoped>
.found {
  display: flex;
  flex-direction: column;
  padding-bottom: 40px;

  .banner {
    display: flex;
    justify-content: center;
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

      .menus {
        position: absolute;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 30%;
        top: 60%;
        border-radius: 25px;

        .menu {
          flex: 1;
          display: flex;
          justify-content: center;
          align-items: center;
          margin: 20px;
          height: 100%;
          background-color: rgba(34, 40, 49, 0.4);
          border-radius: 25px;
          color: #cbf1f5;

          .title {
            font-size: 22px;
          }
        }

        .menu:hover {
          color: #69EACB;
          transition: all 1s ease;
          transform: translateY(-20px);
        }
      }
    }
  }

  .founds {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    margin-top: 30px;

    .found {
      width: 65%;
      height: 100%;

      .found_header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-bottom: 5px;
        border-bottom: 1px dashed #c9d6df;

        .icon {
          font-size: 32px;
          vertical-align: -0.15em;
          fill: currentColor;
          overflow: hidden;
        }

        span {
          font-size: 18px;
        }
      }

      .found_body {
        .found_Detail:nth-child(even) {
          flex-direction: row-reverse;
        }
      }
    }
  }
}
</style>