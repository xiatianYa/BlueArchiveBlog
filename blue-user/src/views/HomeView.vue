<template>
  <div class="home_box">
    <div class="animate__animated animate__slideInDown video_bg">
      <video autoplay class="video-background" loop muted>
        <source
            :src="bgUrl || 'http://127.0.0.1:9300/statics/2024/03/17/a7aa0c43e9d5812543f5924181bc6132-fd_20240317155200A005.mp4'"
            type="video/mp4">
      </video>
    </div>
    <div class="home_center_box">
      <div class="wave">
        <svg class="waves" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
             viewBox="0 24 150 28" preserveAspectRatio="none" shape-rendering="auto">
          <defs>
            <path id="gentle-wave" d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z"/>
          </defs>
          <g class="parallax">
            <use xlink:href="#gentle-wave" x="48" y="0" fill="rgba(255,255,255,0.7"/>
            <use xlink:href="#gentle-wave" x="48" y="3" fill="rgba(255,255,255,0.5)"/>
            <use xlink:href="#gentle-wave" x="48" y="5" fill="rgba(255,255,255,0.3)"/>
            <use xlink:href="#gentle-wave" x="48" y="7" fill="#fff"/>
          </g>
        </svg>
      </div>
      <div class="typewriter animation_writer">
        <span>眼 前 所 见 ， 皆 为 奇 迹 .</span>
      </div>
      <div class="down pointer">
        <svg class="icon pointer" aria-hidden="true" @click="goDown">
          <use xlink:href="#icon-down"></use>
        </svg>
      </div>
      <div class="home_content">
        <div class="content_left animate__animated animate__slideInLeft">
          <div class="content_info box_shadow box_radius">
            <div class="info_backrougnd">
              <img src="\static\images\info.jpg" alt="背景">
            </div>
            <div class="info_avater">
              <a href="#">
                <span>
                  <img src="https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater45.png"
                       alt="背景">
                </span>
              </a>
              <div class="info_name">
                夏天
              </div>
            </div>
            <div class="info_common">
              <span class="common">
                <span>文章</span>
                <span>0</span>
              </span>
              <span class="common">
                <span>分类</span>
                <span>0</span>
              </span>
              <span class="common">
                <span>访问量</span>
                <span>0</span>
              </span>
            </div>
            <div class="info_link">
              <ul>
                <li>
                  <svg class="icon pointer" aria-hidden="true">
                    <use xlink:href="#icon-icon_bilibili"></use>
                  </svg>
                </li>
                <li>
                  <svg class="icon pointer" aria-hidden="true">
                    <use xlink:href="#icon-icon-github"></use>
                  </svg>
                </li>
              </ul>
            </div>
          </div>
          <div class="content_search box_shadow box_radius">
            <div class="search">
              <div class="search_title">
                搜索
              </div>
              <div class="search_box">
                <input type="text" placeholder="搜索文章" class="search_txt">
                <svg class="icon pointer" aria-hidden="true">
                  <use xlink:href="#icon-daohang"></use>
                </svg>
              </div>
            </div>
          </div>
          <div class="content_recommend box_shadow box_radius">
            <div class="recommend">
              <div class="recommend_row recommend_title">
                <svg class="icon" aria-hidden="true">
                  <use xlink:href="#icon-remaituijian"></use>
                </svg>
                <p>
                  推荐文章
                </p>
              </div>
              <div class="recommend_row">
                <div class="recommend_message pointer">
                  <div class="recommend_img">
                    <img src="\static\images\recommend.png" alt="背景"/>
                  </div>
                  <span>碧蓝档案</span>
                </div>
                <div class="recommend_time">
                  <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-rili"></use>
                  </svg>
                  <p>2024-02-20 20:47</p>
                </div>
              </div>
              <div class="recommend_row">
                <div class="recommend_message pointer">
                  <div class="recommend_img">
                    <img src="\static\images\recommend.png" alt="背景"/>
                  </div>
                  <span>碧蓝档案</span>
                </div>
                <div class="recommend_time">
                  <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-rili"></use>
                  </svg>
                  <p>2024-02-20 20:47</p>
                </div>
              </div>
              <div class="recommend_row">
                <div class="recommend_message pointer">
                  <div class="recommend_img">
                    <img src="\static\images\recommend.png" alt="背景"/>
                  </div>
                  <span>碧蓝档案</span>
                </div>
                <div class="recommend_time">
                  <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-rili"></use>
                  </svg>
                  <p>2024-02-20 20:47</p>
                </div>
              </div>
            </div>
          </div>
          <div class="content_browse">
            <div class="browse box_radius pointer  box_shadow ">
              <span>
                分类
              </span>
              <span>
                <p>学</p>习人生
              </span>
              <span>
                活到老,学到老
              </span>
            </div>
          </div>
          <div class="content_consultation box_shadow">

          </div>
        </div>
        <div class="content_right ">
          <div class="tongzhi animate__animated animate__slideInRight">
            <svg class="icon pointer scaleDraw" aria-hidden="true">
              <use xlink:href="#icon-tongzhi1"></use>
            </svg>
            <div class="content">
              <span class="title">{{ noticeInfo.noticeTitle }}</span>
              <span class="body">{{ noticeInfo.noticeContent }}</span>
            </div>
          </div>
          <div class="container">
            <div class="category animate__animated animate__zoomIn">
              <div class="category_header">
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
              <div class="category_body">
                <div class="category_list">
                  <CategoryDetail/>
                  <CategoryDetail/>
                  <CategoryDetail/>
                  <CategoryDetail/>
                  <CategoryDetail/>
                  <CategoryDetail/>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import {onMounted, ref} from 'vue'
