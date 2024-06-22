import request from '@/utils/request'

// 查询标签列表
export function listTag(query?:any) {
  return request({
    url: '/blog/tagSort/list',
    method: 'get',
    params: query
  })
}

// 查询标签详细
export function getTag(id:any) {
  return request({
    url: '/blog/tagSort/' + id,
    method: 'get'
  })
}

// 新增标签
export function addTag(data:any) {
  return request({
    url: '/blog/tagSort',
    method: 'post',
    data: data
  })
}

// 修改标签
export function updateTag(data:any) {
  return request({
    url: '/system/tagSort',
    method: 'put',
    data: data
  })
}

// 删除标签
export function delTag(id:any) {
  return request({
    url: '/system/tagSort/' + id,
    method: 'delete'
  })
}
