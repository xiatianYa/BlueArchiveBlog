<template>
  <div class="leave animate__animated animate__fadeIn" id="container">
    <div class="leave_header">
      <div class="leave_title">
        留下一条友善的评论吧
      </div>
      <div class="leave_input">
        <div class="search_box">
          <input v-model="BarrageInfo.content" class="search_txt" placeholder="留下点什么吧~" type="text">
          <svg class="icon pointer" aria-hidden="true" @click="addBarrage">
            <use xlink:href="#icon-daohang"></use>
          </svg>
        </div>
      </div>
    </div>
    <!-- <div class="leaver_body" :class="gloBalStore.switch ? 'bg_white' : 'bg_black'">
        <div class="leaver_container">
        </div>
    </div> -->
  </div>
</template>

<script setup>
import {onMounted, onUnmounted, ref} from "vue";
import {useGloBalStore} from '@/store/global'
import {useUserStore} from '@/store/user'
import {addMessage, listMessage} from '@/api/message'

const gloBalStore = useGloBalStore()
const timer = ref([])
// 设置定时器  
const startTimer = () => {
  let result = []
  listMessage().then(res => {
    result = res.rows;
  })
  timer.value.push(setInterval(() => {
    let barrage = getRandomItem(result)
    showBarrage(barrage)
  }, 1000))
};

// 清除定时器  
const clearTimer = () => {
  for (let index = 0; index < timer.value.length; index++) {
    clearInterval(timer.value[index])
  }
};
//自定义加载弹幕
onMounted(() => {
  startTimer();
})
//销毁时清理定时器
onUnmounted(() => {
  clearTimer();
})
// 定义弹幕数据
const BarrageInfo = ref({
  userId: "",
  content: "",
  barrageHeight: 0,
})
const UserStore = useUserStore()
//添加弹幕函数
function addBarrage() {
  var container = document.getElementsByClassName("leave_header")[0]
  //创建一个div元素
  var Barrage = document.createElement("div");
  //创建一个span元素
  var BarrageSpan = document.createElement("span");
  //创建一个img元素
  var BarrageImg = new Image();
  //设置img图片地址
  BarrageImg.src = UserStore.avatar || "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater01.png";
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
  } else if (BarrageHeight > container.clientHeight - 30) {
    BarrageHeight = container.clientHeight - 30
  }
  Barrage.style.top = BarrageHeight + "px";

  //添加到弹幕表中
  BarrageInfo.value.userId = UserStore.id || 0;
  BarrageInfo.value.userAvater = UserStore.avatar || "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater01.png";
  BarrageInfo.value.barrageHeight = BarrageHeight;
  addMessage(BarrageInfo.value)
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
}

//展示弹幕
function showBarrage(barrage) {
  var container = document.getElementsByClassName("leave_header")[0]
  //创建一个div元素
  var Barrage = document.createElement("div");
  //创建一个span元素
  var BarrageSpan = document.createElement("span");
  //创建一个img元素
  var BarrageImg = new Image();
  //设置img图片地址
  BarrageImg.src = barrage.userAvater || "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater01.png";
  //设置Barrage的ClassName
  Barrage.className = "Barrage";
  //设置BarrageSpan的ClassName
  BarrageSpan.className = "BarrageSpan";
  //设置BarrageImg的ClassName
  BarrageImg.className = "BarrageImg";
  // 设置其文本内容为参数值
  BarrageSpan.innerText = barrage.content;
  // 设置其的高度
  Barrage.style.top = barrage.barrageHeight + "px";
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
function getRandomItem(array) {
  if (array.length === 0) {
    return null; // 或者可以抛出错误，如果数组为空且不应该抽取任何内容  
  }
  const randomIndex = Math.floor(Math.random() * array.length);
  return array[randomIndex];
}  
</script>

<style lang="scss" scoped>
.leave {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  overflow: hidden;
  background: url("/static/images/liuyan.png") no-repeat;
  background-size: 100% 100%;
  background-attachment: fixed;

  .leave_header {
    width: 100%;
    height: 100vh;

    .leave_title {
      position: absolute;
      top: 35%;
      left: 50%;
      transform: translate(-50%, -50%);
      color: #222831;
      font-size: 20px;
    }

    .leave_input {
      position: absolute;
      top: 40%;
      left: 50%;
      transform: translate(-50%, -50%);
      display: flex;
      justify-content: center;
      align-items: center;

      .search_box {
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

  .leaver_body {
    display: flex;
    justify-content: center;
    width: 100%;
    height: 500px;
    background: url("/static/images/loading_bg.png") no-repeat;
    background-size: 100% 100%;
    background-attachment: fixed;

    .leaver_container {
      width: 70%;
    }
  }
}
</style>@/store/global
