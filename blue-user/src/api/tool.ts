import request from '@/utils/request'

// 查询编程工具列表
export function listTool(query:any) {
  return request({
    url: '/blog/tool/list',
    method: 'get',
    params: query
  })
}
// 查询编程工具列表根据分类
export function listToolBySort() {
  return request({
    url: '/blog/tool/listBySort',
    method: 'get',
  })
}
// 查询编程工具详细
export function getTool(id:any) {
  return request({
    url: '/blog/tool/' + id,
    method: 'get'
  })
}

// 新增编程工具
export function addTool(data:any) {
  return request({
    url: '/blog/tool',
    method: 'post',
    data: data
  })
}

// 修改编程工具
export function updateTool(data:any) {
  return request({
    url: '/blog/tool',
    method: 'put',
    data: data
  })
}

// 删除编程工具
export function delTool(id:any) {
  return request({
    url: '/blog/tool/' + id,
    method: 'delete'
  })
}
