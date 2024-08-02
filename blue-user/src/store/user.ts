import { defineStore } from "pinia";
import { getToken, removeToken } from "@/utils/auth";
import { getInfo, refreshToken } from "@/api/login";
import { type UserType } from "@/types";
export const useUserStore = defineStore("user", {
  // 真正存储数据的地方
  state: (): UserType => {
    return {
      token: getToken() || "",
      id: null,
      nickName: "",
      avatar: "",
      email: "",
      phone: "",
      createTime: "",
      roles: [],
      permissions: [],
    };
  },
  actions: {
    //设置Token
    setToken(value: string) {
      this.token = value;
    },
    //清空用户信息
    clearUserInfo() {
      (this.token = ""),
        (this.id = null),
        (this.nickName = ""),
        (this.avatar = ""),
        (this.email = ""),
        (this.phone = ""),
        (this.createTime = ""),
        (this.roles = []),
        (this.permissions = []);
      removeToken();
    },
    setUserInfo() {
      //获取用户信息
      getInfo().then((res: any) => {
        let user = res.user;
        this.id = user.userId;
        this.nickName = user.nickName;
        this.avatar = user.avatar;
        this.email = user.email;
        this.phone = user.phonenumber;
        this.createTime = user.createTime;
        this.roles = res.roles;
        this.permissions = res.permissions;
      });
      //获取后刷新令牌过期时间
      refreshToken();
    },
    logOut() {
      //清除TOKEN 清除用户消息
      this.id = null;
      this.nickName = "";
      this.avatar = "";
      this.phone = "";
      this.email = "";
      this.createTime = "";
      this.roles = [];
      this.permissions = [];
      this.token = "";
      removeToken();
    },
  },
});
