import request from '@/utils/request'

// 查询音乐分类列表
export function listSort(query?:any) {
  return request({
    url: '/blog/musicSort/list',
    method: 'get',
    params: query
  })
}

// 查询音乐分类详细
export function getSort(id:any) {
  return request({
    url: '/blog/musicSort/' + id,
    method: 'get'
  })
}

// 新增音乐分类
export function addSort(data:any) {
  return request({
    url: '/blog/musicSort',
    method: 'post',
    data: data
  })
}

// 修改音乐分类
export function updateSort(data:any) {
  return request({
    url: '/blog/musicSort',
    method: 'put',
    data: data
  })
}

// 删除音乐分类
export function delSort(id:any) {
  return request({
    url: '/blog/musicSort/' + id,
    method: 'delete'
  })
}
