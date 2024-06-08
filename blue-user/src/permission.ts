import router from './router'
import { getToken } from '@/utils/auth'
import { useUserStore } from '@/store/user'
import { useSocketStore } from '@/store/socket'
import { useBgStore } from '@/store/bg'
import { listBg } from '@/api/bg'
router.beforeEach((to, from, next) => {
    //背景仓库
    const BgStore = useBgStore()
    //如果没有背景则去查询背景
    if (!BgStore.bgList.length) {
        listBg().then((res) => {
            BgStore.SET_BGLIST(res)
        })
    }
    //没有token
    if (!getToken()) {
        next()
    } else {
        //有Token 我们则查看有没有用户信息
        const UserStore = useUserStore()
        const socketStore = useSocketStore()
        //建立Socket连接
        if (UserStore.id && !socketStore.socket) {
            socketStore.INIT(Number(UserStore.id))
        }
        if (socketStore.socket) {
            //连接成功
            socketStore.socket.onopen = function () {
                console.log("WebSocket 连接成功");
            }
            //连接关闭
            socketStore.socket.onclose = () => {
                //清空Socket
                socketStore.CLEAR_SOCKET();
                console.log("WebSocket 连接关闭");
            };
            //连接失败
            socketStore.socket.onerror = function () {
                //清空Socket
                socketStore.CLEAR_SOCKET();
                console.log('WebSocket 连接失败');
            }
        }
        //没有用户信息
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
