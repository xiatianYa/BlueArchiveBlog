package com.blue.auth.form;


/**
 * 用户注册对象
 *
 * @author ruoyi
 */
public class RegisterBody{
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户密码
     */
    private String passWord;
    /**
     * 用户确认密码
     */
    private String entryPassword;
    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户手机验证码
     */
    private String sms;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEntryPassword() {
        return entryPassword;
    }

    public void setEntryPassword(String entryPassword) {
        this.entryPassword = entryPassword;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
}
