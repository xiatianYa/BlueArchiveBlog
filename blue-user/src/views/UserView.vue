<template>
  <div class="user">
    <div class="container animate__animated animate__zoomIn">
      <div class="form-box">
        <!-- 注册 -->
        <div class="register-box hidden">
          <h1>注册</h1>
          <div class="avater_box">
            <img :src="userInfo.avater">
            <a class="pointer avater_select" @click="selectAvater(openAvater)">选择头像</a>
          </div>
          <input type="text" placeholder="用户名">
          <input type="email" placeholder="邮箱">
          <input type="password" placeholder="密码">
          <input type="password" placeholder="确认密码">
          <button>注册</button>
        </div>
        <!-- 登录 -->
        <div class="login-box">
          <h1>登录</h1>
          <input type="text" placeholder="用户名" v-model="userInfo.username">
          <input type="password" placeholder="密码" v-model="userInfo.password">
          <div class="verification">
            <input type="text" placeholder="请输入验证码" v-model="userInfo.code">
            <img :src="'data:image/gif;base64,'+codeImg" @click="getCode">
          </div>
          <button @click="userLogin">登录</button>
        </div>
      </div>
      <div class="con-box left">
        <h2><span>BlueAchiveBlog</span></h2>
        <p>快来<span>注册</span>吧</p>
        <img src="/static/images/zi.png" alt="">
        <p>已有账号</p>
        <button id="login" @click="goLogin">去登录</button>
      </div>
      <div class="con-box right">
        <h2><span>BlueAchiveBlog</span></h2>
        <p>快来<span>登录</span>吧</p>
        <img src="/static/images/xiaocun.png" alt="">
        <p>没有账号？</p>
        <button id="register" @click="goRegister">去注册</button>
      </div>
      <div class="selectAvater animate__animated animate__fadeInLeft" v-show="openAvater">
        <img :src="imgUrl" v-for="imgUrl, index in avaterUrls" :key="index" @click="exchangeAvater(imgUrl)">
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref, reactive, onMounted} from 'vue'
import {login, getCodeImg} from '@/api/login'

let openAvater = ref(false)
let avaterUrls = ref([
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater01.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater02.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater03.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater04.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater05.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater06.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater07.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater08.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater09.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater10.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater11.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater12.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater13.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater14.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater15.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater16.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater17.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater18.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater19.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater20.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater01.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater02.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater03.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater04.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater05.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater06.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater07.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater08.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater09.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater10.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater11.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater12.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater13.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater14.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater15.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater16.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater17.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater18.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater19.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater20.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater01.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater02.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater03.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater04.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater05.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater06.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater07.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater08.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater09.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater10.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater11.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater12.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater13.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater14.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater15.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater16.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater17.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater18.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater19.png",
  "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater20.png",
])
let codeImg = ref([""])
let userInfo = reactive({
  username: "",
  password: "",
  code: "",
  uuid: "",
  avater: "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/UserAvater/Pictures/avater45.png"
})
onMounted(() => {
  getCode()
})

//用户选择头像
function selectAvater(isOpen) {
  openAvater.value = !isOpen
}

//获取验证码
async function getCode() {
  let result = await getCodeImg()
  codeImg.value = result.data.img
  userInfo.uuid = result.data.uuid
}

async function userLogin() {
  let result = await login(userInfo.username, userInfo.password, userInfo.code, userInfo.uuid)
}

//前往登录
function goLogin() {
  var formBox = document.querySelector('.form-box');
  var registerBox = document.querySelector('.register-box');
  var loginBox = document.querySelector('.login-box');
  var logBotton = loginBox.querySelector("button");
  logBotton.style.color = "#FFC7C5"
  formBox.style.backgroundColor = "#FFC7C5";
  formBox.style.transform = 'translateX(0%)';
  registerBox.classList.add('hidden');
  loginBox.classList.remove('hidden');
}

//切换用户头像
function exchangeAvater(imgUrl) {
  userInfo.avater = imgUrl
  openAvater.value = false
}

//前往注册
function goRegister() {
  var formBox = document.querySelector('.form-box');
  var registerBox = document.querySelector('.register-box');
  var loginBox = document.querySelector('.login-box');
  var regBotton = registerBox.querySelector("button");

  regBotton.style.color = "#C6D3FE";
  formBox.style.backgroundColor = "#C6D3FE";
  formBox.style.transform = 'translateX(80%)';
  loginBox.classList.add('hidden');
  registerBox.classList.remove('hidden');
}
</script>

