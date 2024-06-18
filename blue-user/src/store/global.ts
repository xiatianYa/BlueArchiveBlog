import { defineStore } from 'pinia'
import { listBg } from '@/api/bg'
import chatSocket from "@/utils/chatSocket";
//用户对象
interface userVoType{
    userId:number,
    userAvatar:string,
    userNickName:string
}
export interface Global {
    //控制加载动画
    loading: boolean,
    //控制灯开关
    switch: boolean,
    //背景列表
    bgList: Array<any>;
    //接收到的消息列表
    chatHistory:Array<any>;
    //在线用户列表
    onlineUserList:Array<userVoType>;
}
export const useGloBalStore = defineStore('global', {
    // 真正存储数据的地方
    state: (): Global => {
        return {
            loading: true,
            switch: true,
            bgList: [],
            chatHistory:[],
            onlineUserList:[]
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
            //没有背景 则重新获取
            if (!this.bgList.length) {
                listBg().then((res: any) => {
                    Array.prototype.push.apply(this.bgList, res.rows);
                })
            }
        },
        //获取背景根据类型
        getBgByType(type: string) {
            return this.bgList.find(item => item.bgPosition === type)?.bgUrl;
        },
        //初始化socket
        initSocket() {
            chatSocket.init()
        },
        //关闭socket
        closeSocket(){
            chatSocket.close(false)
        }
    },
})