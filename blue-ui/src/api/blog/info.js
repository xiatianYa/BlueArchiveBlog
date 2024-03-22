import request from '@/utils/request'

// 查询友链申请信息列表
export function listInfo(query) {
  return request({
    url: '/system/friendInfo/list',
    method: 'get',
    params: query
  })
}

// 查询友链申请信息详细
export function getInfo(id) {
  return request({
    url: '/system/friendInfo/' + id,
    method: 'get'
  })
}

// 新增友链申请信息
export function addInfo(data) {
  return request({
    url: '/system/friendInfo',
    method: 'post',
    data: data
  })
}

// 修改友链申请信息
export function updateInfo(data) {
  return request({
    url: '/system/friendInfo',
    method: 'put',
    data: data
  })
}

// 删除友链申请信息
export function delInfo(id) {
  return request({
    url: '/system/friendInfo/' + id,
    method: 'delete'
  })
}
