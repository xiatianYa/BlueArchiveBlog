<template>
  <div class="comment">
    <div class="leaver_body">
      <div class="leaver_container">
        <div class="leaver_title">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-liuyanguanli"></use>
          </svg>
          <span>留言</span>
        </div>
        <div class="leaver_input">
          <textarea type="text" placeholder="写些留言一起交流吧..." v-model="commentContent"></textarea>
        </div>
        <div class="leaver_emoji">
          <V3Emoji :disable-group="disableGroup" @click-emoji="appendComment" :options-name="optionsName"
            :fulldata="true" :recent="true" />
          <button class="pointer" @click="addLeaverComment">提交</button>
        </div>
        <div class="leaver_comments">
          <div class="comment" v-for="comment in commentList" :key="comment.id">
            <div class="comment_left">
              <div class="user_avater">
                <img v-lazy="comment.userAvatar">
              </div>
            </div>
            <div class="comment_right">
              <div class="publish_info">
                <span class="user_name">{{ comment.userName }}</span>
                <span class="publish_time">{{ comment.createTime.substring(0, 16) }}</span>
                <span class="reply" @click="openLeaverDialogParent(comment.id)">回复</span>
              </div>
              <div class="comment_content">
                <span class="content">{{ comment.commentContent }}</span>
              </div>
              <div class="comment_reply" v-for="reply in comment.replyList" :key="reply.id">
                <div class="reply_left">
                  <div class="reply_avatar">
                    <img v-lazy="reply.userAvatar">
                  </div>
                </div>
                <div class="reply_right">
                  <div class="reply_info">
                    <span class="user_name">{{ reply.userName }}</span>
                    <span class="reply_time">{{ reply.createTime.substring(0, 16) }}</span>
                    <span class="reply" @click="openLeaverDialog(reply, comment.id)">回复</span>
                  </div>
                  <div class="reply_content">
                    <span class="replyAt" v-show="reply.atUserName">@{{ reply.atUserName }}&nbsp;:&nbsp;</span>
                    <span class="content">{{ reply.commentContent }}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="leaver_dialog" v-show="addLeaver">
      <div class="dialog">
        <div class="leaver_title">
          <span>留言</span>
          <svg class="icon pointer" aria-hidden="true" @click="closeLeaverDialog()">
            <use xlink:href="#icon-guanbi"></use>
          </svg>
        </div>
        <div class="leaver_input">
          <textarea type="text" placeholder="写些留言一起交流吧..." v-model="commentLeaver.commentContent"></textarea>
        </div>
        <div class="leaver_emoji">
          <V3Emoji class="emoji" :disable-group="disableGroup" @click-emoji="appendCommentChile"
            :options-name="optionsName" :fulldata="true" :recent="true" />
          <button class="pointer" @click="addLeaverCommentChile">提交</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import {nextTick, onMounted, ref} from "vue";
import V3Emoji from "vue3-emoji";
import {addComment, listComment} from '@/api/comment'
import promptMsg from "@/components/PromptBoxView"

const props = defineProps(['commentType', 'commonId'])
//通用ID
const commonId = ref()
//回复内容
const commentContent = ref("")
//回复类型
const commentType = ref()
//留言框是否开启
const addLeaver = ref(false)
//查询参数
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  commentType: props.commentType
})
//留言列表
const commentList = ref([])
//留言框对象
const commentLeaver = ref({
  commentContent: "",
  //是否是回复子留言
  reply: null,
  commonId: null,
})
onMounted(() => {
  nextTick(() => {
    commentType.value = props.commentType;
    commonId.value = props.commonId;
    init();
  })
})
//数据初始化
function init() {
  //获取留言,分页获取
  queryParams.value.commonId = commonId.value;
  listComment(queryParams.value).then(res => {
    commentList.value = res.rows;
  })
}

//添加头节点
function addLeaverComment() {
  //参数赋值
  commentLeaver.value.parentId = 0;
  commentLeaver.value.commentType = commentType.value;
  commentLeaver.value.commentContent = commentContent.value;
  commentLeaver.value.commonId = commonId.value;
  addComment(commentLeaver.value).then(res => {
    promptMsg({ type: "success", msg: res.msg })
    //重新获取数据
    init();
  }).catch(error => {
    promptMsg({ type: "warn", msg: "请先登录!" })
  })
  closeLeaverDialog();
}
//添加子节点
function addLeaverCommentChile() {
  if (commentLeaver.value.reply) {
    commentLeaver.value.commentContent = "@" + commentLeaver.value.reply.userName + ":" + commentLeaver.value.commentContent;
  }
  addComment(commentLeaver.value).then(res => {
    promptMsg({ type: "success", msg: res.msg })
    //重新获取数据
    init();
  }).catch(error => {
    promptMsg({ type: "warn", msg: "请先登录!" })
  })
  closeLeaverDialog();
}

//打开留言框 回复子留言
function openLeaverDialogParent(commentId) {
  addLeaver.value = true;
  commentLeaver.value.parentId = commentId;
  commentLeaver.value.commentType = commentType.value;
  commentLeaver.value.commonId = commonId.value;
}
//打开留言框 回复孙留言
function openLeaverDialog(reply, commentId) {
  addLeaver.value = true;
  commentLeaver.value.parentId = commentId;
  commentLeaver.value.reply = reply;
  commentLeaver.value.commentType = commentType.value;
  commentLeaver.value.commonId = commonId.value;
}

//添加表情
function appendComment(emajor) {
  commentContent.value += emajor.emoji
}
//添加子表情
function appendCommentChile(emajor) {
  commentLeaver.value.commentContent += emajor.emoji
}

