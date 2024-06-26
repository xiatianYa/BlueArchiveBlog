import request from '@/utils/request'

// 查询弹幕列表
export function listMessage(query) {
  return request({
    url: '/blog/message/list',
    method: 'get',
    params: query
  })
}

// 查询弹幕详细
export function getMessage(id) {
  return request({
    url: '/blog/message/' + id,
    method: 'get'
  })
}

// 新增弹幕
export function addMessage(data) {
  return request({
    url: '/blog/message',
    method: 'post',
    data: data
  })
}

// 修改弹幕
export function updateMessage(data) {
  return request({
    url: '/blog/message',
    method: 'put',
    data: data
  })
}

// 删除弹幕
export function delMessage(id) {
  return request({
    url: '/blog/message/' + id,
    method: 'delete'
  })
}
