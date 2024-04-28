import request from '@/utils/request'

// 查询友情链接列表
export function init() {
    return request({
      url: '/system/game/init',
      method: 'post',
    })
  }