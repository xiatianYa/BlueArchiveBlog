<template>
  <div class="song">
    <div class="banner">
      <div class="animate__animated animate__slideInDown banner-video">
        <video id="myVideo" autoplay loop muted>
          <source :src="bgUrl" type="video/mp4">
        </video>
      </div>
    </div>
    <div class="tongzhi animate__animated animate__fadeInLeft">
      <div class="border">
        <div class="left">
          <svg class="icon pointer scaleDraw" aria-hidden="true">
            <use xlink:href="#icon-tongzhi1"></use>
          </svg>
        </div>
        <div class="right">
          <div class="content">
            <span class="title">温馨提示: 点击音乐封面进行 播放/暂停 操作</span>
          </div>
        </div>
      </div>
    </div>
    <MusicDetail :musicList="musicList" :startMusic="startMusic" class="animate__animated animate__fadeInTop"/>
    <audio id="music">
    </audio>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue"
import { bySortList } from '@/api/music'
import MusicDetail from "@/view/music/component/MusicDetail.vue";

const bgUrl = "http://bluearchive.top:9500/statics/2024/04/26/6f28756c150b49d8ba7e98d2ef052e50-f84f3d0b1e7e9da25069b3324272e29e-fd_20240426121202A002.mp4"
//音乐列表
const musicList = ref([])
//存储上一个img图片
let BeforeImg: any = ref()
onMounted(() => {
  bySortList().then((res: any) => {
    musicList.value = res;
  })
})
//播放音乐
function startMusic(event: any, musicUrl: string) {
  //获取音乐标签
  let music: any = document.getElementById("music");
  //清除img图片的旋转样式
  BeforeImg.className = ""
  //获取点击的img
  let img = event.target;
  //判断是不是点击正在播放的音乐
  if (img === BeforeImg) {
    //清除BeforeImg
    BeforeImg = {};
    //停止音乐
    music.pause();
    //返回
    return;
  }
  //停止音乐
  music.pause();
  //设置新的音乐地址
  music.src = musicUrl
  //设置img图片旋转
  img.className = "music-img rotateAll";
  //设置ImgNBefore
  BeforeImg = img
  //开始播放音乐
  music.play();
}
</script>

<style lang="scss" scoped>
.song {
  padding-bottom: 40px;

  .banner {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 400px;
    padding-top: 60px;

    .banner-video {
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
  }


  .tongzhi {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    padding-top: 60px;

    .border {
      width: 90%;
      display: flex;
      align-items: center;
      padding: 10px;
      border: 2px dashed #c9d6df;

      .left {
        height: 100%;
        width: 10%;

        .icon {
          font-size: 28px;
        }
      }

      .right {
        width: 80%;
        height: 100%;

        .content {
          width: 100%;
          height: 100%;
          display: flex;
          align-items: center;

          .title {
            padding: 0;
          }
        }
      }
    }

  }

  .container {
    width: 90%;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    flex-wrap: wrap;
    padding-top: 40px;
    margin: 0 auto;

    .title {
      width: 100%;
      padding-top: 40px;

      .icon {
        font-size: 24px;
        animation-name: rotateAll;
        /*关键帧名称*/
        animation-timing-function: linear;
        /*动画的速度曲线*/
        animation-iteration-count: infinite;
        /*动画所花费的时间*/
        animation-duration: 2s;
      }

      span {
        padding-left: 5px;
        font-size: 18px;
      }
    }


    .musics {
      display: flex;
      flex-wrap: wrap;
      max-width: 150px;
      flex: 1;

      .title {
        width: 100%;
        display: flex;
        align-items: center;
        padding: 20px 0px 0px 20px;
      }

      .music {
        display: flex;
        flex-direction: column;
        align-items: center;
        flex-wrap: wrap;
        padding-right: 30px;
        padding-top: 30px;

        .music-img {
          width: 100px;
          height: 100px;
          overflow: hidden;

          img {
            border-radius: 50%;
            max-width: 100%;
            height: auto;
            object-fit: cover;
            transition: all 1s ease-in;
          }

          img:hover {
            object-fit: cover;
            transform: rotate(360deg);
            transition: all 1s ease-in;
          }
        }

        .music-name {
          display: flex;
          align-items: center;
          justify-content: center;
          padding-top: 10px;
        }
      }
    }

  }
}
</style>
