package com.blue.auth.form;

/**
 * 用户第三方登录接口
 *
 * @author summer
 */
public class OauthBody {
    /**
     * 用户Token
     */
    private String accessToken;
    /**
     * 用户OpenId
     */
    private String openId;
    /**
     * 登录类型 QQ
     */
    private Integer type;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
