import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import FoundView from '../views/FoundView.vue'
import SortView from '../views/SortView.vue'
import PhotoView from '../views/PhotoView.vue'
import FriendChainView from '../views/FriendChainView.vue'
import LeaveMessageView from '../views/LeaveMessageView.vue'
import SongView from '../views/SongView.vue'
import UserView from '../views/UserView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
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
            path: '/song',
            name: 'song',
            component: SongView
        }, {
            path: '/user',
            name: 'user',
            component: UserView
        }
    ]
})

export default router
