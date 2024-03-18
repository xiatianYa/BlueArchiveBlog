import request from '@/utils/request'

// 查询分类列表
export function listSort(query) {
  return request({
    url: '/system/sort/list',
    method: 'get',
    params: query
  })
}

// 查询分类详细
export function getSort(id) {
  return request({
    url: '/system/sort/' + id,
    method: 'get'
  })
}

// 新增分类
export function addSort(data) {
  return request({
    url: '/system/sort',
    method: 'post',
    data: data
  })
}

// 修改分类
export function updateSort(data) {
  return request({
    url: '/system/sort',
    method: 'put',
    data: data
  })
}

// 删除分类
export function delSort(id) {
  return request({
    url: '/system/sort/' + id,
    method: 'delete'
  })
}
