<template>
  <div ref="artRef"></div>
</template>

<script setup>
import Artplayer from 'artplayer';
import {nextTick, onBeforeUnmount, onMounted, ref, watch} from 'vue';
import {addMessage, listMessage} from '@/api/pixivMessage'
import artplayerPluginDanmuku from 'artplayer-plugin-danmuku'
import promptMsg from "@/components/PromptBoxView"

const emit = defineEmits(['get-instance']);

const props = defineProps({
  option: {
    type: Object,
    required: true,
  },
  chaptersIndex: {
    type: Number,
    required: true,
  },
  pixivId: {
    type: Number,
    required: true,
  },
  danmuList: {
    type: Array,
    required: true,
  }
});
const artRef = ref(null);
var art;
onMounted(() => {
  nextTick(() => {
    art = new Artplayer({
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
          // 使用 Promise 异步返回
          danmuku: function () {
            return new Promise((resovle) => {
              const query = {
                pixivId: props.pixivId,
                episodeId: 1,
              }
              listMessage(query).then(res => {
                for (const item of res.rows) {
                  if (item.border === 0) {
                    item.border = true
                  } else {
                    item.border = false
                  }
                }
                return resovle(res.rows);
              });
            });
          },
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
      controls: [
        {
          position: 'right',
          html: '重载弹幕',
          click: function () {
            art.plugins.artplayerPluginDanmuku.load();
          },
        },
      ],
      moreVideoAttr: {
        crossOrigin: 'anonymous',
      },
    });
    art.on('artplayerPluginDanmuku:emit', (danmu) => {
      const border = 0;
      if (!danmu.border) {
        border = 1
      }
      const message = {
        pixivId: props.pixivId,
        episodeId: props.chaptersIndex,
        border: border,
        color: danmu.color,
        mode: danmu.mode,
        text: danmu.text,
        time: danmu.time
      }
      addMessage(message).then(res => {
        promptMsg({ type: "success", msg: "添加成功" })
      })
    });
    emit('get-instance', art);
  });
});

onBeforeUnmount(() => {
  if (art) {
    art.destroy(false);
  }
});
watch(() => props.chaptersIndex, async (newVal, oldVal) => {
  if (oldVal) {
    const query = {
      pixivId: props.pixivId,
      episodeId: newVal,
    }
    let result = await listMessage(query);
    for (const item of result.rows) {
      if (item.border === 0) {
        item.border = true
      } else {
        item.border = false
      }
    }
    art.plugins.artplayerPluginDanmuku.config({
      danmuku: result.rows,
    });
    art.plugins.artplayerPluginDanmuku.load();
  }
})
</script>
