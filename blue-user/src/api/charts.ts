import request from '@/utils/request'

// 查询用户热力图数据
export function selectUserHeatMapData() {
    return request({
      url: '/blog/echarts/selectUserHeatMapData',
      method: 'get',
    })
  }