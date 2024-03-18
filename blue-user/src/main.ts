//全局css
import './assets/main.scss'
//自定义字体css
import '@/assets/iconfont/iconfont.css'
//自定义字体js
import '@/assets/iconfont/iconfont.js'
// permission control
import './permission'
//adnimate.css 动画库
import 'animate.css';


//引入App.vue
import App from './App.vue'
//引入vue
import {createApp} from 'vue'
//引入pinia
import {createPinia} from 'pinia'
//引入路由
import router from './router'


//创建App实际
const app = createApp(App)
//使用Pinia
app.use(createPinia())
//使用路由
app.use(router)
//挂载
app.mount('#app')
