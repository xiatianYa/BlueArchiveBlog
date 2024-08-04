import request from '@/utils/request'

// 查询游戏模式列表
export function listMode(query) {
  return request({
    url: '/blog/mode/list',
    method: 'get',
    params: query
  })
}

// 查询游戏模式详细
export function getMode(id) {
  return request({
    url: '/blog/mode/' + id,
    method: 'get'
  })
}

// 新增游戏模式
export function addMode(data) {
  return request({
    url: '/blog/mode',
    method: 'post',
    data: data
  })
}

// 修改游戏模式
export function updateMode(data) {
  return request({
    url: '/blog/mode',
    method: 'put',
    data: data
  })
}

// 删除游戏模式
export function delMode(id) {
  return request({
    url: '/blog/mode/' + id,
    method: 'delete'
  })
}
