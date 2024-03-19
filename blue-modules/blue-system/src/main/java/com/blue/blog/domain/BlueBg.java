package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 网站背景资源信息对象 blue_bg
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public class BlueBg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 背景类型 */
    @Excel(name = "背景类型")
    private String bgType;

    /** 背景路径 */
    @Excel(name = "背景路径")
    private String bgUrl;

    /** 背景位置类型 */
    @Excel(name = "背景位置类型")
    private String bgPosition;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBgType(String bgType) 
    {
        this.bgType = bgType;
    }

    public String getBgType() 
    {
        return bgType;
    }
    public void setBgUrl(String bgUrl) 
    {
        this.bgUrl = bgUrl;
    }

    public String getBgUrl() 
    {
        return bgUrl;
    }
    public void setBgPosition(String bgPosition) 
    {
        this.bgPosition = bgPosition;
    }

    public String getBgPosition() 
    {
        return bgPosition;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bgType", getBgType())
            .append("bgUrl", getBgUrl())
            .append("bgPosition", getBgPosition())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
