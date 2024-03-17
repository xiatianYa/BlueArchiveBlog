package com.blue.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 弹幕对象 blue_leave_message
 *
 * @author ruoyi
 * @date 2024-03-17
 */
public class BlueLeaveMessage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 弹幕ID
     */
    private Long id;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 内容
     */
    @Excel(name = "内容")
    private String content;

    /**
     * 高度
     */
    @Excel(name = "高度")
    private Long barrageHeight;

    /**
     * 是否删除（0表示未删除，1表示已删除）
     */
    @Excel(name = "是否删除", readConverterExp = "0=表示未删除，1表示已删除")
    private String isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getBarrageHeight() {
        return barrageHeight;
    }

    public void setBarrageHeight(Long barrageHeight) {
        this.barrageHeight = barrageHeight;
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
                .append("userId", getUserId())
                .append("content", getContent())
                .append("barrageHeight", getBarrageHeight())
                .append("createTime", getCreateTime())
                .append("isDelete", getIsDelete())
                .toString();
    }
}
