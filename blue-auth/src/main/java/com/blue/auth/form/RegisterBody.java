package com.blue.auth.form;

/**
 * 用户注册对象
 *
 * @author ruoyi
 */
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
     * 用户手机验证码
     */
    private String phoneCode;
}
