<template>
  <div class="user">
    <div class="container animate__animated animate__zoomIn">
      <div class="form-box">
        <!-- 注册 -->
        <div class="register-box hidden">
          <h1>注册</h1>
          <div class="avater_box">
            <n-avatar :size="48" round :src="userInfo.avatar"></n-avatar>
            <a class="pointer avater_select" @click="selectAvatar(openAvater)">选择头像</a>
          </div>
          <n-space vertical>
            <n-input type="text" placeholder="用户名" v-model:value="userInfo.userName" />
            <n-input type="text" placeholder="用户昵称" v-model:value="userInfo.nickName" />
            <n-input type="password" show-password-on="mousedown" placeholder="密码" v-model:value="userInfo.passWord" />
            <n-input type="password" show-password-on="mousedown" placeholder="确认密码"
              v-model:value="userInfo.entryPassword" />
            <div class="Sms_box">
              <n-input type="text" placeholder="请填写手机号" autosize style="min-width: 70%"
                v-model:value="userInfo.phone" />
              <a class="pointer code_select" @click="getPhoneCode()">发送</a>
            </div>
            <n-input type="password" show-password-on="mousedown" placeholder="请输入验证码" v-model:value="userInfo.sms" />
          </n-space>
          <button @click="userRegister" v-show="!loading">注册</button>
          <button v-show="loading">
            <Loading />
          </button>
        </div>
        <!-- 登录 -->
        <div class="login-box">
          <h1>登录</h1>
          <n-space vertical>
            <n-input type="text" placeholder="用户名" v-model:value="userInfo.userName" />
            <n-input type="password" show-password-on="mousedown" placeholder="密码" v-model:value="userInfo.passWord" />
            <n-input type="text" placeholder="请输入验证码" v-model:value="userInfo.code" />
            <n-image width="100%" :src="codeImg" preview-disabled @click="getCode" />
          </n-space>
          <button @click="userLogin" v-show="!loading">登录</button>
          <button v-show="loading">
            <Loading />
          </button>
        </div>
      </div>
      <div class="con-box left">
        <h2><span>BlueAchiveBlog</span></h2>
        <p>快来<span>注册</span>吧</p>
        <img src="https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/zi.png" alt="">
        <p>已有账号</p>
        <button id="login" @click="goLogin">去登录</button>
      </div>
      <div class="con-box right">
        <h2><span>BlueAchiveBlog</span></h2>
        <p>快来<span>登录</span>吧</p>
        <img src="https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/xiaocun.png" alt="">
        <p>没有账号？</p>
        <button id="register" @click="goRegister">去注册</button>
      </div>
      <div class="selectAvater animate__animated animate__fadeInLeft" v-show="openAvater">
        <n-avatar class="n-avatar" v-for="(imgUrl, index) in avaterUrls" :key="index" :size="48" :src="imgUrl.avaterUrl"
          round @click="exchangeAvater(imgUrl.avaterUrl)" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { getCodeImg, getSms, login, register } from '@/api/login'
import { listAvater } from '@/api/avater'
import { setExpiresIn, setToken } from '@/utils/auth'
import { useRouter } from "vue-router";
import { useMessage, NInput, NSpace, NImage, NAvatar } from 'naive-ui'
let { userStore } = useStore()
import useStore from "@/store"
let { globalStore } = useStore()
import Loading from '@/components/CssLoadingView02.vue'