<style lang="scss" scoped>
.user {
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  padding-bottom: 40px;
  background: url("/static/images/loading_bg.png") no-repeat;
  background-size: 100% 100%;
  background-attachment: fixed;

  .container {
    background-color: #fff;
    width: 650px;
    height: 415px;
    border-radius: 5px;
    /* 阴影 */
    box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.1);
    /* 相对定位 */
    position: relative;

    .form-box {
      /* 绝对定位 */
      position: absolute;
      top: -10%;
      left: 5%;
      width: 320px;
      height: 500px;
      border-radius: 5px;
      box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
      display: flex;
      justify-content: center;
      align-items: center;
      z-index: 2;
      /* 动画过渡 加速后减速 */
      transition: 1s ease-in-out;
      background-color: #FFC7C5;
    }

    .con-box.left {
      left: -2%;
    }

    .con-box.right {
      right: -2%;
    }

    .selectAvater {
      position: absolute;
      display: flex;
      flex-wrap: wrap;
      justify-content: space-around;
      align-items: center;
      width: 50%;
      height: 100%;
      z-index: 10;
      background-color: #fff;
      border-radius: 5px;
      overflow-y: auto;

      img {
        width: 60px;
        height: 60px;
        border-radius: 50%;
        padding: 5px 5px 5px 0;
        object-fit: cover;
      }
    }
  }
}


.register-box,
.login-box {
  /* 弹性布局 垂直排列 */
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;

  .avater_box {
    display: flex;
    justify-content: center;
    width: 100%;
    height: 60px;

    img {
      margin-right: 30px;
      border-radius: 50%;
    }

    .avater_select {
      display: flex;
      align-items: center;
      margin-left: 30px;
      height: 100%;
      font-size: 16px;
      color: #fff;
    }
  }

  .verification {
    box-sizing: border-box;
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 0 10% 0 15%;

    input {
      width: 90%;
    }

    img {
      padding-left: 10px;
      width: 100px;
    }
  }

}

.hidden {
  display: none;
  transition: 1s;
}

h1 {
  text-align: center;
  margin-bottom: 25px;
  /* 大写 */
  text-transform: uppercase;
  color: #fff;
  /* 字间距 */
  letter-spacing: 5px;
}

input {
  background-color: transparent;
  width: 70%;
  color: #fff;
  border: none;
  /* 下边框样式 */
  border-bottom: 1px solid rgba(255, 255, 255, 0.4);
  padding: 10px 0;
  text-indent: 10px;
  margin: 8px 0;
  font-size: 14px;
  letter-spacing: 2px;
}

input::placeholder {
  color: #fff;
}

input:focus {
  color: #fff;
  outline: none;
  border-bottom: 1px solid #e3fdfd;
  transition: 0.5s;
}

input:focus::placeholder {
  opacity: 0;
}

.form-box button {
  width: 70%;
  margin-top: 35px;
  background-color: #f6f6f6;
  outline: none;
  border-radius: 8px;
  padding: 13px;
  letter-spacing: 2px;
  border: none;
  cursor: pointer;
}

.form-box button:hover {
  background-color: #fc5185;
  color: #f6f6f6;
  transition: background-color 0.5s ease;
}

.con-box {
  width: 50%;
  /* 弹性布局 垂直排列 居中 */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* 绝对定位 居中 */
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
}


.con-box h2 {
  color: #8e9aaf;
  font-size: 25px;
  font-weight: bold;
  letter-spacing: 3px;
  text-align: center;
  margin-bottom: 4px;
}

.con-box p {
  font-size: 12px;
  letter-spacing: 2px;
  color: #8e9aaf;
  text-align: center;
}

.con-box.right span {
  color: #FFC7C5;
}

.con-box.left span {
  color: #C6D3FE;
}

.con-box img {
  width: 150px;
  height: 150px;
  opacity: 0.9;
  margin: 40px 0;
  object-fit: cover;
  border-radius: 50%;
}

.con-box.right button {
  margin-top: 3%;
  background-color: #fff;
  color: #FFC7C5;
  border: 1px solid #FFC7C5;
  padding: 6px 10px;
  border-radius: 5px;
  letter-spacing: 1px;
  outline: none;
  cursor: pointer;
}

.con-box.left button {
  margin-top: 3%;
  background-color: #fff;
  color: #C6D3FE;
  border: 1px solid #C6D3FE;
  padding: 6px 10px;
  border-radius: 5px;
  letter-spacing: 1px;
  outline: none;
  cursor: pointer;
}

.con-box button:hover {
  background-color: #d3b7d8;
  color: #fff;
}
</style>
