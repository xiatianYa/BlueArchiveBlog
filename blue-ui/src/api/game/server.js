import request from '@/utils/request'

// 查询游戏服务器列表
export function listServer(query) {
  return request({
    url: '/blog/server/list',
    method: 'get',
    params: query
  })
}

// 查询游戏服务器详细
export function getServer(id) {
  return request({
    url: '/blog/server/' + id,
    method: 'get'
  })
}

// 新增游戏服务器
export function addServer(data) {
  return request({
    url: '/blog/server',
    method: 'post',
    data: data
  })
}

// 修改游戏服务器
export function updateServer(data) {
  return request({
    url: '/blog/server',
    method: 'put',
    data: data
  })
}

// 删除游戏服务器
export function delServer(id) {
  return request({
    url: '/blog/server/' + id,
    method: 'delete'
  })
}
