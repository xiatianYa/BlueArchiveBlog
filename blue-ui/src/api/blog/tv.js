import request from '@/utils/request'

// 查询番剧信息列表
export function listTv(query) {
  return request({
    url: '/system/tv/list',
    method: 'get',
    params: query
  })
}

// 查询番剧信息详细
export function getTv(id) {
  return request({
    url: '/system/tv/' + id,
    method: 'get'
  })
}

// 新增番剧信息
export function addTv(data) {
  return request({
    url: '/system/tv',
    method: 'post',
    data: data
  })
}

// 修改番剧信息
export function updateTv(data) {
  return request({
    url: '/system/tv',
    method: 'put',
    data: data
  })
}

// 删除番剧信息
export function delTv(id) {
  return request({
    url: '/system/tv/' + id,
    method: 'delete'
  })
}
