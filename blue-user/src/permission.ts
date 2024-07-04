import router from './router'
import { getToken } from '@/utils/auth'
import useStore from "@/store"

router.beforeEach((to, from, next) => {
    let { userStore } = useStore()
    //没有token
    if (!getToken()) {
        //判断用户当前路由 如果需要校权 则返回登录页
        next()
    } else {
        //没有用户信息
        if (!userStore.id) {
            //获取用户信息 重新加载
            userStore.setUserInfo()
            next()
        }
        //如果有我们就直接跳过
        next();
    }
})
//路由跳转后
router.afterEach((next) => {
    let { globalStore } = useStore()
    //重新获取背景仓库
    globalStore.setBgList();
})
