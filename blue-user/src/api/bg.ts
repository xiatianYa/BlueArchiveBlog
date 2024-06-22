import request from '@/utils/request'

// 查询网站背景资源信息列表
export function listBg(query?:any) {
  return request({
    url: '/blog/bg/list',
    method: 'get',
    params: query
  })
}

// 查询网站背景资源信息详细
export function getBg(id:any) {
  return request({
    url: '/blog/bg/' + id,
    method: 'get'
  })
}

// 新增网站背景资源信息
export function addBg(data:any) {
  return request({
    url: '/blog/bg',
    method: 'post',
    data: data
  })
}

// 修改网站背景资源信息
export function updateBg(data:any) {
  return request({
    url: '/blog/bg',
    method: 'put',
    data: data
  })
}

// 删除网站背景资源信息
export function delBg(id:any) {
  return request({
    url: '/blog/bg/' + id,
    method: 'delete'
  })
}
