package com.blue.sort.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 相册分类信息对象 blue_photo_sort
 * 
 * @author ruoyi
 * @date 2024-03-17
 */
public class BluePhotoSort extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 相册分类id
     */
    private Long id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String sortName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSortName(String sortName)
    {
        this.sortName = sortName;
    }

    public String getSortName() {
        return sortName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("sortName", getSortName())
                .toString();
    }
}
