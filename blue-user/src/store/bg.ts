import {defineStore} from 'pinia'
import {listBg} from '@/api/bg'

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
            bgList:[]
        }
    }
})