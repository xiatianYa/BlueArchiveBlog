//引入App.vue
import App from './App.vue'
//引入vue
import {createApp} from 'vue'

//引入图片懒加载插件
import Lazyload from "vue3-lazyload";
//Github热力图
import VueCalendarHeatmap from 'vue3-calendar-heatmap'

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

// v-md-editor 编辑器的相关资源
import Codemirror from 'codemirror';
// mode
import 'codemirror/mode/markdown/markdown';
import 'codemirror/mode/javascript/javascript';
import 'codemirror/mode/css/css';
import 'codemirror/mode/htmlmixed/htmlmixed';
import 'codemirror/mode/vue/vue';
// edit
import 'codemirror/addon/edit/closebrackets';
import 'codemirror/addon/edit/closetag';
import 'codemirror/addon/edit/matchbrackets';
// placeholder
import 'codemirror/addon/display/placeholder';
// active-line
import 'codemirror/addon/selection/active-line';
// scrollbar
import 'codemirror/addon/scroll/simplescrollbars';
import 'codemirror/addon/scroll/simplescrollbars.css';
// style
import 'codemirror/lib/codemirror.css';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
//快捷复制代码
import createCopyCodePlugin from '@kangc/v-md-editor/lib/plugins/copy-code/index';
import '@kangc/v-md-editor/lib/plugins/copy-code/copy-code.css';
//任务列表
import createTodoListPlugin from '@kangc/v-md-editor/lib/plugins/todo-list/index';
import '@kangc/v-md-editor/lib/plugins/todo-list/todo-list.css';
//表情
import createEmojiPlugin from '@kangc/v-md-editor/lib/plugins/emoji/index';
import '@kangc/v-md-editor/lib/plugins/emoji/emoji.css';
//提示信息
import createTipPlugin from '@kangc/v-md-editor/lib/plugins/tip/index';
import '@kangc/v-md-editor/lib/plugins/tip/tip.css';
// 代码行号
import createLineNumbertPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';
import {createPinia} from 'pinia'
import router from './router'
import VueMarkdownEditor from '@kangc/v-md-editor';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import Prism from 'prismjs';


VueMarkdownEditor.use(vuepressTheme, {
  Prism,
});
VueMarkdownEditor.Codemirror = Codemirror;
VueMarkdownEditor.use(createLineNumbertPlugin());
VueMarkdownEditor.use(createCopyCodePlugin())
VueMarkdownEditor.use(createTodoListPlugin())
VueMarkdownEditor.use(createEmojiPlugin())
VueMarkdownEditor.use(createTipPlugin())
//创建App实际
const app = createApp(App)

//注册插件
app.use(Lazyload, {
  loading: "https://edu-9556.oss-cn-hangzhou.aliyuncs.com/BlueAchive/config/loading.png",//可以指定加载中的图像
});

app.use(VueCalendarHeatmap)
app.use(createPinia())
app.use(router)
app.use(VueMarkdownEditor);
app.mount('#app')