<template>
  <div class="song">
    <div class="banner">
      <div class="animate__animated animate__slideInDown banner_video">
        <video autoplay loop>
          <source
              src="https://outin-eeb8d5f20db011eebcc500163e1c60dc.oss-cn-shanghai.aliyuncs.com/80702878dd3771eea6d36723a78f0102/6f28756c150b49d8ba7e98d2ef052e50-f84f3d0b1e7e9da25069b3324272e29e-fd.mp4?Expires=1709897734&OSSAccessKeyId=LTAIxSaOfEzCnBOj&Signature=a1o2P2jGG2FhClwNEukro1gDahc%3D"
              type="video/mp4">
        </video>
      </div>
    </div>
    <div class="container">
      <div class="tongzhi animate__animated animate__fadeInLeft">
        <svg class="icon pointer scaleDraw" aria-hidden="true">
          <use xlink:href="#icon-tongzhi1"></use>
        </svg>
        <div class="content">
          <span class="title">温馨提示: 点击音乐封面进行 播放/暂停 操作</span>
        </div>
      </div>
      <div class="title animate__animated animate__fadeInRight">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-xiaofengche"></use>
        </svg>
        <span>动漫音乐收藏馆</span>
      </div>
      <div class="musics animate__animated animate__fadeInUp">
        <div class="music" @click="startMusic($event)">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music" @click="startMusic($event)">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
        <div class="music">
          <div class="music_img">
            <img src="/static/images/6667438510952720.png" alt="">
          </div>
          <div class="music_name">
            <span>East if Eden</span>
          </div>
        </div>
      </div>
    </div>
    <audio id="music" src="/static/music/East of Eden-Zella Day.mp3">
    </audio>
  </div>
</template>

<script setup>
import {ref, reactive} from "vue"
//存储上一个img图片
let BeforeImg = reactive({})

function startMusic(event) {
  //获取音乐标签
  let music = document.getElementById("music");
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
  music.src = "/static/music/East of Eden-Zella Day.mp3"
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

  .container {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    padding-top: 40px;

    .tongzhi {
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: center;
      width: 90%;
      height: 100px;
      box-sizing: border-box;
      border: 1px dashed #c9d6df;
      margin-top: 20px;

      .icon {
        padding-left: 20px;
        font-size: 28px;
      }

      .content {
        width: 100%;
        height: 100%;
        display: flex;
        align-items: center;
        padding-left: 15px;

        .title {
          padding: 0;
        }
      }
    }

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
