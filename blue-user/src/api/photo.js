import request from '@/utils/request'

// 查询相册列表
export function listPhoto(query) {
  return request({
    url: '/system/photo/list',
    method: 'get',
    params: query
  })
}

// 查询相册详细
export function getPhoto(id) {
  return request({
    url: '/system/photo/' + id,
    method: 'get'
  })
}

// 新增相册
export function addPhoto(data) {
  return request({
    url: '/system/photo',
    method: 'post',
    data: data
  })
}

// 修改相册
export function updatePhoto(data) {
  return request({
    url: '/system/photo',
    method: 'put',
    data: data
  })
}

// 删除相册
export function delPhoto(id) {
  return request({
    url: '/system/photo/' + id,
    method: 'delete'
  })
}
