import request from '@/utils/request'

// 查询评论列表
export function listComment(query:any) {
  return request({
    url: '/blog/comment/list',
    method: 'get',
    params: query
  })
}

// 查询评论详细
export function getComment(id:any) {
  return request({
    url: '/blog/comment/' + id,
    method: 'get'
  })
}

// 新增评论
export function addComment(data:any) {
  return request({
    url: '/blog/comment',
    method: 'post',
    data: data
  })
}

// 修改评论
export function updateComment(data:any) {
  return request({
    url: '/blog/comment',
    method: 'put',
    data: data
  })
}

// 删除评论
export function delComment(id:any) {
  return request({
    url: '/blog/comment/' + id,
    method: 'delete'
  })
}
