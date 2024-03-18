import request from '@/utils/request'

// 查询相册分类信息列表
export function listPhoto(query) {
  return request({
    url: '/system/photosort/list',
    method: 'get',
    params: query
  })
}

// 查询相册分类信息详细
export function getPhoto(id) {
  return request({
    url: '/system/photosort/' + id,
    method: 'get'
  })
}

// 新增相册分类信息
export function addPhoto(data) {
  return request({
    url: '/system/photosort',
    method: 'post',
    data: data
  })
}

// 修改相册分类信息
export function updatePhoto(data) {
  return request({
    url: '/system/photosort',
    method: 'put',
    data: data
  })
}

// 删除相册分类信息
export function delPhoto(id) {
  return request({
    url: '/system/photosort/' + id,
    method: 'delete'
  })
}
