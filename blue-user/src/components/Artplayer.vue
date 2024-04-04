<template>
  <div ref="artRef"></div>
</template>

<script setup>
import Artplayer from 'artplayer';
import {nextTick, onBeforeUnmount, onMounted, ref} from 'vue';
import artplayerPluginDanmuku from 'artplayer-plugin-danmuku'

const emit = defineEmits(['get-instance']);

const props = defineProps({
  option: {
    type: Object,
    required: true,
  },
});

const instance = ref(null);
const artRef = ref(null);
onMounted(() => {
  instance.value = new Artplayer({
    ...props.option,
    container: artRef.value,
    volume: 0.5,
    isLive: false,
    muted: false,
    autoplay: false,
    pip: true,
    screenshot: true,
    setting: true,
    loop: false,
    lock: true,
    hotkey: true,
    miniProgressBar: true,
    flip: true,
    playbackRate: true,
    aspectRatio: true,
    fullscreen: true,
    mutex: true,
    theme: '#ffad00',
    lang: navigator.language.toLowerCase(),
    airplay: true,
    plugins: [
      artplayerPluginDanmuku({
        danmuku: [
          {
            text: '111', // 弹幕文本
            time: 1, // 发送时间，单位秒
            color: '#fff', // 弹幕局部颜色
            border: false, // 是否显示描边
            mode: 0, // 弹幕模式: 0表示滚动, 1静止
          },
          {
            text: '222',
            time: 2,
            color: 'red',
            border: true,
            mode: 0,
          },
          {
            text: '333',
            time: 3,
            color: 'green',
            border: false,
            mode: 1,
          },
        ],
        speed: 5, // 弹幕持续时间，单位秒，范围在[1 ~ 10]
        opacity: 1, // 弹幕透明度，范围在[0 ~ 1]
        fontSize: 25, // 字体大小，支持数字和百分比
        color: '#FFFFFF', // 默认字体颜色
        mode: 0, // 默认模式，0-滚动，1-静止
        margin: [10, '25%'], // 弹幕上下边距，支持数字和百分比
        antiOverlap: true, // 是否防重叠
        useWorker: true, // 是否使用 web worker
        synchronousPlayback: false, // 是否同步到播放速度
        filter: (danmu) => danmu.text.length < 50, // 弹幕过滤函数，返回 true 则可以发送
        lockTime: 5, // 输入框锁定时间，单位秒，范围在[1 ~ 60]
        maxLength: 100, // 输入框最大可输入的字数，范围在[0 ~ 500]
        minWidth: 200, // 输入框最小宽度，范围在[0 ~ 500]，填 0 则为无限制
        maxWidth: 600, // 输入框最大宽度，范围在[0 ~ Infinity]，填 0 则为 100% 宽度
        theme: 'light', // 输入框自定义挂载时的主题色，默认为 dark，可以选填亮色 light
        heatmap: true, // 是否开启弹幕热度图, 默认为 false
        beforeEmit: (danmu) => !!danmu.text.trim(), // 发送弹幕前的自定义校验，返回 true 则可以发送

        // 通过 mount 选项可以自定义输入框挂载的位置，默认挂载于播放器底部，仅在当宽度小于最小值时生效
        // mount: document.querySelector('.artplayer-danmuku'),
      }),
    ],
    moreVideoAttr: {
      crossOrigin: 'anonymous',
    },
    contextmenu: [
      {
        html: 'Custom menu',
        click: function (contextmenu) {
          console.info('You clicked on the custom menu');
          contextmenu.show = false;
        },
      },
    ],
  });
  nextTick(() => {
    emit('get-instance', instance.value);
    instance.value.on('artplayerPluginDanmuku:emit', (danmu) => {
      console.info('新增弹幕', danmu);
    });
  });
});

onBeforeUnmount(() => {
  if (instance.value) {
    instance.value.destroy(false);
  }
});
</script>
