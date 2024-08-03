import request from "@/utils/request";

// 搜索文章列表
export function searchArticleList(query?: any) {
  return request({
    url: "/blog/article/search",
    method: "get",
    params: query,
  });
}
// 查询文章列表
export function listArticle(query?: any) {
  return request({
    url: "/blog/article/list",
    method: "get",
    params: query,
  });
}
// 查询用户自己文章列表
export function listArticleByUser() {
  return request({
    url: "/blog/article/listByUser",
    method: "get",
  });
}
// 根据标签ID查询文章
export function listByTagId(tagId: any, query?: any) {
  return request({
    url: "/blog/article/listByTagId/" + tagId,
    method: "get",
    params: query,
  });
}
// 根据分类ID查询文章
export function listBySortId(sortId: any) {
  return request({
    url: "/blog/article/listBySortId/" + sortId,
    method: "get",
  });
}
// 查询文章详细
export function getArticle(id: any) {
  return request({
    url: "/blog/article/" + id,
    method: "get",
  });
}

// 新增文章
export function addArticle(data: any) {
  return request({
    url: "/blog/article",
    method: "post",
    data: data,
  });
}

// 修改文章
export function updateArticle(data: any) {
  return request({
    url: "/blog/article",
    method: "put",
    data: data,
  });
}

// 删除文章
export function delArticle(id: any) {
  return request({
    url: "/blog/article/" + id,
    method: "delete",
  });
}

// 文章点赞
export function addLike(id: any) {
  return request({
    url: "/blog/article/addLike/" + id,
    method: "post",
  });
}

// 获取首页文章
export function listByHome() {
  return request({
    url: "/blog/article/listByHome",
    method: "get",
  });
}

// 删除文章
export function dexamineArticle(ids: any) {
  return request({
    url: "/blog/article/examine/" + ids,
    method: "post",
  });
}
