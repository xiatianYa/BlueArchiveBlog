//引入App.vue
import App from './App.vue'
//引入vue
import {createApp} from 'vue'

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


import {createPinia} from 'pinia'
import router from './router'
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';

import Prism from 'prismjs';


//创建App实际
const app = createApp(App)


app.use(createPinia())
app.use(router)
app.mount('#app')

VueMarkdownEditor.use(vuepressTheme, {
  Prism,
});
app.use(VueMarkdownEditor);