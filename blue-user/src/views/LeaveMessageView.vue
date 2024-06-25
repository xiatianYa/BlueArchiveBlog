<template>
  <div class="leave animate__animated animate__fadeIn" id="container">
    <div class="leave-header">
      <div class="leave-title">
        留下一条友善的评论吧
      </div>
      <div class="leave-input">
        <div class="search-box">
          <input v-model="BarrageInfo.content" class="search-txt" placeholder="留下点什么吧~" type="text">
          <svg class="icon pointer" aria-hidden="true" @click="addBarrage">
            <use xlink:href="#icon-daohang"></use>
          </svg>
        </div>
      </div>
    </div>
    <div class="leaver-comments">
      <CommentDetail :comment-type="0" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted, ref } from "vue";
import { addMessage, listMessage } from '@/api/message'
import CommentDetail from '@/components/CommentDetail.vue';
import 'vue3-emoji/dist/style.css'
import { useMessage } from 'naive-ui'
import useStore from "@/store"
let { userStore } = useStore()
interface barrageType {
  userId: number | null,
  userAvater: string,
  content: string,
  barrageHeight: number
}
//提示框
const message = useMessage()
//定时器
const timer = ref(<any>[])
// 定义弹幕数据
const BarrageInfo = ref<barrageType>({
  userId: null,
  userAvater: "",
  content: "",
  barrageHeight: 0,
})
//自定义加载弹幕
onMounted(() => {
  startTimer();
})

// 设置定时器
const startTimer = () => {
  let result: any = []
  listMessage().then((res: any) => {
    result = res.rows;
  })
  //每0.2秒添加一条随机弹幕
  timer.value.push(setInterval(() => {
    let barrage: any = getRandomItem(result)
    showBarrage(barrage)
  }, 150))
};


// 清除定时器
const clearTimer = () => {
  for (let index = 0; index < timer.value.length; index++) {
    clearInterval(timer.value[index])
  }
};

//销毁时清理定时器
onUnmounted(() => {
  clearTimer();
})

//添加弹幕函数
function addBarrage() {
  var container = document.getElementsByClassName("leave-header")[0]
  //创建一个div元素
  var Barrage = document.createElement("div");
  //创建一个span元素
  var BarrageSpan = document.createElement("span");
  //创建一个img元素
  var BarrageImg = new Image();
  //设置img图片地址
  BarrageImg.src = userStore.avatar ||
    "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/avater35.png";
  //设置Barrage的ClassName
  Barrage.className = "Barrage";
  //设置BarrageSpan的ClassName
  BarrageSpan.className = "BarrageSpan";
  //设置BarrageImg的ClassName
  BarrageImg.className = "BarrageImg";
  // 设置其文本内容为参数值
  BarrageSpan.innerText = BarrageInfo.value.content;
  // 设置其随机的高度
  var BarrageHeight = Math.floor(Math.random() * container.clientHeight);
  if (BarrageHeight < 53) {
    BarrageHeight = 53;
  } else if (BarrageHeight > container.clientHeight - 40) {
    BarrageHeight = container.clientHeight - 40
  }
  Barrage.style.top = BarrageHeight + "px";

  //添加到弹幕表中
  BarrageInfo.value.userId = userStore.id || 0;
  BarrageInfo.value.userAvater = userStore.avatar ||
    "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/avater35.png";
  BarrageInfo.value.barrageHeight = BarrageHeight;
  addMessage(BarrageInfo.value).then((res: any) => {
    message.success(res.msg)
    BarrageInfo.value.content = ""
    // 设置其随机的颜色
    Barrage.style.color = "rgb(" + Math.floor(Math.random() * 256) + "," + Math.floor(Math.random() * 256) + "," + Math.floor(Math.random() * 256) + ")";
    //将Img元素插入到容器元素中
    Barrage.appendChild(BarrageImg)
    //将Span元素插入到Barrage中
    Barrage.appendChild(BarrageSpan)
    //将Barrage元素插入到容器元素中
    container.appendChild(Barrage);
    //获取动画时间
    var startTime = (7 + Math.random() * 5);
    // 使用CSS动画来让span元素从右向左移动
    Barrage.style.animation = "BarrageMove " + startTime + "s linear infinite";
    setTimeout(() => {
      container.removeChild(Barrage)
    }, startTime * 1000)
  }).catch(error => {
    message.warning(error)
  })
}

