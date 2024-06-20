import request from '@/utils/request'

// 查询二创信息列表
export function listErchuangByUser(query:any) {
  return request({
    url: '/blog/erchuang/listByUser',
    method: 'get',
    params:query
  })
}
// 查询二创信息列表
export function listErchuang(query:any) {
  return request({
    url: '/blog/erchuang/list',
    method: 'get',
    params: query
  })
}

// 查询二创信息详细
export function getErchuang(id:any) {
  return request({
    url: '/blog/erchuang/' + id,
    method: 'get'
  })
}

// 新增二创信息
export function addErchuang(data:any) {
  return request({
    url: '/blog/erchuang',
    method: 'post',
    data: data
  })
}

// 修改二创信息
export function updateErchuang(data:any) {
  return request({
    url: '/blog/erchuang',
    method: 'put',
    data: data
  })
}

// 删除二创信息
export function delErchuang(id:any) {
  return request({
    url: '/blog/erchuang/' + id,
    method: 'delete'
  })
}
