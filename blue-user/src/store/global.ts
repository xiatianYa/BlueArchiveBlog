import { defineStore } from 'pinia'
import { listBg } from '@/api/bg'
export interface Global {
    //控制加载动画
    loading: boolean,
    //控制灯开关
    switch: boolean,
    //背景列表
    bgList: Array<any>;
    //聊天室socket地址
    socketUrl: string;
    //socket连接对象;
    socket: any;
}
export const useGloBalStore = defineStore('global', {
    // 真正存储数据的地方
    state: (): Global => {
        return {
            //控制加载动画
            loading: true,
            //控制灯开关
            switch: true,
            bgList: [],
            socketUrl: "ws://bluearchive.top:8080/websocket/server/",
            socket: null,
        }
    },
    actions: {
        //切换加载
        changLoading(value: boolean) {
            this.loading = value;
        },
        //切换背景模式
        changSwitch(value: boolean) {
            this.switch = value;
        },
        //获取背景
        setBgList() {
            if (!this.bgList.length) {
                listBg().then((res: any) => {
                    for (let index = 0; index < res.rows.length; index++) {
                        this.bgList.push(res.rows[index])
                    }
                })
            }
        },
        //获取背景根据类型
        getBgByType(type: string) {
            for (let index = 0; index < this.bgList.length; index++) {
                if (this.bgList[index].bgPosition === type) {
                    return this.bgList[index].bgUrl;
                }
            }
        },
        //初始化socket
        initSocket() {
            this.socket = new WebSocket(this.socketUrl);
        },
        //清除socket
        clearSocket() {
            this.socket = null;
        }
    },
})