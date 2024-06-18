import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import prismjs from 'vite-plugin-prismjs';
// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
        prismjs({
            languages: 'all',
        }),
    ],
    // 设置代理
    server: {
        host: '0.0.0.0',
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8080',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api/, '') // 路径重写，将 '/api' 前缀去掉
            }
        }
    },
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },
    define: {
        __VUE_PROD_DEVTOOLS__: 'true',
    },
})
