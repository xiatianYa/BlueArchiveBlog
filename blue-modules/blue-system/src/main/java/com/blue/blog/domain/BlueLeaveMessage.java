package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 弹幕对象 blue_leave_message
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public class BlueLeaveMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 用户ID */
    @Excel(name = "用户名称")
    private String userName;

    /** 弹幕内容 */
    @Excel(name = "弹幕内容")
    private String content;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String userAvater;

    /** 弹幕高度 */
    @Excel(name = "弹幕高度")
    private Long barrageHeight;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setUserAvater(String userAvater) 
    {
        this.userAvater = userAvater;
    }

    public String getUserAvater() 
    {
        return userAvater;
    }
    public void setBarrageHeight(Long barrageHeight) 
    {
        this.barrageHeight = barrageHeight;
    }

    public Long getBarrageHeight() 
    {
        return barrageHeight;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
