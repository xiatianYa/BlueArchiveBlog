import request from '@/utils/request'

// 上传图片
export function uploadImages(file: any) {
  return request({
    headers: { 'Content-Type': 'multipart/form-data' },
    url: '/file/upload',
    method: 'post',
    data: file
  })
}
// 上传文件
export function uploadFiles(file: any) {
  return request({
    headers: { 'Content-Type': 'multipart/form-data' },
    url: '/file/upload',
    method: 'post',
    data: file
  })
}