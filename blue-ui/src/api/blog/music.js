import request from '@/utils/request'

// 查询音乐列表
export function listMusic(query) {
  return request({
    url: '/system/music/list',
    method: 'get',
    params: query
  })
}

// 查询音乐详细
export function getMusic(id) {
  return request({
    url: '/system/music/' + id,
    method: 'get'
  })
}

// 新增音乐
export function addMusic(data) {
  return request({
    url: '/system/music',
    method: 'post',
    data: data
  })
}

// 修改音乐
export function updateMusic(data) {
  return request({
    url: '/system/music',
    method: 'put',
    data: data
  })
}

// 删除音乐
export function delMusic(id) {
  return request({
    url: '/system/music/' + id,
    method: 'delete'
  })
}
