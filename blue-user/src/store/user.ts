import {defineStore} from 'pinia'
import {getToken, removeToken} from '@/utils/auth'
import {getInfo, refreshToken} from '@/api/login'

export const useUserStore = defineStore('user', {
    actions: {
        SET_TOKEN(value: string) {
            this.token = value;
        },
        SET_ID(value: string) {
            this.id = value;
        },
        SET_NAME(value: string) {
            this.name = value;
        },
        SET_AVATAR(value: string) {
            this.avatar = value;
        },
        SET_ROLES(value: []) {
            this.roles = value;
        },
        SET_PERMISSIONS(value: []) {
            this.permissions = value;
        },
        CLEAR_USERINFO() {
            this.id = ""
            this.name = ""
            this.avatar = ""
            this.roles = []
            this.permissions = []
        },
        SET_USERINFO() {
            //获取用户信息
            getInfo().then((res: any) => {
                let user = res.user
                this.id = user.userId
                this.name = user.userName
                this.avatar = user.avatar
                this.roles = res.roles
                this.permissions = res.permissions
            })
            //获取后刷新令牌过期时间
            refreshToken()
        },
        LogOut() {
            this.token = ""
            removeToken()
            alert("退出成功")
        }
    },
    // 真正存储数据的地方
    state() {
        return {
            token: getToken() || "",
            id: '',
            name: '',
            avatar: '',
            roles: [],
            permissions: []
        }
    }
})