<template>
  <div class="song">
    <div class="banner">
      <div class="animate__animated animate__slideInDown banner_video">
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
    <div class="container" v-for="item in musicList">
      <div class="title">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-xiaofengche"></use>
        </svg>
        <span>{{ item.sortName }}</span>
      </div>
      <div class="musics" v-for="music in item.musicList" :key="music.id">
        <div class="music" @click="startMusic($event,music.musicUrl)">
          <div class="music_img">
            <img :src="music.imgUrl">
          </div>
          <div class="music_name">
            <span>{{ music.musicName }}</span>
          </div>
        </div>
      </div>
    </div>
    <audio id="music">
    </audio>
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue"
import {useBgStore} from '@/store/bg'
import {bySortList} from '@/api/music'

const bgUrl = ref(useBgStore().GET_BGLIST_BYTYPE("5"))
//音乐列表
const musicList = ref([])
//存储上一个img图片
let BeforeImg = ref({})
onMounted(() => {
  bySortList().then(res => {
    musicList.value = res;
  })
})
function startMusic(event,musicUrl) {
  //关闭视频音乐
  var video = document.getElementById("myVideo");
  // 静音视频
  video.muted = true;
  //获取音乐标签
  let music = document.getElementById("music");
  //清除img图片的旋转样式
  BeforeImg.className = ""
  //获取点击的img
  let img = event.target;
  //判断是不是点击正在播放的音乐
  if (img === BeforeImg) {
    //关闭视频音乐
    var video = document.getElementById("myVideo");
    // 静音视频
    video.muted = false;
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
  img.className = "rotateAll";
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
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    padding-top: 40px;

    .title {
      width: 90%;
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
      width: 90%;
      display: flex;
      flex-wrap: wrap;

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

        .music_img {
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

        .music_name {
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
