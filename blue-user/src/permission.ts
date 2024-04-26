import router from './router'
import {getToken} from '@/utils/auth'
import {useUserStore} from '@/store/user'

router.beforeEach((to, from, next) => {
    //没有token
    if (!getToken()) {
        next()
    } else {
        //有Token 我们则查看有没有用户信息
        const UserStore = useUserStore()
        //没有用户信息 我们就去查询
        if (!UserStore.id) {
            //获取用户信息 重新加载
            UserStore.SET_USERINFO()
            next()
        }
        //如果有我们就直接跳过
        next();
    }
})
//路由跳转后
router.afterEach((next) => {
})
