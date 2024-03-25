import request from '@/utils/request'

// 查询友情链接列表
export function uploadImages(file) {
    return request({
      url: '/file/upload',
      method: 'post',
      data: file
    })
  }