<template>
  <div class="preview">
    <div class="preview_left">
      <div v-for="anchor in titles" :style="{ padding: `10px 0 10px ${anchor.indent * 20}px` }"
        @click="handleAnchorClick(anchor)">
        <a style="cursor: pointer">{{ anchor.title }}</a>
      </div>
    </div>
    <div class="preview_right">
      <v-md-editor v-model="text" mode="preview" height="100%" ref="preview"></v-md-editor>
    </div>
  </div>

</template>

<script setup>
import {onMounted, ref} from 'vue'

const text = ref(`
# heading 1
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent

## heading 2
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent

### heading 3
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent

## heading 2
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent

### heading 3
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
### heading 4
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
### heading 5
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
contentcontentcontent
`)
const titles = ref([])
const preview = ref()
onMounted(() => {
  const anchors = document.querySelectorAll('h1,h2,h3,h4,h5,h6');
  const titleList = Array.from(anchors).filter((title) => !!title.innerText.trim());

  if (!titleList.length) {
    titles.value = [];
    return;
  }

  const hTags = Array.from(new Set(titleList.map((title) => title.tagName))).sort();

  titles.value = titleList.map((el) => ({
    title: el.innerText,
    lineIndex: el.getAttribute('data-v-md-line'),
    indent: hTags.indexOf(el.tagName),
  }));
})
function handleAnchorClick(anchor) {
  const { lineIndex } = anchor
  const heading = document.querySelector(`[data-v-md-line="${lineIndex}"]`)
  if (heading) {
    const headingRect = heading.getBoundingClientRect();
    const offset = 0;
    window.scrollTo({
      top: headingRect.top + window.scrollX + offset,
      behavior: 'smooth'
    });
  }
}
</script>
<style lang="scss" scoped>
.preview {
  width: 100%;
  height: 100%;
  padding-top: 60px;
  padding-bottom: 40px;
  min-height: 100vh;
  display: flex;
  flex-wrap: nowrap;

  .preview_left {
    width: 50%;
    height: 100%;
    background: red;
  }

  .preview_right {
    width: 50%;
    height: 100%;
  }
}
</style>
