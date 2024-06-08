import request from '@/utils/request'

// 查询用户头像列存储列表
export function listAvater(query) {
  return request({
    url: '/blog/avater/list',
    method: 'get',
    params: query
  })
}

// 查询用户头像列存储详细
export function getAvater(id) {
  return request({
    url: '/blog/avater/' + id,
    method: 'get'
  })
}

// 新增用户头像列存储
export function addAvater(data) {
  return request({
    url: '/blog/avater',
    method: 'post',
    data: data
  })
}

// 修改用户头像列存储
export function updateAvater(data) {
  return request({
    url: '/blog/avater',
    method: 'put',
    data: data
  })
}

// 删除用户头像列存储
export function delAvater(id) {
  return request({
    url: '/blog/avater/' + id,
    method: 'delete'
  })
}
