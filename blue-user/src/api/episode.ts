import request from '@/utils/request'

// 查询番剧集存储列表
export function listEpisode(query:any) {
  return request({
    url: '/blog/episode/list',
    method: 'get',
    params: query
  })
}

// 查询番剧集存储详细
export function getEpisode(id:any) {
  return request({
    url: '/blog/episode/' + id,
    method: 'get'
  })
}

// 新增番剧集存储
export function addEpisode(data:any) {
  return request({
    url: '/blog/episode',
    method: 'post',
    data: data
  })
}

// 修改番剧集存储
export function updateEpisode(data:any) {
  return request({
    url: '/blog/episode',
    method: 'put',
    data: data
  })
}

// 删除番剧集存储
export function delEpisode(id:any) {
  return request({
    url: '/blog/episode/' + id,
    method: 'delete'
  })
}
