import { defineStore } from 'pinia'
import { listBg } from '@/api/bg'

export const useBgStore = defineStore('bg', {
    actions: {
        SET_BGLIST() {
            listBg().then((res: any) => {
                for (let index = 0; index < res.rows.length; index++) {
                    this.bgList.push(res.rows[index])
                }
            })
        },
        GET_BGLIST_BYTYPE(type:string) {
            for (let index = 0; index < this.bgList.length; index++) {
                if (this.bgList[index].bgPosition === type) {
                    return this.bgList[index].bgUrl;
                }
            }
        }
    },
    // 真正存储数据的地方
    state() {
        return {
            bgList: [{
                "createTime": "2024-03-17 15:48:36",
                "updateTime": "2024-03-17 15:48:36",
                "id": 1,
                "bgType": "1",
                "bgUrl": "http://127.0.0.1:9300/statics/2024/03/17/miyu_swimsuit_20240317154608A002.mp4",
                "bgPosition": "2"
            }]
        }
    }
})