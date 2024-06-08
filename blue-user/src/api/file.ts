import request from '@/utils/request'

// 查询友情链接列表
export function uploadImages(file) {
  return request({
    headers: { 'Content-Type': 'multipart/form-data' },
    url: '/file/upload',
    method: 'post',
    data: file
  })
}