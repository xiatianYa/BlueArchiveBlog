import router from './router'
import {getToken} from '@/utils/auth'
import {useUserStore} from '@/store/user'
import {listBg} from '@/api/bg'
import {useBgStore} from '@/store/bg'

router.beforeEach((to, from, next) => {
    //背景仓库
    const BgStore = useBgStore()
    if (BgStore.bgList.length <= 1) {
        listBg().then((res: any) => {
            BgStore.SET_BGLIST(res)
        })
    }
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
router.afterEach(() => {

})