//关闭留言框
function closeLeaverDialog() {
  addLeaver.value = false;
  //清空留言
  commentContent.value = "";
  //清空子留言对象
  commentLeaver.value = {
    commentContent: ""
  };
}
</script>
<style lang="scss">
.comment {
  display: flex;
  justify-content: center;

  .leaver_body {
    display: flex;
    justify-content: center;
    width: 100%;
    padding-top: 10px;
    padding-bottom: 40px;

    .leaver_container {
      width: 100%;
      box-sizing: border-box;

      .leaver_title {
        display: flex;
        align-items: center;

        .icon {
          font-size: 24px;
        }

        span {
          color: #A1B6C3;
          padding-left: 5px;
        }
      }

      .leaver_input {
        textarea {
          resize: none;
          box-sizing: border-box;
          width: 100%;
          height: 250px;
          padding: 5px;
          color: #A1B6C3;
          border-radius: 5px;
        }

        textarea:focus {
          border: 1px solid #A1B6C3;
          /* 获得焦点时的边框颜色 */
          outline: none;
          /* 移除浏览器默认的聚焦轮廓 */
        }

        /* 标准语法 */
        textarea::placeholder {
          color: #767676;
        }
      }

      .leaver_emoji {
        display: flex;
        padding-top: 3px;

        button {
          width: 60px;
          border-radius: 20px;
          background-color: #A1B6C3;
          border: 1px solid #000000;
        }

        button:hover {
          background-color: #ffe2e2;
          border: 1px solid #ffcfdf;
        }
      }

      .leaver_comments {
        width: 100%;
        margin-top: 20px;

        .comment {
          display: flex;
          padding-top: 20px;
          padding-bottom: 20px;
          width: 100%;
          height: 100%;
          border-top: 2px solid #D5D5D5;

          .comment_left {
            display: flex;

            .user_avater {
              padding-right: 10px;

              img {
                width: 40px;
                height: 40px;
                border-radius: 5px;
                object-fit: cover;
              }
            }
          }

          .comment_right {
            width: 90%;
            height: 100%;

            .publish_info {
              display: flex;

              .user_name {
                display: flex;
                align-items: center;
                font-size: 14px;
                padding-right: 5px;
              }

              .publish_time {
                display: flex;
                flex-grow: 1;
                align-items: end;
                font-weight: 100;
                font-size: 12px;
              }

              .reply {
                box-sizing: border-box;
                color: #FFFDFF;
                padding: 2px;
                font-size: 12px;
                background-color: #FFA500;
                border-radius: 5px;
              }
            }

            .comment_content {
              box-sizing: border-box;
              display: flex;
              align-items: center;
              flex-wrap: wrap;
              width: 100%;
              margin-top: 10px;
              padding: 10px;
              border-radius: 10px;
              background-color: #676767;

              .content {
                font-size: 14px;
              }
            }

            .comment_reply {
              display: flex;
              width: 100%;
              padding-top: 20px;

              .reply_left {
                display: flex;

                .reply_avatar {
                  padding-right: 10px;

                  img {
                    width: 40px;
                    height: 40px;
                    border-radius: 5px;
                    object-fit: cover;
                  }
                }
              }

              .reply_right {
                flex-grow: 1;
                width: 90%;
                height: 100%;

                .reply_info {
                  display: flex;

                  .user_name {
                    display: flex;
                    align-items: center;
                    font-size: 14px;
                    padding-right: 5px;
                  }

                  .reply_time {
                    display: flex;
                    align-items: end;
                    flex-grow: 1;
                    font-weight: 100;
                    font-size: 12px;
                  }

                  .reply {
                    box-sizing: border-box;
                    color: #FFFDFF;
                    padding: 2px;
                    font-size: 12px;
                    background-color: #FFA500;
                    border-radius: 5px;
                  }
                }

                .reply_content {
                  box-sizing: border-box;
                  display: flex;
                  align-items: center;
                  flex-wrap: wrap;
                  width: 100%;
                  margin-top: 10px;
                  padding: 10px;
                  border-radius: 10px;
                  background-color: #676767;

                  .content {
                    font-size: 14px;
                  }

                  .replyAt {
                    font-size: 14px;
                    color: #0097FC;
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  .leaver_dialog {
    position: fixed;
    display: flex;
    justify-content: center;
    top: 0;
    width: 100%;
    height: 100%;
    padding-top: 70px;
    background-color: rgba(rgb(0, 0, 0), 0.3);

    .dialog {
      position: fixed;
      width: 420px;
      height: 350px;
      border-radius: 10px;
      background-color: #FFFFFF;

      .leaver_title {
        box-sizing: border-box;
        position: relative;
        width: 100%;
        height: 15%;
        text-align: center;
        margin-top: 20px;

        .icon {
          position: absolute;
          top: 0;
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

      .leaver_input {
        display: flex;
        justify-content: center;
        height: 65%;

        textarea {
          resize: none;
          box-sizing: border-box;
          padding: 10px;
          border-radius: 3px;
          width: 80%;
          color: #A1B6C3;
          opacity: 1;
        }

        textarea:focus {
          border: 1px solid #A1B6C3;
          /* 获得焦点时的边框颜色 */
          outline: none;
          /* 移除浏览器默认的聚焦轮廓 */
        }

        /* 标准语法 */
        textarea::placeholder {
          color: #767676;
        }
      }

      .leaver_emoji {
        display: flex;
        justify-content: center;
        width: 100%;
        padding-top: 5px;

        .emoji {
          width: 70%;
        }

        button {
          width: 10%;
          border-radius: 5px;
          background-color: #A1B6C3;
          border: 1px solid #000000;
        }

        button:hover {
          background-color: #ffe2e2;
          border: 1px solid #ffcfdf;
        }
      }
    }

  }
}
</style>