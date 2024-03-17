package com.blue.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 网站背景资源信息对象 blue_bg
 * 
 * @author ruoyi
 * @date 2024-03-17
 */
public class BlueBg extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 背景表ID
     */
    private Long id;

    /**
     * 资料类型
     */
    @Excel(name = "资料类型")
    private String bgType;

    /**
     * 资料路径
     */
    @Excel(name = "资料路径")
    private String bgUrl;

    /** 资源位置类型 */
    @Excel(name = "资源位置类型")
    private String bgPosition;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBgType() {
        return bgType;
    }

    public void setBgType(String bgType)
    {
        this.bgType = bgType;
    }

    public String getBgUrl() {
        return bgUrl;
    }

    public void setBgUrl(String bgUrl)
    {
        this.bgUrl = bgUrl;
    }

    public String getBgPosition()
    {
        return bgPosition;
    }

    public void setBgPosition(String bgPosition)
    {
        this.bgPosition = bgPosition;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("bgType", getBgType())
                .append("bgUrl", getBgUrl())
                .append("bgPosition", getBgPosition())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
            .toString();
    }
}
