<template>
  <div class="menu">
    <div class="menu-left">
      <n-menu :options="menuOptions" />
    </div>
    <div class="menu-right">
      <RouterView></RouterView>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { Component } from 'vue'
import { h } from 'vue'
import { NMenu, NIcon } from 'naive-ui';
import type { MenuOption } from 'naive-ui'
import { RouterLink } from 'vue-router'
import {
  PersonOutline as PersonIcon,
  FileTrayOutline as FileTrayIcon,
  ImagesOutline as ImagesIcon,
  HeadsetOutline as HeadsetIcon,
  ShareOutline as ShareIcon
} from '@vicons/ionicons5'

function renderIcon(icon: Component) {
  return () => h(NIcon, null, { default: () => h(icon) })
}

const menuOptions: MenuOption[] = [
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: 'menuUser',
            params: {
              lang: 'zh-CN'
            }
          }
        },
        { default: () => '个人信息' }
      ),
    key: 'menuUser',
    icon: renderIcon(PersonIcon)
  },
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: 'menuArticle',
            params: {
              lang: 'zh-CN'
            }
          }
        },
        { default: () => '文章管理' }
      ),
    key: 'menuArticle',
    icon: renderIcon(FileTrayIcon)
  },
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: 'menuPhoto',
            params: {
              lang: 'zh-CN'
            }
          }
        },
        { default: () => '相册管理' }
      ),
    key: 'menuPhoto',
    icon: renderIcon(ImagesIcon)
  },
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: 'menuMusic',
            params: {
              lang: 'zh-CN'
            }
          }
        },
        { default: () => '歌单管理' }
      ),
    key: 'menuMusic',
    icon: renderIcon(HeadsetIcon)
  },
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: 'menuErchuang',
            params: {
              lang: 'zh-CN'
            }
          }
        },
        { default: () => '二创管理' }
      ),
    key: 'menuErchuang',
    icon: renderIcon(ShareIcon)
  },
]
</script>

<style lang="scss" scoped>
.menu {
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 100%;
  padding-top: 53px;

  .menu-left {
    width: 10%;
    height: 100%;
  }

  .menu-right {
    width: 90%;
    height: 100%;
    border-left: 1px solid #c8d9eb;
  }
}
</style>
