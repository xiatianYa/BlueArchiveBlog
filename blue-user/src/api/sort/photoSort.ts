import request from '@/utils/request'

// 查询相册分类信息列表
export function listSort(query?:any) {
  return request({
    url: '/blog/photoSort/list',
    method: 'get',
    params: query
  })
}

// 查询相册分类信息详细
export function getSort(id:any) {
  return request({
    url: '/blog/photoSort/' + id,
    method: 'get'
  })
}

// 新增相册分类信息
export function addSort(data:any) {
  return request({
    url: '/blog/photoSort',
    method: 'post',
    data: data
  })
}

// 修改相册分类信息
export function updateSort(data:any) {
  return request({
    url: '/blog/photoSort',
    method: 'put',
    data: data
  })
}

// 删除相册分类信息
export function delSort(id:any) {
  return request({
    url: '/blog/photoSort/' + id,
    method: 'delete'
  })
}
