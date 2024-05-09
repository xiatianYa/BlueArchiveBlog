import request from '@/utils/request'

// 查询网站背景资源信息列表
export function listBg(query) {
  return request({
    url: '/blog/bg/list',
    method: 'get',
    params: query
  })
}

// 查询网站背景资源信息详细
export function getBg(id) {
  return request({
    url: '/blog/bg/' + id,
    method: 'get'
  })
}

// 新增网站背景资源信息
export function addBg(data) {
  return request({
    url: '/blog/bg',
    method: 'post',
    data: data
  })
}

// 修改网站背景资源信息
export function updateBg(data) {
  return request({
    url: '/blog/bg',
    method: 'put',
    data: data
  })
}

// 删除网站背景资源信息
export function delBg(id) {
  return request({
    url: '/blog/bg/' + id,
    method: 'delete'
  })
}
