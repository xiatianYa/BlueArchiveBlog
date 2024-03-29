import request from '@/utils/request'

// 查询音乐分类列表
export function listSort(query) {
  return request({
    url: '/system/musicsort/list',
    method: 'get',
    params: query
  })
}

// 查询音乐分类详细
export function getSort(id) {
  return request({
    url: '/system/musicsort/' + id,
    method: 'get'
  })
}

// 新增音乐分类
export function addSort(data) {
  return request({
    url: '/system/musicsort',
    method: 'post',
    data: data
  })
}

// 修改音乐分类
export function updateSort(data) {
  return request({
    url: '/system/musicsort',
    method: 'put',
    data: data
  })
}

// 删除音乐分类
export function delSort(id) {
  return request({
    url: '/system/musicsort/' + id,
    method: 'delete'
  })
}
