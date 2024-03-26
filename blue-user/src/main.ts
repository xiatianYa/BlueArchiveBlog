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

// codemirror 编辑器的相关资源
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
//创建App实际
const app = createApp(App)


app.use(createPinia())
app.use(router)
app.mount('#app')
app.use(VueMarkdownEditor);