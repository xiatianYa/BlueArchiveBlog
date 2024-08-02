import request from "@/utils/request";

// 登录方法
export function login(username: any, password: any, code: any, uuid: any) {
  return request({
    url: "/auth/login",
    headers: {
      isToken: false,
      repeatSubmit: false,
    },
    method: "post",
    data: { username, password, code, uuid },
  });
}

// 注册方法
export function register(data: any) {
  return request({
    url: "/auth/userRegister",
    headers: {
      isToken: false,
    },
    method: "post",
    data: data,
  });
}

// 刷新方法
export function refreshToken() {
  return request({
    url: "/auth/refresh",
    method: "post",
  });
}

// 获取用户详细信息
export function getInfo() {
  return request({
    url: "/system/user/getInfo",
    method: "get",
  });
}

// 退出方法
export function logout() {
  return request({
    url: "/auth/logout",
    method: "delete",
  });
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: "/code",
    headers: {
      isToken: false,
    },
    method: "get",
  });
}
// 获取短信验证码
export function getSms(phone: any) {
  return request({
    url: "/auth/code",
    headers: {
      isToken: false,
    },
    method: "post",
    data: phone,
  });
}

//用户第三方登录
export function oauthLogin(data: any) {
  return request({
    url: "/auth/oauth2/login",
    method: "post",
    data: data,
  });
}
