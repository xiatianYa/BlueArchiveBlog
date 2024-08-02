import request from "@/utils/request";

// 查询友链申请信息列表
export function listInfo(query?: any) {
  return request({
    url: "/blog/friendInfo/list",
    method: "get",
    params: query,
  });
}

// 查询友链申请信息详细
export function getInfo(id: any) {
  return request({
    url: "/blog/friendInfo/" + id,
    method: "get",
  });
}

// 新增友链申请信息
export function addInfo(data: any) {
  return request({
    url: "/blog/friendInfo",
    method: "post",
    data: data,
  });
}

// 修改友链申请信息
export function updateInfo(data: any) {
  return request({
    url: "/blog/friendInfo",
    method: "put",
    data: data,
  });
}

// 删除友链申请信息
export function delInfo(id: any) {
  return request({
    url: "/blog/friendInfo/" + id,
    method: "delete",
  });
}
