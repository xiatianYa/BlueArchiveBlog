import router from './router'
import {getToken} from '@/utils/auth'
import {useUserStore} from '@/store/user'

router.beforeEach((to, from, next) => {
    //有token
    if (getToken()) {
        const UserStore = useUserStore()
        //获取用户信息 重新加载
        UserStore.SET_USERINFO()
        next()
    } else {
        // 没有token
        next()
    }
})
//路由跳转后
router.afterEach(() => {

})
