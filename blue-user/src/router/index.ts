import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import FoundView from '../views/FoundView.vue'
import SortView from '../views/SortView.vue'
import PhotoView from '../views/PhotoView.vue'
import FriendChainView from '../views/FriendChainView.vue'
import LeaveMessageView from '../views/LeaveMessageView.vue'
import MusicView from '../views/MusicView.vue'
import UserView from '../views/UserView.vue'
import PixivView from '../views/PixivView.vue'
import MenuView from '../views/MenuView.vue'
import LoadingVuew from '../components/LoadingView.vue'
import ArticlePreview from '../components/ArticlePreview.vue'
import ChatView from '../views/ChatView.vue'
import FlvView from '../views/FlvView.vue'
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    scrollBehavior(to, from, savedPosition) {
        // 总是滚动到顶部  
        return { left: 0, top: 0 };
    },
    routes: [
        {
            path: '/',
            name: 'loading',
            component: LoadingVuew,
        },
        {
            path: '/home',
            name: 'home',
            component: HomeView
        },
        {
            path: '/found',
            name: 'found',
            component: FoundView
        },
        {
            path: '/sort',
            name: 'sort',
            component: SortView
        },
        {
            path: '/photo',
            name: 'photo',
            component: PhotoView
        },
        {
            path: '/friend',
            name: 'friend',
            component: FriendChainView
        }, {
            path: '/leave',
            name: 'leave',
            component: LeaveMessageView
        }, {
            path: '/music',
            name: 'music',
            component: MusicView
        }, {
            path: '/user',
            name: 'user',
            component: UserView
        },
        {
            path: '/editPreView',
            name: 'editPreView',
            component: ArticlePreview
        },
        {
            path: '/pixivView',
            name: 'pixivView',
            component: PixivView
        },
        {
            path: '/menu',
            name: 'menu',
            component: MenuView
        },
        {
            path: '/chat',
            name: 'chat',
            component: ChatView
        },
        {
            path: '/flv',
            name: 'flv',
            component: FlvView
        }
    ]
})

export default router
