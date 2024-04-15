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
            this.nickName = value;
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
            this.nickName = ""
            this.avatar = ""
            this.roles = []
            this.permissions = []
        },
        SET_USERINFO() {
            //获取用户信息
            getInfo().then((res: any) => {
                let user = res.user
                this.id = user.userId
                this.nickName = user.nickName
                this.avatar = user.avatar
                this.email=user.email
                this.phone=user.phonenumber
                this.createTime=user.createTime
                this.roles = res.roles
                this.permissions = res.permissions
            })
            //获取后刷新令牌过期时间
            refreshToken()
        },
        LogOut() {
            //清除TOKEN 清除用户消息
            this.id=""
            this.nickName=""
            this.avatar=""
            this.phone=""
            this.email=""
            this.createTime=""
            this.roles=[]
            this.permissions=[]
            this.token = ""
            removeToken()
        }
    },
    // 真正存储数据的地方
    state() {
        return {
            token: getToken() || "",
            id: '',
            nickName: '',
            avatar: '',
            email:'',
            phone:'',
            createTime:'',
            roles: [],
            permissions: []
        }
    }
})