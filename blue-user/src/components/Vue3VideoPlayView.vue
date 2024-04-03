<template>
  <div class="video">
    <vue3VideoPlay width="auto" title="钢铁侠" :src="src" :poster="poster" @play="onPlay" @pause="onPause"
      @timeupdate="onTimeupdate" @canplay="onCanplay" :ligthOff="true" :control="options.control" />
  </div>

</template>


<script setup>
import {reactive} from 'vue';
//视频地址 和 封面
let props = defineProps(['src','poster'])
//video配置项
const options = reactive({
  control: true,
})
const onPlay = (ev) => {

}
const onPause = (ev) => {

}

const onTimeupdate = (ev) => {

}
const onCanplay = (ev) => {

}
</script>
