import { createRouter, createWebHistory } from "vue-router";
import Layout from "@/view/layout/index.vue";
import Loading from "@/view/loading/LoadingView.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  scrollBehavior(to, from, savedPosition) {
    // 总是滚动到顶部
    return { left: 0, top: 0 };
  },
  routes: [
    {
      path: "/",
      component: Loading,
    },
    {
      path: "/layout",
      component: Layout,
      redirect: "/main",
      children: [
        {
          path: "/main",
          name: "main",
          component: () => import("@/view/home/index.vue"),
        },
        {
          path: "/found",
          name: "found",
          component: () => import("@/view/found/index.vue"),
        },
        {
          path: "/sort",
          name: "sort",
          component: () => import("@/view/sort/index.vue"),
        },
        {
          path: "/photo",
          name: "photo",
          component: () => import("@/view/photo/index.vue"),
        },
        {
          path: "/chat",
          name: "chat",
          component: () => import("@/view/chat/index.vue"),
        },
        {
          path: "/friend",
          name: "friend",
          component: () => import("@/view/friend/index.vue"),
        },
        {
          path: "/leave",
          name: "leave",
          component: () => import("@/view/leave/index.vue"),
        },
        {
          path: "/music",
          name: "music",
          component: () => import("@/view/music/index.vue"),
        },
        {
          path: "/pixiv",
          name: "pixiv",
          component: () => import("@/view/pixiv/index.vue"),
        },
        {
          path: "/user",
          name: "user",
          component: () => import("@/view/user/index.vue"),
        },
        {
          path: "/menu",
          name: "menu",
          redirect: "/menu/user",
          component: () => import("@/view/menu/index.vue"),
          children: [
            {
              path: "user",
              name: "menuUser",
              component: () => import("@/view/menu/component/user/index.vue"),
            },
            {
              path: "article",
              name: "menuArticle",
              component: () =>
                import("@/view/menu/component/article/index.vue"),
            },
            {
              path: "music",
              name: "menuMusic",
              component: () => import("@/view/menu/component/music/index.vue"),
            },
            {
              path: "photo",
              name: "menuPhoto",
              component: () => import("@/view/menu/component/photo/index.vue"),
            },
            {
              path: "erchuang",
              name: "menuErchuang",
              component: () =>
                import("@/view/menu/component/erchuang/index.vue"),
            },
          ],
        },
      ],
    },
  ],
});

export default router;
