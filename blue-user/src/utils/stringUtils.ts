//获取浏览器路径Hash值
const getHashParams = () => {
  let hashParams: any = [];
  let hash = window.location.hash.substring(1); // 去掉 # 号
  if (!hash) {
    return null;
  }
  const params = hash.split("&");

  params.forEach((param) => {
    let [key, value] = param.split("=");
    hashParams[key] = decodeURIComponent(value);
  });

  return hashParams;
};

export default { getHashParams };
