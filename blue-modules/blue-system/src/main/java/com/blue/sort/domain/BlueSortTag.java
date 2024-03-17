package com.blue.sort.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 标签对象 blue_sort_tag
 *
 * @author ruoyi
 * @date 2024-03-17
 */
public class BlueSortTag extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    private Long id;

    /**
     * 分类id
     */
    @Excel(name = "分类id")
    private Long sortId;

    /**
     * 标签的名称
     */
    @Excel(name = "标签的名称")
    private String tagName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("sortId", getSortId())
                .append("tagName", getTagName())
                .toString();
    }
}