import CategoryDetail from '@/components/CategoryDetail.vue'
import {useBgStore} from '@/store/bg'
import {listNotice} from '@/api/notice'

const noticeInfo=ref({})
const bgUrl = ref(useBgStore().GET_BGLIST_BYTYPE("0"))
onMounted(()=>{
  listNotice().then(res=>{
    noticeInfo.value=res.rows[0]
  })
})
function goDown() {
  let dom = document.documentElement;
  window.scrollTo({behavior: 'smooth', top: dom.scrollHeight});
}
</script>
<style lang="scss" scoped>
.home_box {
  width: 100%;
  height: auto;
  overflow: hidden;
  background: url("/static/images/loading_bg.png") no-repeat;
  background-size: 100% 100%;
  background-attachment: fixed;
  padding-bottom: 40px;

  .video_bg {
    width: 100%;
    height: 100vh;

    video {
      width: 100%;
      height: 100%;
      object-fit: cover;
      z-index: -1;
    }
  }

  .home_center_box {
    display: flex;
    width: 100%;
    position: relative;

    .typewriter {
      z-index: 1;
      position: absolute;
      width: 0;
      left: 50%;
      top: -35%;
      transform: translate(-50%, 0%);
      margin-left: 18px;
      span {
        width: 325px;
        font-size: 28px;
        font-weight: bolder;
        color: #eeeeee;
      }
    }

    .animation_writer {
      animation: grow 7s steps(44) 4s normal infinite,
      blink 1s steps(44) infinite normal;
      white-space: nowrap;
      border-right: 2px solid #eeeeee;
      overflow: hidden;
    }

    .down {
      position: absolute;
      left: 50%;
      top: -30%;
      animation-name: updown;
      animation-duration: 2.5s;
      animation-iteration-count: infinite;

      .icon {
        font-size: 36px;
        font-weight: bold;
      }

    }

    .wave {
      width: 100vw;
      height: 200px;
      left: 0;
      top: -200px;
      position: absolute;
      overflow: hidden;

      .waves {
        position: absolute;
        width: 100vw;
        bottom: -80px;

        /* Animation */
        .parallax > use {
          animation: move-forever 25s cubic-bezier(0.55, 0.5, 0.45, 0.5) infinite;
        }

        .parallax > use:nth-child(1) {
          animation-delay: -2s;
          animation-duration: 10s;
        }

        .parallax > use:nth-child(2) {
          animation-delay: -3s;
          animation-duration: 13s;
        }

        .parallax > use:nth-child(3) {
          animation-delay: -4s;
          animation-duration: 16s;
        }

        .parallax > use:nth-child(4) {
          animation-delay: -5s;
          animation-duration: 23s;
        }
      }
    }

    .home_content {
      display: flex;
      margin: 10px auto 0 auto;
      width: 80%;

      .content_left {
        display: flex;
        flex-direction: column;
        width: 25%;
        z-index: 1;

        .content_search,
        .content_recommend,
        .content_browse,
        .content_consultation {
          display: flex;
          margin-top: 30px;
          width: 100%;
        }

        .content_info {
          display: flex;
          flex-direction: column;
          transition-duration: 0.5s;
          background: linear-gradient(to right, #9fe1fa, #f4edc9);

          .info_link {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px 0 20px 0;

            ul {
              display: flex;
              height: 100%;
              flex-direction: row;
              justify-content: center;
              align-items: center;
              /* 默认有边距，都要清除 */
              margin: 0;
              padding: 0;
              list-style: none;

              li {
                padding-left: 10px;

                .icon {
                  flex: 1;
                  width: 1em;
                  height: 1em;
                  vertical-align: -0.15em;
                  fill: currentColor;
                  overflow: hidden;
                  font-size: 32px;
                }
              }

              li:hover {
                color: #f38181;
              }

              .info_backrougnd {
                display: flex;
                width: 100%;
                height: 35%;
              }
            }
          }

          img {
            width: 100%;
            height: 100%;
            border-radius: 5px 5px 0 0;
            object-fit: cover;
          }

          .info_avater {
            display: flex;
            width: 100%;
            height: 10%;

            .info_name {
              padding-left: 45px;
              display: flex;
              flex: 3;
              align-items: center;
              font-size: 16px;
            }

            a {
              display: flex;
              flex: 1;
              width: 40%;

              span {
                width: 60px;
                height: 60px;
                margin: -30px 0 0 12px;
              }

              img {
                width: 100%;
                height: 100%;
                display: block;
                border-radius: 50%;
              }

              img:hover {
                transform: rotate(360deg);
                transition-duration: 0.8s;
              }
            }
          }

          .info_common {
            display: flex;
            flex-direction: row;
            justify-content: space-around;
            align-items: center;
            width: 100%;
            height: 30%;
            padding: 20px 0 20px 0;
            font-size: 16px;

            .common {
              display: flex;
              flex-direction: row;
              justify-content: center;
              align-items: center;
              flex-wrap: wrap;
              height: 50%;

              span {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100%;
                font-size: 16px;
              }
            }
          }
        }

        .content_search {
          transition-duration: 0.5s;

          .search {
            width: 100%;
            height: 50%;
            display: flex;
            flex-wrap: wrap;
            padding: 7px;

            .search_title {
              width: 100%;
              height: 50%;
              margin: 5px 0 0 5px;
              font-size: 22px;
            }

            .search_box {
              display: flex;
              justify-content: center;
              align-items: center;
              flex-wrap: nowrap;
              width: 100%;
              height: 50%;
              margin-top: 20px;
              border: #676767 2px solid;
              border-radius: 15px;

              .icon {
                flex: 2;
                width: 1rem;
                vertical-align: -0.15em;
                fill: currentColor;
                overflow: hidden;
                font-size: 22px;
              }

              .search_txt {
                flex: 8;
                width: 80%;
                margin-left: 10px;
                float: right;
              }

              input[type=text]::placeholder {
                color: #676767;
              }
            }
          }
        }

        .content_recommend {
          .recommend {
            display: flex;
            flex-direction: column;
            padding: 20px 20px 20px 10px;
            width: 100%;
            flex: 1;
          }

          .recommend_title {
            display: flex;
            align-items: center;
            font-size: 22px;
            padding-bottom: 10px;

            .icon {
              width: 1em;
              height: 1em;
              vertical-align: -0.15em;
              fill: currentColor;
              overflow: hidden;
              font-size: 30px;
            }

            p {
              padding-left: 5px;
              font-size: 20px;
            }
          }

          .recommend_row {
            padding: 5px 0 5px 0;

            .recommend_message {
              width: 100%;
              display: flex;

              span {
                flex: 1;
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
                padding-left: 5px;
                font-size: 14px;
              }

              .recommend_img {
                width: 100%;
                height: 100%;
                overflow: hidden;
                flex: 1;
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

            .recommend_time {
              display: flex;
              align-items: center;
              height: 20%;
              padding-top: 2px;

              .icon {
                width: 1em;
                height: 1em;
                vertical-align: -0.15em;
                fill: currentColor;
                overflow: hidden;
                font-size: 16px;
                padding-left: 1px;
              }

              p {
                font-size: 12px;
                color: #bfbfbf;
              }

              .content_browse {
                display: flex;
                flex-direction: column;
              }
            }
          }
        }

        .content_browse {
          .browse {
            display: flex;
            flex: 1;
            margin-top: 10px;
            padding: 10px;
            flex-direction: column;

            span {
              padding-top: 10px;
              font-size: 16px;
            }

            span:nth-child(2) {
              display: flex;
              align-items: center;
              font-size: 18px;
            }

            span:nth-child(2) p {
              font-size: 24px;
              border-bottom: 2px solid #fff;
            }

            span:last-child {
              font-size: 14px;
            }
          }

          .browse:nth-child(1) {
            background-image: linear-gradient(to top, #fad0c4 0%, #fad0c4 1%, #ffd1ff 100%);
          }

          .browse:nth-child(2) {
            background-image: linear-gradient(-225deg, #FFE29F 0%, #FFA99F 48%, #FF719A 100%);
          }

          .browse:nth-child(3) {
            background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
          }

          .browse:nth-child(4) {
            background-image: linear-gradient(to right, #fddb92 0%, #d1fdff 100%);
          }
        }
      }

      .content_right {
        display: flex;
        flex-direction: column;
        width: 80%;
        margin: 0 0 0 40px;
        z-index: 1;

        .tongzhi {
          display: flex;
          flex-direction: row;
          align-items: center;
          width: 100%;
          height: 100px;
          padding: 15px;
          box-sizing: border-box;
          border: 1px dashed #c9d6df;
        }

        .icon {
          font-size: 32px;
        }

        .content {
          display: flex;
          flex-direction: column;
          align-items: center;
          flex-wrap: wrap;
          flex-grow: 1;
          padding-left: 15px;

          .title {
            display: flex;
            align-items: center;
            flex: 1;
            width: 100%;
            text-align: left;
          }

          .body {
            display: flex;
            align-items: center;
            flex: 1;
            width: 100%;
            text-align: left;
          }
        }

        .container {
          display: flex;
          flex-direction: column;
          width: 100%;
          height: 100%;
          padding-top: 40px;

          .category {
            display: flex;
            flex-direction: column;
            width: 100%;
            padding: 0 0 40px 0;

            .category_header {
              display: flex;
              justify-content: space-between;
              border-bottom: 1px dashed #c9d6df;
              padding-bottom: 5px;

              .icon {
                width: 1em;
                height: 1em;
                vertical-align: -0.15em;
                fill: currentColor;
                overflow: hidden;
                display: flex;
              }

              span {
                display: flex;
                justify-content: center;
                align-items: center;
                font-size: 18px;
              }
            }

            .category_body {
              width: 100%;
              height: 80%;
              padding-top: 20px;

              .category_list {
                display: flex;
                flex-direction: row;
                flex-wrap: wrap;
                justify-content: space-between;

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

                  .category-time {
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
              }
            }
          }
        }
      }
    }
  }
}
</style>
