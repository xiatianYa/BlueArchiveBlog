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
     * 背景类型
     */
    @Excel(name = "背景类型")
    private String bgType;

    /**
     * 背景资源存储路径
     */
    @Excel(name = "背景资源存储路径")
    private String bgUrl;

    /**
     * 背景使用类型
     */
    @Excel(name = "背景使用类型")
    private String bgPosition;

    /**
     * 是否删除（0表示未删除, 1表示已逻辑删除）
     */
    @Excel(name = "是否删除", readConverterExp = "0=表示未删除,,1=表示已逻辑删除")
    private String isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBgType() {
        return bgType;
    }

    public void setBgType(String bgType) {
        this.bgType = bgType;
    }

    public String getBgUrl() {
        return bgUrl;
    }

    public void setBgUrl(String bgUrl) {
        this.bgUrl = bgUrl;
    }

    public String getBgPosition() {
        return bgPosition;
    }

    public void setBgPosition(String bgPosition) {
        this.bgPosition = bgPosition;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
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
                .append("isDelete", getIsDelete())
                .toString();
    }
}
