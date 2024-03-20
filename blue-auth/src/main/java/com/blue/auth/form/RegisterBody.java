package com.blue.auth.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注册对象
 *
 * @author ruoyi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBody extends LoginBody {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户确认密码
     */
    private String entryPassword;
    /**
     * 用户头像
     */
    private String avater;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 用户手机验证码
     */
    private String sms;
}
