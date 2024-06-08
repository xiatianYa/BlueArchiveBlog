import request from '@/utils/request'

// 查询网站信息列表
export function listWebsite(query) {
  return request({
    url: '/blog/websiteInfo/list',
    method: 'get',
    params: query
  })
}

// 查询网站信息详细
export function getWebsite(id) {
  return request({
    url: '/blog/websiteInfo/' + id,
    method: 'get'
  })
}

// 新增网站信息
export function addWebsite(data) {
  return request({
    url: '/blog/websiteInfo',
    method: 'post',
    data: data
  })
}

// 修改网站信息
export function updateWebsite(data) {
  return request({
    url: '/blog/websiteInfo',
    method: 'put',
    data: data
  })
}

// 删除网站信息
export function delWebsite(id) {
  return request({
    url: '/blog/websiteInfo/' + id,
    method: 'delete'
  })
}
