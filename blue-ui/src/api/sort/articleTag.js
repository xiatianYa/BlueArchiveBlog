import request from '@/utils/request'

// 查询文章标签关联列表
export function listTag(query) {
  return request({
    url: '/blog/articleTag/list',
    method: 'get',
    params: query
  })
}

// 查询文章标签关联详细
export function getTag(id) {
  return request({
    url: '/blog/articleTag/' + id,
    method: 'get'
  })
}

// 新增文章标签关联
export function addTag(data) {
  return request({
    url: '/blog/articleTag',
    method: 'post',
    data: data
  })
}

// 修改文章标签关联
export function updateTag(data) {
  return request({
    url: '/blog/articleTag',
    method: 'put',
    data: data
  })
}

// 删除文章标签关联
export function delTag(id) {
  return request({
    url: '/blog/articleTag/' + id,
    method: 'delete'
  })
}
