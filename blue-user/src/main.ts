//引入App.vue
import App from './App.vue'
//引入vue
import { createApp } from 'vue'

//Github热力图
import VueCalendarHeatmap from 'vue3-calendar-heatmap'

// 引入重置样式
import "@/styles/reset.scss";
// 引入基础样式
import "@/styles/index.scss";
// 引入全局样式
import "@/styles/global.scss";
// 引入全局动画
import "@/styles/animation.scss"
//自定义字体css
import '@/assets/iconfont/iconfont.css'
//自定义字体js
import '@/assets/iconfont/iconfont.js'
// permission control
import './permission'
//adnimate.css 动画库
import 'animate.css';

//Pinia
import { createPinia } from 'pinia'
//路由
import router from './router'

//MarkDown编辑器的相关资源
import VMdEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
//主题
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
//代码行号
import createLineNumbertPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';
//todo-list
import createTodoListPlugin from '@kangc/v-md-editor/lib/plugins/todo-list/index';
import '@kangc/v-md-editor/lib/plugins/todo-list/todo-list.css';
//表情包
import createEmojiPlugin from '@kangc/v-md-editor/lib/plugins/emoji/index';
import '@kangc/v-md-editor/lib/plugins/emoji/emoji.css';
//代码高亮
import createHighlightLinesPlugin from '@kangc/v-md-editor/lib/plugins/highlight-lines/index';


// 引入所有语言包
import hljs from 'highlight.js';

VMdEditor.use(githubTheme, {
  Hljs: hljs,
});
VMdEditor.use(createLineNumbertPlugin());
VMdEditor.use(createTodoListPlugin());
VMdEditor.use(createEmojiPlugin());
VMdEditor.use(createHighlightLinesPlugin());
//创建App实际
const app = createApp(App)


app.use(VueCalendarHeatmap)
app.use(createPinia())
app.use(router)
app.use(VMdEditor);
app.mount('#app')