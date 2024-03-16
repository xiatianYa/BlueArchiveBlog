import {defineStore} from 'pinia'

export const useGloBalStore = defineStore('global', {
    actions: {
        changLoading(value: boolean) {
            this.loading = value;
        },
        changSwitch(value: boolean) {
            this.switch = value;
        }
    },
    // 真正存储数据的地方
    state() {
        return {
            //控制加载动画
            loading: true,
            //控制灯开关
            switch: true,
        }
    }
})
