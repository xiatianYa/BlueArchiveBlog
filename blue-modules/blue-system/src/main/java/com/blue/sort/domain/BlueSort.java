package com.blue.sort.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 分类对象 blue_sort
 *
 * @author ruoyi
 * @date 2024-03-17
 */
public class BlueSort extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    private Long id;

    /**
     * 分类的名称
     */
    @Excel(name = "分类的名称")
    private String sortName;

    /**
     * 标签信息
     */
    private List<BlueSortTag> blueSortTagList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public List<BlueSortTag> getBlueSortTagList() {
        return blueSortTagList;
    }

    public void setBlueSortTagList(List<BlueSortTag> blueSortTagList) {
        this.blueSortTagList = blueSortTagList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("sortName", getSortName())
                .append("blueSortTagList", getBlueSortTagList())
                .toString();
    }
}