//展示弹幕
function showBarrage(barrage: any) {
  var container = document.getElementsByClassName("leave-header")[0]
  //创建一个div元素
  var Barrage = document.createElement("div");
  //创建一个span元素
  var BarrageSpan = document.createElement("span");
  //创建一个img元素
  var BarrageImg = new Image();
  //设置img图片地址
  if (barrage.userAvater) {
    BarrageImg.src = barrage.userAvater
  } else {
    BarrageImg.src = "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater01.png"
  }
  //设置Barrage的ClassName
  Barrage.className = "Barrage";
  //设置BarrageSpan的ClassName
  BarrageSpan.className = "BarrageSpan";
  //设置BarrageImg的ClassName
  BarrageImg.className = "BarrageImg";
  // 设置其文本内容为参数值
  BarrageSpan.innerText = barrage.content;
  // 设置其的高度
  if (barrage.barrageHeight > container.clientHeight) {
    // 设置其随机的高度
    var BarrageHeight = Math.floor(Math.random() * container.clientHeight);
    if (BarrageHeight < 53) {
      BarrageHeight = 53;
    } else if (BarrageHeight > container.clientHeight - 40) {
      BarrageHeight = container.clientHeight - 40
    }
    Barrage.style.top = BarrageHeight + "px";
  } else {
    //以原来的高度设置
    Barrage.style.top = barrage.barrageHeight + "px";
  }
  // 设置其随机的颜色
  Barrage.style.color = "rgb(" + Math.floor(Math.random() * 256) + "," + Math.floor(Math.random() * 256) + "," + Math.floor(Math.random() * 256) + ")";
  //将Img元素插入到容器元素中
  Barrage.appendChild(BarrageImg)
  //将Span元素插入到Barrage中
  Barrage.appendChild(BarrageSpan)
  //将Barrage元素插入到容器元素中
  container.appendChild(Barrage);
  //获取动画时间
  var startTime = (7 + Math.random() * 5);
  // 使用CSS动画来让span元素从右向左移动
  Barrage.style.animation = "BarrageMove " + startTime + "s linear infinite";
  setTimeout(() => {
    container.removeChild(Barrage)
  }, startTime * 1000)
}

//从数组中随机抽取一条弹幕
function getRandomItem(array: any) {
  if (array.length === 0) {
    return null; // 或者可以抛出错误，如果数组为空且不应该抽取任何内容
  }
  const randomIndex = Math.floor(Math.random() * array.length);
  return array[randomIndex];
}
</script>

<style lang="scss" scoped>
.leave {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  overflow: hidden;

  .leave-header {
    width: 100%;
    height: 100vh;
    background: url("https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/liuyan.png") no-repeat;
    background-size: cover;
    background-attachment: fixed;
    background-position: center;
    position: relative;

    .leave-title {
      position: absolute;
      top: 40%;
      left: 50%;
      transform: translate(-50%, -50%);
      color: #222831;
      font-size: 20px;
    }

    .leave-input {
      position: absolute;
      top: 45%;
      left: 50%;
      transform: translate(-50%, -50%);
      display: flex;
      justify-content: center;
      align-items: center;

      .search-box {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-wrap: nowrap;
        width: 300px;
        height: 30px;
        margin-top: 20px;
        border: #222831 2px solid;
        border-radius: 15px;

        .icon {
          flex: 2;
          width: 1rem;
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

  .leaver-comments {
    width: 60%;
    margin: auto;
  }
}
</style>
