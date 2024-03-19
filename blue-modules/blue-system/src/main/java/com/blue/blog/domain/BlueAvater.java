package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户头像列存储对象 blue_avater
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public class BlueAvater extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 头像id */
    private Long id;

    /** 头像路径 */
    @Excel(name = "头像路径")
    private String avaterUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAvaterUrl(String avaterUrl) 
    {
        this.avaterUrl = avaterUrl;
    }

    public String getAvaterUrl() 
    {
        return avaterUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("avaterUrl", getAvaterUrl())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
