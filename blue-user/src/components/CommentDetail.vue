<template>
  <div class="comment">
    <div class="leaver-body">
      <div class="leaver-container">
        <div class="leaver-title">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-liuyanguanli"></use>
          </svg>
          <span>留言</span>
        </div>
        <div class="leaver-input">
          <n-input v-model:value="commentContent" type="textarea" placeholder="写些留言一起交流吧..." />
        </div>
        <div class="leaver-emoji">
          <V3Emoji @click-emoji="appendComment" :fulldata="true" :recent="true" />
          <button class="pointer" @click="addLeaverComment">提交</button>
        </div>
        <div class="leaver-comments">
          <div class="comment" v-for="comment in commentList" :key="comment.id">
            <div class="comment-left">
              <div class="user-avater">
                <img :src="comment.userAvatar">
              </div>
            </div>
            <div class="comment-right">
              <div class="publish-info">
                <span class="user-name">{{ comment.userName }}</span>
                <span class="publish-time">{{ comment.createTime.substring(0, 16) }}</span>
                <span class="reply pointer" @click="openLeaverDialogParent(comment.id)">回复</span>
              </div>
              <div class="comment-content">
                <span class="content">{{ comment.commentContent }}</span>
              </div>
              <div class="comment-reply" v-for="reply in comment.replyList" :key="reply.id">
                <div class="reply-left">
                  <div class="reply-avatar">
                    <img :src="reply.userAvatar">
                  </div>
                </div>
                <div class="reply-right">
                  <div class="reply-info">
                    <span class="user-name">{{ reply.userName }}</span>
                    <span class="reply-time">{{ reply.createTime.substring(0, 16) }}</span>
                    <span class="reply pointer" @click="openLeaverDialog(reply, comment.id)">回复</span>
                  </div>
                  <div class="reply-content">
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
    <div class="leaver-dialog" v-show="addLeaver">
      <div class="dialog">
        <div class="leaver-title">
          <span>留言</span>
          <svg class="icon pointer" aria-hidden="true" @click="closeLeaverDialog()">
            <use xlink:href="#icon-guanbi"></use>
          </svg>
        </div>
        <div class="leaver-input">
          <textarea type="text" placeholder="写些留言一起交流吧..." v-model="commentLeaver.commentContent"></textarea>
        </div>
        <div class="leaver-emoji">
          <V3Emoji class="emoji" @click-emoji="appendCommentChile" :fulldata="true" :recent="true" />
          <button class="pointer" @click="addLeaverCommentChile">提交</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import V3Emoji from "vue3-emoji";
