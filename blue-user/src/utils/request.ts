import axios from 'axios'
import { getToken } from '@/utils/auth'
import { useUserStore } from '@/store/user'
import promptMsg from "@/components/PromptBoxView"

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: "/api",
    // 超时
    timeout: 20000,
    //重试次数

})

// request拦截器
service.interceptors.request.use(config => {
    // 是否需要设置 token
    const isToken = (config.headers || {}).isToken === false
    if (getToken() && !isToken) {
        config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return config
}, error => {
    Promise.reject(error)
})

// 响应拦截器
service.interceptors.response.use(res => {
    const UserStore = useUserStore()
    // 未设置状态码则默认成功状态
    const code: number = res.data.code || 200;
    // 获取错误信息
    const msg = res.data.msg
    if (code === 401) {
        //验证码失效 清空用户数据 和Token 前往登录页面
        UserStore.CLEAR_USERINFO()
        //提示信息
        promptMsg({ type: "warn", msg: "用户信息获取失败,请重新登录!" })
        return Promise.reject(msg)
    } else if (code === 500) {
        return Promise.reject(msg)
    } else if (code === 601) {
        return Promise.reject(msg)
    } else if (code !== 200) {
        return Promise.reject(msg)
    } else {
        return res.data
    }
},
    error => {
        let { message } = error;
        if (message == "Network Error") {
            message = "后端接口连接异常";
        } else if (message.includes("timeout")) {
            message = "系统接口请求超时";
        } else if (message.includes("Request failed with status code")) {
            message = "系统接口" + message.substr(message.length - 3) + "异常";
        }
        return Promise.reject(error)
    }
)

export default service