// 定义UserInfo的类型  
interface UserInfoType {
  userName: string;
  nickName: string;
  passWord: string;
  entryPassword: string;
  avatar: string;
  phone: string;
  sms: string;
  code: string,
  uuid: string,
}
//提示框
const message = useMessage()
//路由
const router = useRouter()
//是否打开头像选择
let openAvater = ref(false)
//存储头像的地址列表
let avaterUrls: any = ref([])
//验证码
let codeImg = ref("")
//用户信息
let userInfo = ref<UserInfoType>({
  userName: "",
  nickName: "",
  passWord: "",
  entryPassword: "",
  avatar: "",
  phone: "",
  sms: "",
  code: "",
  uuid: "",
})
//是否加载Loading
const loading = ref(false)
onMounted(() => {
  //获取用户头像
  listAvater({ pageSize: 999 }).then((res: any) => {
    avaterUrls.value = res.rows
  })
  getCode()
})
//获取注册短信验证码
function getPhoneCode() {
  getSms(userInfo.value.phone).then(res => {
    message.success(res.data)
  }).catch(error => {
    message.error(error)
  })
}
//用户选择头像
function selectAvatar(isOpen: any) {
  openAvater.value = !isOpen
}

//获取验证码
function getCode() {
  getCodeImg().then((res: any) => {
    codeImg.value = 'data:image/gif;base64,' + res.img
    userInfo.value.uuid = res.uuid
  })
}
//用户登录
function userLogin() {
  loading.value = true;
  const username = userInfo.value.userName.trim()
  const password = userInfo.value.passWord
  const code = userInfo.value.code
  const uuid = userInfo.value.uuid

  login(username, password, code, uuid).then(res => {
    let result = res.data
    //设置Token
    setToken(result.access_token)
    //设置Token过期时间
    setExpiresIn(result.expires_in)
    //设置本地仓库Token
    userStore.setToken(result.access_token)
    //提示用户信息
    message.success("登录成功")
    //初始化socket连接
    globalStore.initSocket();
    //关闭加载
    loading.value = false;
    router.push({ path: "/menu" })
  }).catch(error => {
    //提示用户信息
    message.error(error)
    //关闭加载
    loading.value = false;
    getCode()
  })
}
//用户注册
function userRegister() {
  loading.value = true;
  register(userInfo).then(res => {
    clearUserInfo();
    goLogin();
    message.success("注册成功")
    loading.value = false;
  }).catch(error => {
    message.error(error)
    loading.value = false;
  })
}
//清空表单
function clearUserInfo() {
  userInfo.value = {
    userName: "",
    nickName: "",
    passWord: "",
    entryPassword: "",
    avatar: "",
    phone: "",
    sms: "",
    code: "",
    uuid: "",
  }
}
//前往登录
function goLogin() {
  clearUserInfo()
  const formBox: any = document.querySelector('.form-box');
  const registerBox: any = document.querySelector('.register-box');
  const loginBox: any = document.querySelector('.login-box');
  const logBotton: any = loginBox.querySelector("button");
  logBotton.style.color = "#FFC7C5"
  formBox.style.backgroundColor = "#FFC7C5";
  formBox.style.transform = 'translateX(0%)';
  registerBox.classList.add('hidden');
  loginBox.classList.remove('hidden');
}

//切换用户头像
function exchangeAvater(imgUrl: any) {
  userInfo.value.avatar = imgUrl
  openAvater.value = false
}

//前往注册
function goRegister() {
  clearUserInfo()
  const formBox: any = document.querySelector('.form-box');
  const registerBox: any = document.querySelector('.register-box');
  const loginBox: any = document.querySelector('.login-box');
  const regBotton: any = registerBox.querySelector("button");

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
      top: -20%;
      left: 5%;
      width: 320px;
      height: 600px;
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
      justify-content: space-between;
      align-items: center;
      width: 50%;
      height: 100%;
      z-index: 9;
      background-color: #fff;
      border-radius: 5px;
      overflow-y: auto;

      .n-avatar {
        margin: 10px;
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

  button {
    display: flex;
    justify-content: center;
  }

  .Sms_box {
    display: flex;
    align-items: center;

    .code_select {
      text-align: center;
      flex-grow: 1;
      color: #fff;
    }
  }

  .avater_box {
    display: flex;
    justify-content: center;
    width: 100%;
    height: 60px;

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
      margin-left: 10px;
      border-radius: 10px;
      width: 80px;
      object-fit: cover;
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
