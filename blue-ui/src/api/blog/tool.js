import request from '@/utils/request'

// 查询编程工具列表
export function listTool(query) {
  return request({
    url: '/blog/tool/list',
    method: 'get',
    params: query
  })
}

// 查询编程工具详细
export function getTool(id) {
  return request({
    url: '/blog/tool/' + id,
    method: 'get'
  })
}

// 新增编程工具
export function addTool(data) {
  return request({
    url: '/blog/tool',
    method: 'post',
    data: data
  })
}

// 修改编程工具
export function updateTool(data) {
  return request({
    url: '/blog/tool',
    method: 'put',
    data: data
  })
}

// 删除编程工具
export function delTool(id) {
  return request({
    url: '/blog/tool/' + id,
    method: 'delete'
  })
}
