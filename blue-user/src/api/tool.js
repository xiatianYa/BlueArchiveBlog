import request from '@/utils/request'

// 查询编程工具列表
export function listTool(query) {
  return request({
    url: '/system/tool/list',
    method: 'get',
    params: query
  })
}
// 查询编程工具列表根据分类
export function listToolBySort() {
  return request({
    url: '/system/tool/listBySort',
    method: 'get',
  })
}
// 查询编程工具详细
export function getTool(id) {
  return request({
    url: '/system/tool/' + id,
    method: 'get'
  })
}

// 新增编程工具
export function addTool(data) {
  return request({
    url: '/system/tool',
    method: 'post',
    data: data
  })
}

// 修改编程工具
export function updateTool(data) {
  return request({
    url: '/system/tool',
    method: 'put',
    data: data
  })
}

// 删除编程工具
export function delTool(id) {
  return request({
    url: '/system/tool/' + id,
    method: 'delete'
  })
}
