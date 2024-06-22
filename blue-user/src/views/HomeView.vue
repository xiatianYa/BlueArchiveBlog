<template>
  <div class="home-box no-select">
    <div class="animate__animated animate__slideInDown banner">
      <div class="banner-img">
        <img :src="bgUrl">
      </div>
      <div class="typewriter animation-writer">
        <span>眼 前 所 见 , 皆 为 奇 迹 .</span>
      </div>
      <div class="down pointer">
        <svg class="icon pointer" aria-hidden="true" @click="goDown">
          <use xlink:href="#icon-down"></use>
        </svg>
      </div>
    </div>
    <div class="home-center-box">
      <div class="home-container">
        <div class="content-left animate__animated animate__slideInLeft">
          <!-- 用户信息 -->
          <div class="content-info box-shadow box-radius">
            <div class="info-backrougnd">
              <img :src="'https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/info.jpg'">
            </div>
            <div class="info-avater" v-if="userStore.avatar">
              <a>
                <span>
                  <img :src="userStore.avatar">
                </span>
              </a>
              <div class="info-name" v-if="userStore.nickName">
                {{ userStore.nickName }}
              </div>
            </div>
            <div class="info-link">
              <ul>
                <li @click="goHref('https://space.bilibili.com/180200497?spm-id-from=333.1007.0.0')">
                  <svg class="icon pointer" aria-hidden="true">
                    <use xlink:href="#icon-icon-bilibili"></use>
                  </svg>
                </li>
                <li @click="goHref('https://github.com/xiatianYa')">
                  <svg class="icon pointer" aria-hidden="true">
                    <use xlink:href="#icon-icon-github"></use>
                  </svg>
                </li>
                <li @click="goHref('https://gitee.com/xiatianYa1003')">
                  <svg class="icon pointer" aria-hidden="true">
                    <use xlink:href="#icon-gitee-fill-round"></use>
                  </svg>
                </li>
              </ul>
            </div>
          </div>
          <!-- 搜索 -->
          <div class="content-search box-shadow box-radius">
            <div class="search">
              <div class="search-title">
                搜索
              </div>
              <div class="search-box">
                <input type="text" placeholder="搜索文章" class="search-txt" @keydown.enter="search"
                  v-model="queryParams.searchValue">
                <svg class="icon pointer" aria-hidden="true" @click="search">
                  <use xlink:href="#icon-daohang"></use>
                </svg>
              </div>
            </div>
          </div>
          <!-- 在线用户 -->
          <div class="content-online box-shadow box-radius">
            <div class="online-title">在线用户列表</div>
            <n-scrollbar style="max-height: 150px">
              <div class="onlines">
                <div class="online" v-for="onlineUser in globalStore.onlineUserList">
                  <n-popover placement="top-start">
                    <template #trigger>
                      <img class="avatar" :src="onlineUser.userAvatar">
                    </template>
                    <span>
                      {{ onlineUser.userNickName }}
                    </span>
                  </n-popover>
                </div>
              </div>
            </n-scrollbar>
          </div>
          <!-- 推荐文章 -->
          <div class="content-recommend box-shadow box-radius">
            <div class="recommend">
              <div class="recommend-row recommend-title">
                <svg class="icon" aria-hidden="true">
                  <use xlink:href="#icon-huangguan"></use>
                </svg>
                <p>
                  推荐文章
                </p>
              </div>
              <div class="recommend-row" v-for="article in recommendArticleList" @click="goArticlePreview(article.id)">
                <div class="recommend-message pointer">
                  <div class="recommend-img">
                    <img :src="article.cover" />
                  </div>
                  <div class="recommend-info">
                    <span class="article-name">
                      <n-ellipsis :line-clamp="1">
                        {{ article.articleName }}
                      </n-ellipsis>
                    </span>
                    <span class="article-author">
                      <n-ellipsis :line-clamp="1">
                        作者 : {{ article.userName }}
                      </n-ellipsis>

                    </span>
                    <span class="article-time">
                      <n-ellipsis :line-clamp="1">
                        {{ article.createTime }}
                      </n-ellipsis>
                    </span>
                  </div>
                </div>
                <div class="recommend-time">
                  <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-rili"></use>
                  </svg>
                  <p>{{ article.createTime }}</p>
                </div>
              </div>
            </div>
          </div>
          <!-- 文章分类 -->
          <div class="content-browse">
            <div class="shell box-radius pointer box-shadow">
              <div class="box" v-for="sort, index in sortList" :key="sort.id" @click="goArticleBySortId(sort.id)">
                <img src="@/assets/images/xingye.png" v-if="index === 0" />
                <img src="@/assets/images/alis.png" v-else-if="index === 1" />
                <img src="@/assets/images/quenai.png" v-else-if="index === 2" />
                <img src="@/assets/images/wangxiaotao.png" v-else />
                <span>{{ sort.sortName }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="content-right animate__animated animate__slideInRight">
          <!-- 通知 -->
          <div class="tongzhi">
            <svg class="icon pointer scaleDraw" aria-hidden="true">
              <use xlink:href="#icon-tongzhi1"></use>
            </svg>
            <div class="content">
              <span class="title">{{ noticeInfo.noticeTitle }}</span>
              <span class="body">{{ noticeInfo.noticeContent }}</span>
            </div>
          </div>
          <!-- 文章主题内容 -->
          <div class="container">
            <div class="category" v-for="sort in sortList">
              <div class="category-header">
                <svg class="icon pointer" aria-hidden="true">
                  <use xlink:href="#icon-icon-gengduo"></use>
                </svg>
                <span>
                  {{ sort.sortName }}
                </span>
                <div class="more pointer" @click="goArticleBySortId(sort.id)">
                  <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-fenlei"></use>
                  </svg>
                  <span>More</span>
                </div>
              </div>
              <div class="category-body">
                <div class="category-list">
                  <CategoryDetail class="test" :article="article" v-for="article in sort.articleList" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="article_dialog" v-show="searchShow">
      <div class="dialog">
        <div class="article_title">
          <span>文章信息</span>
          <svg class="icon pointer" aria-hidden="true" @click="closeSearchDialog">
            <use xlink:href="#icon-guanbi"></use>
          </svg>
        </div>
        <div class="article_list">
          <div class="article pointer" v-for="article in searchArticle.blueArticleList"
            v-show="searchArticle.blueArticleList.length > 0" @click="goArticlePreview(article.id)">
            <div class="article_name" v-html="article.articleName"></div>
            <div class="article_describe" v-html="article.articleDescribe"></div>
            <div class="article_info">
              <div class="info_left">
                <div class="info">
                  <svg class="icon pointer" aria-hidden="true">
                    <use xlink:href="#icon-remen"></use>
                  </svg>
                  <span>
                    {{ article.hot }} 热度
                  </span>
                </div>
                <div class="info">
                  <svg class="icon pointer" aria-hidden="true">
                    <use xlink:href="#icon-pinglun"></use>
                  </svg>
                  <span>
                    {{ article.comment }} 评论
                  </span>
                </div>
                <div class="info">
                  <svg class="icon pointer" aria-hidden="true">
                    <use xlink:href="#icon-dianzan1"></use>
                  </svg>
                  {{ article.like }} 点赞
                </div>
              </div>
              <div class="info_right">
                <span v-html="article.userName">
                </span>
                <span>{{ article.createTime }}</span>
              </div>
            </div>
          </div>
          <div v-show="!searchLoading && searchArticle.blueArticleList.length == 0">
            <span>
              {{ searchArticle.msg }}
            </span>
          </div>
          <Loading v-show="searchLoading" />
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { listNotice } from '@/api/notice'
import { listArticle, searchArticleList, listByHome } from '@/api/article'
import { listSort } from '@/api/sort/sort'
import { useRouter } from 'vue-router'
import { NEllipsis, NScrollbar, NPopover } from 'naive-ui'
let { globalStore, userStore } = useStore()
import CategoryDetail from '@/components/CategoryDetail.vue'
import Loading from '@/components/CssLoadingView01.vue'
import useStore from "@/store"

//路由
const router = useRouter()
//公告
const noticeInfo = ref({
  noticeTitle: "",
  noticeContent: ""
})
//分类列表
const sortList: any = ref()
//推荐文章
const recommendArticleList: any = ref()
//背景路径
const bgUrl = ref(globalStore.getBgByType("0"))
//搜索加载中
const searchLoading = ref(false);
//搜索显示
const searchShow = ref(false)
//搜索文章列表
const searchArticle = ref({
  total: "",
  blueArticleList: <any>[],
  msg: "",
})
//搜索条件
const queryParams = ref({
  pageNum: 0,
  pageSize: 10,
  searchValue: ""
})
onMounted(() => {
  //获取公告
  listNotice().then((res: any) => {
    noticeInfo.value = res.rows[0]
  })
  //获取分类
  listSort().then((res: any) => {
    sortList.value = res.rows
  })
  //获取首页文章
  listByHome().then(res => {
    res.data.forEach((articleVo: any) => {
      sortList.value.forEach((sort: any) => {
        if (sort.id === articleVo.sortId) {
          sort.articleList = articleVo.blueArticleList;
        }
      });
    })
  })
  //获取推荐文章
  listArticle().then((res: any) => {
    recommendArticleList.value = res.rows.slice(0, 3)
  })
})
//前往文章浏览页
function goArticlePreview(articleId: number) {
  router.push({ path: '/editPreView', query: { articleId: articleId } })
}
//搜索
function search() {
  //查询匹配文章
  searchShow.value = true;
  //加载中
  searchLoading.value = true;
  searchArticleList(queryParams.value).then(res => {
    searchArticle.value = res.data
    //加载完成
    searchLoading.value = false;
    //列表为空
    if (res.data.blueArticleList == 0) {
      searchArticle.value.msg = "很抱歉,没有找到与 " + queryParams.value.searchValue + " 相关的文章";
    }
  }).catch(() => {
    //超时 出错 重发请求
    searchArticleList(queryParams.value).then(res => {
      searchArticle.value = res.data
      //加载完成
      searchLoading.value = false;
      if (res.data.blueArticleList == 0) {
        searchArticle.value.msg = "很抱歉,没有找到与 " + queryParams.value.searchValue + " 相关的文章";
      }
    }).catch(() => {
      searchArticle.value.msg = "很抱歉,连接超时,请重试...";
    })
  })
}
//关闭搜索框
function closeSearchDialog() {
  //清空搜索条件
  queryParams.value = {
    pageNum: 0,
    pageSize: 10,
    searchValue: ""
  }
  //清空列表
  searchArticle.value = {
    total: "",
    blueArticleList: [],
    msg: ""
  }
  //关闭加载
  searchLoading.value = false;
  //关闭窗口
  searchShow.value = false;
}
//前往分类,携带分类ID
function goArticleBySortId(sortId: number) {
  router.push({ path: '/sort', query: { sortId: sortId } })
}
//前往页面底部
function goDown() {
  window.scrollTo({ behavior: 'smooth', top: window.innerHeight });
}
//跳转链接
function goHref(url: string) {
  window.open(url, "-blank")
}
</script>
<style lang="scss" scoped>
.shell {
  width: 100%;
  height: 250px;
  display: flex;
  flex-wrap: wrap;

  .box {
    flex: 1;
    overflow: hidden;
    transition: .5s;
    margin: 0 2px;
    border-radius: 15px;
  }

  .box>img {
    width: 125%;
    height: 85%;
    object-fit: cover;
    transition: .5s;
  }

  .box>span {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 15%;
  }

  .box:hover {
    flex-basis: 40%;
  }

  .box:hover>img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
}

.home-box {
  width: 100%;
  height: auto;
  overflow: hidden;
  padding-bottom: 40px;

  .banner {
    width: 100%;
    height: 100vh;

    .banner-img {
      width: 100%;
      height: 100%;

      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    }

    .typewriter {
      z-index: 1;
      position: absolute;
      width: 0;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      margin-left: 18px;

      span {
        width: 325px;
        font-size: 28px;
        font-weight: bolder;
        color: #eeeeee;
      }
    }

    .animation-writer {
      animation: grow 7s steps(44) 1s normal infinite,
        blink 0.5s steps(44) normal infinite;
      white-space: nowrap;
      border-right: 2px solid #eeeeee;
      overflow: hidden;
    }

    .down {
      position: absolute;
      top: 53%;
      left: 50%;
      transform: translate(-50%, -50%);
      z-index: 99;
      animation-name: updown;
      animation-duration: 2.5s;
      animation-iteration-count: infinite;

      .icon {
        font-size: 36px;
        font-weight: bold;
      }

    }
  }

  .home-center-box {
    display: flex;
    width: 100%;
    position: relative;

    .home-container {
      display: flex;
      margin: 10px auto 0 auto;
      width: 80%;

      .content-left {
        display: flex;
        flex-direction: column;
        width: 25%;
        z-index: 1;

        .content-search,
        .content-recommend,
        .content-browse,
        .content-online {
          display: flex;
          margin-top: 30px;
          width: 100%;
        }

        .content-online {
          box-sizing: border-box;
          padding: 10px;
          display: flex;
          flex-wrap: wrap;

          .online-title {
            width: 100%;
          }

          .onlines {
            display: flex;
            flex-wrap: wrap;
            width: 100%;

            .online {
              width: 10%;
              display: flex;
              padding: 5px;
              align-items: center;

              .avatar {
                width: 100%;
                border-radius: 50%;
              }
            }
          }
        }

        .content-info {
          transition-duration: 0.5s;
          background: linear-gradient(to right, #9fe1fa, #f4edc9);

          .info-link {
            display: flex;
            justify-content: center;
            padding: 20px 0 20px 0;

            ul {
              display: flex;
              height: 100%;
              /* 默认有边距，都要清除 */
              margin: 0;
              padding: 0;
              list-style: none;

              li {
                box-sizing: border-box;

                .icon {
                  fill: currentColor;
                  overflow: hidden;
                  font-size: 32px;
                }
              }

              li:hover {
                color: #f38181;
              }

              .info-backrougnd {
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

          .info-avater {
            display: flex;
            width: 100%;
            height: 10%;

            .info-name {
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
        }

        .content-search {
          transition-duration: 0.5s;

          .search {
            width: 100%;
            height: 50%;
            display: flex;
            flex-wrap: wrap;
            padding: 7px;

            .search-title {
              width: 100%;
              height: 50%;
              margin: 5px 0 0 5px;
              font-size: 22px;
            }

            .search-box {
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
                vertical-align: -0.15em;
                fill: currentColor;
                overflow: hidden;
                font-size: 22px;
              }

              .search-txt {
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

        .content-recommend {
          .recommend {
            display: flex;
            flex-direction: column;
            padding: 20px 20px 20px 10px;
            width: 100%;
            flex: 1;
          }

          .recommend-title {
            display: flex;
            align-items: center;
            padding-bottom: 10px;

            .icon {
              width: 1em;
              height: 1em;
              vertical-align: -0.15em;
              fill: currentColor;
              overflow: hidden;
              font-size: 28px;
            }

            p {
              padding-left: 5px;
              font-size: 20px;
            }
          }

          .recommend-row {
            padding: 5px 0 5px 0;

            .recommend-message {
              width: 100%;
              height: 70%;
              margin: 5px 0;
              display: flex;
              flex-direction: row;

              .recommend-img {
                width: 50%;
                height: 100%;
                border-radius: 10px;
                overflow: hidden;

                img {
                  width: 100%;
                  height: 100px;
                  object-fit: fill;
                  transition: all .3s ease-in 0s;
                }

                img:hover {
                  border-radius: 10px;
                  object-fit: fill;
                  transform: scale(1.1);
                  transition: all 0.3s ease-in;
                }

              }

              .recommend-info {
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                width: 50%;
                padding: 0px 7px;

                .article-name {
                  width: 100%;
                  font-size: 12px;
                }

                .article-author {
                  width: 100%;
                  font-size: 12px;
                }

                .article-time {
                  width: 100%;
                  font-size: 10px;
                }
              }
            }

            .recommend-time {
              display: flex;
              align-items: center;
              height: 20%;
              padding-top: 2px;

              .icon {
                font-size: 14px;
                padding-left: 5px;
                padding-right: 5px;
              }

              p {
                font-size: 12px;
              }

              .content-browse {
                display: flex;
                flex-direction: column;
              }
            }
          }
        }

        .content-browse {
          width: 100%;
        }
      }

      .content-right {
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
          border: 2px dashed #c9d6df;
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

            .category-header {
              display: flex;
              align-items: center;
              justify-content: space-between;
              border-bottom: 2px dashed #c9d6df;
              padding-bottom: 5px;

              .more {
                display: flex;
                align-items: center;
                justify-content: center;
                transform: scale(1);
                transition: all 0.5s ease-in-out;

                .icon {
                  font-size: 16px;
                }

                span {
                  padding-left: 5px;
                  font-size: 16px;
                }
              }

              .more:hover {
                transform: scale(1.3);

                span {
                  color: #64EDAC;
                }
              }

              .icon {
                width: 1em;
                height: 1em;
                vertical-align: -0.15em;
                font-size: 20px;
                fill: currentColor;
                overflow: hidden;
                display: flex;
              }

              span {
                display: flex;
                justify-content: center;
                align-items: center;
                font-size: 16px;
              }
            }

            .category-body {
              width: 100%;
              height: 80%;
              padding-top: 20px;

              .category-list {
                display: flex;
                flex-direction: row;
                flex-wrap: wrap;
                justify-content: space-between;

                .category-detail {
                  width: 30%;
                  display: flex;
                  flex-direction: column;
                  border-radius: 10px 10px 10px 10px;

                  .category-img {
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

                  .category-title {
                    display: flex;
                    align-items: center;
                    font-size: 16px;
                    margin: 15px 5px 15px 7px;
                    flex-grow: 1;
                  }

                  .category-info {
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

                  .category-msg {
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

                  .category-target {
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

                .category-detail:nth-child(n + 4) {
                  margin-top: 20px;
                }
              }
            }
          }
        }
      }
    }
  }

  .article_dialog {
    position: fixed;
    display: flex;
    justify-content: center;
    top: 0;
    width: 100%;
    height: 100%;
    padding-top: 70px;
    background-color: rgba(rgb(0, 0, 0), 0.3);
    z-index: 99;

    .dialog {
      position: fixed;
      width: 50%;
      height: 70%;
      border-radius: 10px;
      background-color: #FFFFFF;

      .article_title {
        box-sizing: border-box;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100%;
        height: 10%;
        text-align: center;
        padding: 10px 0;

        .icon {
          position: absolute;
          top: 10;
          right: 10px;
          /* 初始状态 */
          transition: transform 0.5s ease-in-out;
          /* 定义过渡效果，持续时间为0.5秒，使用ease-in-out缓动函数 */
          transform: rotate(0deg);
          /* 初始旋转角度为0度 */
        }

        .icon:hover {
          /* 鼠标移入状态 */
          transform: rotate(240deg);
          /* 旋转角度为360度，即一圈 */
        }
      }

      .article_list {
        box-sizing: border-box;
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 100%;
        padding: 10px 20px;
        height: 90%;
        overflow: auto;

        .article {
          display: flex;
          flex-direction: column;
          width: 100%;
          font-size: 12px;
          margin-top: 10px;
          border-bottom: 1px solid #A1B6C3;

          .article_name {
            padding: 5px 0;
            font-size: 14px;
          }

          .article_describe {
            padding: 5px 0;
          }

          .article_info {
            display: flex;
            justify-content: space-between;
            padding: 5px 0;

            .info_left {
              display: flex;

              .info {
                padding: 0 3px;
              }
            }

            .info_right {
              display: flex;

              span {
                padding-right: 10px;
              }
            }
          }
        }
      }
    }
  }
}
</style>
