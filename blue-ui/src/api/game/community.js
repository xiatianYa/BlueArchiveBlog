import request from '@/utils/request'

// 查询游戏社区列表
export function listCommunity(query) {
  return request({
    url: '/blog/community/list',
    method: 'get',
    params: query
  })
}

// 查询游戏社区详细
export function getCommunity(id) {
  return request({
    url: '/blog/community/' + id,
    method: 'get'
  })
}

// 新增游戏社区
export function addCommunity(data) {
  return request({
    url: '/blog/community',
    method: 'post',
    data: data
  })
}

// 修改游戏社区
export function updateCommunity(data) {
  return request({
    url: '/blog/community',
    method: 'put',
    data: data
  })
}

// 删除游戏社区
export function delCommunity(id) {
  return request({
    url: '/blog/community/' + id,
    method: 'delete'
  })
}