import { nextTick, onMounted, ref } from "vue";
import { NInput } from 'naive-ui'
import { addComment, listComment } from '@/api/comment'
import { useMessage } from 'naive-ui'
interface commentType {
  commentContent: string,
  //是否是回复子留言
  reply: any,
  commonId: number | null,
  parentId: number | null,
  commentType: number | null,
}
//提示框
const message = useMessage()
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
  commonId: 0,
  pageNum: 1,
  pageSize: 10,
  commentType: props.commentType
})
//留言列表
const commentList = ref(<any>[])
//留言框对象
const commentLeaver = ref<commentType>({
  commentContent: "",
  //是否是回复子留言
  reply: "",
  commonId: null,
  parentId: null,
  commentType: null,
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
  listComment(queryParams.value).then((res: any) => {
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
  addComment(commentLeaver.value).then((res: any) => {
    message.success(res.msg)
    //重新获取数据
    init();
  }).catch(error => {
    message.success("请先登录!")
  })
  closeLeaverDialog();
}
//添加子节点
function addLeaverCommentChile() {
  //回复信息 要携带@头
  if (commentLeaver.value.reply) {
    commentLeaver.value.commentContent = "@" + commentLeaver.value.reply.userName + ":" + commentLeaver.value.commentContent;
  }
  //添加评论
  addComment(commentLeaver.value).then((res: any) => {
    message.success(res.msg)
    //重新获取数据
    init();
  }).catch(error => {
    message.success("请先登录!")
  })
  closeLeaverDialog();
}

//打开留言框 回复子留言
function openLeaverDialogParent(commentId: any) {
  addLeaver.value = true;
  commentLeaver.value.parentId = commentId;
  commentLeaver.value.commentType = commentType.value;
  commentLeaver.value.commonId = commonId.value;
}
//打开留言框 回复孙留言
function openLeaverDialog(reply: any, commentId: any) {
  addLeaver.value = true;
  commentLeaver.value.parentId = commentId;
  commentLeaver.value.reply = reply;
  commentLeaver.value.commentType = commentType.value;
  commentLeaver.value.commonId = commonId.value;
}

//添加表情
function appendComment(emajor: any) {
  commentContent.value += emajor.emoji
}
//添加子表情
function appendCommentChile(emajor: any) {
  commentLeaver.value.commentContent += emajor.emoji
}

//关闭留言框
function closeLeaverDialog() {
  addLeaver.value = false;
  //清空留言
  commentContent.value = "";
  //清空子留言对象
  commentLeaver.value = {
    commentContent: "",
    //是否是回复子留言
    reply: "",
    commonId: null,
    parentId: null,
    commentType: null,
  };
}
</script>
<style lang="scss">
.comment {
  display: flex;
  justify-content: center;

  .leaver-body {
    display: flex;
    justify-content: center;
    width: 100%;
    padding-top: 10px;
    padding-bottom: 40px;

    .leaver-container {
      width: 100%;

      .leaver-title {
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

      .leaver-input {
        textarea {
          resize: none;
          width: 100%;
          height: 250px;
          padding: 5px;
          color: #A1B6C3;
          border-radius: 5px;
        }
      }

      .leaver-emoji {
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

      .leaver-comments {
        width: 100%;
        margin-top: 20px;

        .comment {
          display: flex;
          padding-top: 20px;
          padding-bottom: 20px;
          width: 100%;
          height: 100%;
          border-top: 2px solid #D5D5D5;

          .comment-left {
            display: flex;

            .user-avater {
              padding-right: 10px;

              img {
                width: 40px;
                height: 40px;
                border-radius: 5px;
                object-fit: cover;
              }
            }
          }

          .comment-right {
            width: 90%;
            height: 100%;

            .publish-info {
              display: flex;

              .user-name {
                display: flex;
                align-items: center;
                font-size: 14px;
                padding-right: 5px;
              }

              .publish-time {
                display: flex;
                flex-grow: 1;
                align-items: end;
                font-weight: 100;
                font-size: 12px;
              }

              .reply {
                color: #FFFDFF;
                padding: 2px;
                font-size: 12px;
                background-color: #FFA500;
                border-radius: 5px;
              }
            }

            .comment-content {
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

            .comment-reply {
              display: flex;
              width: 100%;
              padding-top: 20px;

              .reply-left {
                display: flex;

                .reply-avatar {
                  padding-right: 10px;

                  img {
                    width: 40px;
                    height: 40px;
                    border-radius: 5px;
                    object-fit: cover;
                  }
                }
              }

              .reply-right {
                flex-grow: 1;
                width: 90%;
                height: 100%;

                .reply-info {
                  display: flex;

                  .user-name {
                    display: flex;
                    align-items: center;
                    font-size: 14px;
                    padding-right: 5px;
                  }

                  .reply-time {
                    display: flex;
                    align-items: end;
                    flex-grow: 1;
                    font-weight: 100;
                    font-size: 12px;
                  }

                  .reply {
                    color: #FFFDFF;
                    padding: 2px;
                    font-size: 12px;
                    background-color: #FFA500;
                    border-radius: 5px;
                  }
                }

                .reply-content {
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

  .leaver-dialog {
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

      .leaver-title {
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

      .leaver-input {
        display: flex;
        justify-content: center;
        height: 65%;

        textarea {
          resize: none;
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

      .leaver-emoji {
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