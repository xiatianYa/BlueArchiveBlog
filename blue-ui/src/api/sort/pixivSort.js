import request from '@/utils/request'

// 查询番剧类型列表
export function listType(query) {
  return request({
    url: '/system/pixivSort/list',
    method: 'get',
    params: query
  })
}

// 查询番剧类型详细
export function getType(id) {
  return request({
    url: '/system/pixivSort/' + id,
    method: 'get'
  })
}

// 新增番剧类型
export function addType(data) {
  return request({
    url: '/system/pixivSort',
    method: 'post',
    data: data
  })
}

// 修改番剧类型
export function updateType(data) {
  return request({
    url: '/system/pixivSort',
    method: 'put',
    data: data
  })
}

// 删除番剧类型
export function delType(id) {
  return request({
    url: '/system/pixivSort/' + id,
    method: 'delete'
  })
}
