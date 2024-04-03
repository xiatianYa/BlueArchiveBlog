import request from '@/utils/request'

// 查询编程工具分类列表
export function listSort(query) {
  return request({
    url: '/system/toolSort/list',
    method: 'get',
    params: query
  })
}

// 查询编程工具分类详细
export function getSort(id) {
  return request({
    url: '/system/toolSort/' + id,
    method: 'get'
  })
}

// 新增编程工具分类
export function addSort(data) {
  return request({
    url: '/system/toolSort',
    method: 'post',
    data: data
  })
}

// 修改编程工具分类
export function updateSort(data) {
  return request({
    url: '/system/toolSort',
    method: 'put',
    data: data
  })
}

// 删除编程工具分类
export function delSort(id) {
  return request({
    url: '/system/toolSort/' + id,
    method: 'delete'
  })
}
