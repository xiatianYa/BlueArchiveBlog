import {defineStore} from 'pinia'

export const useSocketStore = defineStore('socket', {
    actions: {
        INIT(UserId: Number) {
            //拼接用户id
            this.socketUrl += UserId;
            this.socketUrl = this.socketUrl.replace("https", "ws").replace("http", "ws");
            this.socket = new WebSocket(this.socketUrl);
        },
        CLEAR_SOCKET() {
            this.socket = ""
        }
    },
    // 真正存储数据的地方
    state() {
        return {
            socket: "",
            socketUrl: "http://127.0.0.1:8080/websocket/server/"
        }
    }
})