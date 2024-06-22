import request from '@/utils/request'

// 查询分类列表
export function listSort(query?:any) {
  return request({
    url: '/blog/sort/list',
    method: 'get',
    params: query
  })
}

// 查询分类详细
export function getSort(id:any) {
  return request({
    url: '/blog/sort/' + id,
    method: 'get'
  })
}

// 新增分类
export function addSort(data:any) {
  return request({
    url: '/blog/sort',
    method: 'post',
    data: data
  })
}

// 修改分类
export function updateSort(data:any) {
  return request({
    url: '/blog/sort',
    method: 'put',
    data: data
  })
}

// 删除分类
export function delSort(id:any) {
  return request({
    url: '/blog/sort/' + id,
    method: 'delete'
  })
}
