<template>
  <div class="friend">
    <div class="banner">
      <div class="animate__animated animate__slideInDown banner_video">
        <video autoplay loop muted>
          <source
              :src="bgUrl"
              type="video/mp4">
        </video>
      </div>
    </div>
    <div class="container animate__animated animate__fadeInLeft">
      <div class="content">
        <div class="info">
          <div class="info_msg">
            <svg class="icon pointer" aria-hidden="true" @click="goDown">
              <use xlink:href="#icon-faxian"></use>
            </svg>
            <span>
              本站信息
            </span>
            <div class="msg">
              <div class="msg_name">网站名称: {{ websiteInfo.websiteName }}</div>
              <div class="msg_link">网址: {{ websiteInfo.websiteUrl }}</div>
              <div class="msg_img select">图标: {{ websiteInfo.websiteCover }}</div>
              <div class="msg_detail">描述: {{ websiteInfo.websiteDetail }}</div>
              <div class="msg_detail">备注: {{ websiteInfo.websiteRemark }}</div>
            </div>
          </div>
          <div class="apply_msg">
            <svg class="icon pointer" aria-hidden="true" @click="goDown">
              <use xlink:href="#icon-faxian"></use>
            </svg>
            <span>
              友链申请信息
            </span>
            <div class="msg">
              <div class="controller">
                接口地址: {{ FirendInfo.friendUrl }}
              </div>
              <div class="jinzhi">
                {{ FirendInfo.friendContent }}
              </div>
              <div class="tongzhi">
                {{ FirendInfo.friendDetail }}
              </div>
            </div>
          </div>
        </div>
        <div class="friends">
          <div class="friend_segmentation">
            <div class="segmentation">

            </div>
            <svg class="icon pointer" aria-hidden="true">
              <use xlink:href="#icon-xiangsu-xuehua"></use>
            </svg>
          </div>
          <div class="friend box_shadow pointer" v-for="friend in FriednList">
            <div class="friend_img">
              <img :src="friend.friendUrl">
            </div>
            <div class="friend_name">{{ friend.friendName }}</div>
            <div class="friend_detail">{{ friend.friendIntroduce }}</div>
            <div class="friend_time">
              <svg class="icon pointer" aria-hidden="true">
                <use xlink:href="#icon-shalou"></use>
              </svg>
              <span>发布于 {{ friend.pleaseTime }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue"
import {useBgStore} from '@/store/bg'
import {listFriend} from '@/api/friend'
import {listInfo} from '@/api/info'
import {listWebsite} from '@/api/website'

const bgUrl = ref(useBgStore().GET_BGLIST_BYTYPE("4"))
//友链列表
const FriednList=ref([])
//网站信息
const websiteInfo=ref({})
//友链信息
const FirendInfo=ref({})
onMounted(()=>{
  //获取友链列表
  listFriend().then(res=>{
    FriednList.value=res.rows
  })
  //获取网站信息
  listWebsite().then(res=>{
    websiteInfo.value=res.rows[0]
  })
  //获取友链信息
  listInfo().then(res=>{
    FirendInfo.value=res.rows[0]
  })
})

</script>

<style lang="scss" scoped>
.friend {
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
      width: 80%;
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
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    margin-top: 30px;

    .content {
      width: 80%;
      height: 100%;

      .info {
        display: flex;
        flex-wrap: wrap;

        .info_msg {
          display: flex;
          align-items: center;
          flex-wrap: wrap;
          width: 100%;
          margin-top: 30px;
          background-color: #ffe2e2;
          border-left: 3px solid #ff9999;
          border-radius: 10px;
          padding: 5px;

          .icon {
            vertical-align: -0.15em;
            fill: currentColor;
            overflow: hidden;
            font-size: 26px;
            padding-left: 10px;
          }

          span {
            font-size: 18px;
          }

          .msg {
            display: flex;
            flex-wrap: wrap;
            width: 100%;
            margin-top: 20px;
            margin-left: 10px;

            div {
              width: 100%;
              padding-bottom: 10px;
            }
          }
        }

        .apply_msg {
          display: flex;
          align-items: center;
          flex-wrap: wrap;
          width: 100%;
          margin-top: 30px;
          background-color: #ffe2e2;
          border-left: 3px solid #ff9999;
          border-radius: 10px;
          padding: 5px;

          .icon {
            vertical-align: -0.15em;
            fill: currentColor;
            overflow: hidden;
            font-size: 26px;
            margin-left: 5px;
          }

          span {
            font-size: 18px;
          }

          .msg {
            display: flex;
            flex-wrap: wrap;
            width: 100%;
            margin-top: 20px;
            margin-left: 10px;

            div {
              width: 100%;
              padding-bottom: 10px;
            }
          }
        }
      }

      .friends {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        flex-wrap: wrap;
        padding-top: 30px;

        .friend_segmentation {
          width: 100%;
          position: relative;
          display: flex;
          align-items: center;

          .segmentation {
            width: 100%;
            border-bottom: 4px dashed #c9d6df;
          }

          .icon {
            position: absolute;
            padding-left: 20px;
            font-size: 32px;
            overflow: hidden;
            transition: all 5s ease;
            transform: translateX(0%) rotate(360deg);
          }

          .icon:hover {
            transition: all 5s ease;
            transform: translateX(70vw) rotate(1080deg);
          }
        }

        .friend {
          display: flex;
          border-radius: 10px;
          width: 30%;
          height: 100%;
          margin-top: 40px;
          overflow: hidden;

          .friend_img {
            width: 100%;
            height: 50%;
            overflow: hidden;

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

          .friend_name {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: start;
            font-size: 18px;
            padding: 10px 0px 10px 15px;
          }

          .friend_detail {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: start;
            font-size: 14px;
            padding: 10px 0px 10px 15px;
          }

          .friend_time {
            width: 100%;
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: start;
            font-size: 12px;
            padding: 10px 0px 10px 15px;

            .icon {
              font-size: 14px;
            }
          }
        }
      }
    }
  }
}
</style>
