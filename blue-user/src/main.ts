import './assets/main.scss'
import '@/assets/iconfont/iconfont.css'
import '@/assets/iconfont/iconfont.js'
import './permission' // permission control
import 'animate.css';
import {createApp} from 'vue'
import {createPinia} from 'pinia'

import App from './App.vue'
import router from './router'

const app = createApp(App)
app.use(createPinia())
app.use(router)
app.mount('#app')
