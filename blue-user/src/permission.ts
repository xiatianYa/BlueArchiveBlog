import router from './router'
import {getToken} from '@/utils/auth'
import {useUserStore} from '@/store/user'
import {useBgStore} from '@/store/bg'

router.beforeEach((to, from, next) => {
    const BgStore = useBgStore()
    //获取背景
    if (BgStore.bgList.length <= 1) {
        BgStore.SET_BGLIST()
    }
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
