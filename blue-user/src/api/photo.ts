import request from '@/utils/request'

// 查询相册列表
export function listPhotoByUser(query:any) {
  return request({
    url: '/blog/photo/listByUser',
    method: 'get',
    params:query
  })
}

// 查询相册列表
export function listPhoto(query:any) {
  return request({
    url: '/blog/photo/list',
    method: 'get',
    params: query
  })
}

// 查询相册详细
export function getPhoto(id:any) {
  return request({
    url: '/blog/photo/' + id,
    method: 'get'
  })
}

// 新增相册
export function addPhoto(data:any) {
  return request({
    url: '/blog/photo',
    method: 'post',
    data: data
  })
}

// 修改相册
export function updatePhoto(data:any) {
  return request({
    url: '/blog/photo',
    method: 'put',
    data: data
  })
}

// 删除相册
export function delPhoto(id:any) {
  return request({
    url: '/blog/photo/' + id,
    method: 'delete'
  })
}
