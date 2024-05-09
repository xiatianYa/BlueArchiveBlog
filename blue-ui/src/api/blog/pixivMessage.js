import request from '@/utils/request'

// 查询番剧弹幕列表
export function listMessage(query) {
  return request({
    url: '/blog/pixivMessage/list',
    method: 'get',
    params: query
  })
}

// 查询番剧弹幕详细
export function getMessage(id) {
  return request({
    url: '/blog/pixivMessage/' + id,
    method: 'get'
  })
}

// 新增番剧弹幕
export function addMessage(data) {
  return request({
    url: '/blog/pixivMessage',
    method: 'post',
    data: data
  })
}

// 修改番剧弹幕
export function updateMessage(data) {
  return request({
    url: '/blog/pixivMessage',
    method: 'put',
    data: data
  })
}

// 删除番剧弹幕
export function delMessage(id) {
  return request({
    url: '/blog/pixivMessage/' + id,
    method: 'delete'
  })
}
