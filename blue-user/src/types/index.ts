export type CustomType<T = any> = T;
//用户
export type UserType = {
  //用户Token
  token: string;
  //用户id
  id: number | null;
  //用户昵称
  nickName: string;
  //用户头像
  avatar: string;
  //用户邮箱
  email: string;
  //用户手机号
  phone: string;
  //用户创建实际
  createTime: string;
  //用户权限
  roles: [];
  //用户身份
  permissions: [];
};
