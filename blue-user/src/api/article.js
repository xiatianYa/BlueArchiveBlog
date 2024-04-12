import request from '@/utils/request'

// 查询文章列表
export function listArticle(query) {
  return request({
    url: '/system/article/list',
    method: 'get',
    params: query
  })
}
// 查询用户自己文章列表
export function listArticleByUser() {
  return request({
    url: '/system/article/listByUser',
    method: 'get',
  })
}
// 根据标签ID查询文章
export function listByTagId(tagId) {
  return request({
    url: '/system/article/listByTagId/'+tagId,
    method: 'get',
  })
}
// 根据分类ID查询文章
export function listBySortId(sortId) {
  return request({
    url: '/system/article/listBySortId/'+sortId,
    method: 'get',
  })
}
// 查询文章详细
export function getArticle(id) {
  return request({
    url: '/system/article/' + id,
    method: 'get'
  })
}

// 新增文章
export function addArticle(data) {
  return request({
    url: '/system/article',
    method: 'post',
    data: data
  })
}

// 修改文章
export function updateArticle(data) {
  return request({
    url: '/system/article',
    method: 'put',
    data: data
  })
}

// 删除文章
export function delArticle(id) {
  return request({
    url: '/system/article/' + id,
    method: 'delete'
  })
}
