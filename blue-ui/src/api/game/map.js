import request from '@/utils/request'

// 查询游戏地图列表
export function listMap(query) {
  return request({
    url: '/blog/map/list',
    method: 'get',
    params: query
  })
}

// 查询游戏地图详细
export function getMap(id) {
  return request({
    url: '/blog/map/' + id,
    method: 'get'
  })
}

// 新增游戏地图
export function addMap(data) {
  return request({
    url: '/blog/map',
    method: 'post',
    data: data
  })
}

// 修改游戏地图
export function updateMap(data) {
  return request({
    url: '/blog/map',
    method: 'put',
    data: data
  })
}

// 删除游戏地图
export function delMap(id) {
  return request({
    url: '/blog/map/' + id,
    method: 'delete'
  })
}
