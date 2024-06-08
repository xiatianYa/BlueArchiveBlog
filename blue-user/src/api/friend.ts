import request from '@/utils/request'

// 查询友情链接列表
export function listFriend(query) {
  return request({
    url: '/blog/friend/list',
    method: 'get',
    params: query
  })
}

// 查询友情链接详细
export function getFriend(id) {
  return request({
    url: '/blog/friend/' + id,
    method: 'get'
  })
}

// 新增友情链接
export function addFriend(data) {
  return request({
    url: '/blog/friend',
    method: 'post',
    data: data
  })
}

// 修改友情链接
export function updateFriend(data) {
  return request({
    url: '/blog/friend',
    method: 'put',
    data: data
  })
}

// 删除友情链接
export function delFriend(id) {
  return request({
    url: '/blog/friend/' + id,
    method: 'delete'
  })
}
