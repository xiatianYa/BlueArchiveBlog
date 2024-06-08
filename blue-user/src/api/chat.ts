import request from '@/utils/request'

// 查询友情链接列表
export function getUserList() {
    return request({
      url: '/websocket/ws/getUserList',
      method: 'get',
    })
  }