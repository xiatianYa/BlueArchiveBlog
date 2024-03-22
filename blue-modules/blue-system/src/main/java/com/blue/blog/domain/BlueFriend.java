package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 友情链接对象 blue_friend
 * 
 * @author ruoyi
 * @date 2024-03-22
 */
public class BlueFriend extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 申请手机号 */
    @Excel(name = "申请手机号")
    private String friendPhone;

    /** 网站名称 */
    @Excel(name = "网站名称")
    private String friendName;

    /** 网站图片 */
    @Excel(name = "网站图片")
    private String friendUrl;

    /** 介绍 */
    @Excel(name = "介绍")
    private String friendIntroduce;

    /** 申请状态 */
    @Excel(name = "申请状态")
    private Long status;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pleaseTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFriendPhone(String friendPhone) 
    {
        this.friendPhone = friendPhone;
    }

    public String getFriendPhone() 
    {
        return friendPhone;
    }
    public void setFriendName(String friendName) 
    {
        this.friendName = friendName;
    }

    public String getFriendName() 
    {
        return friendName;
    }
    public void setFriendUrl(String friendUrl) 
    {
        this.friendUrl = friendUrl;
    }

    public String getFriendUrl() 
    {
        return friendUrl;
    }
    public void setFriendIntroduce(String friendIntroduce) 
    {
        this.friendIntroduce = friendIntroduce;
    }

    public String getFriendIntroduce() 
    {
        return friendIntroduce;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public void setPleaseTime(Date pleaseTime)
    {
        this.pleaseTime = pleaseTime;
    }

    public Date getPleaseTime() 
    {
        return pleaseTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("friendPhone", getFriendPhone())
            .append("friendName", getFriendName())
            .append("friendUrl", getFriendUrl())
            .append("friendIntroduce", getFriendIntroduce())
            .append("status", getStatus())
            .append("pleaseTime", getPleaseTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
