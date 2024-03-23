package com.blue.sort.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 分类对象 blue_sort
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public class BlueSort extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类id */
    private Long id;

    /** 分类的名称 */
    @Excel(name = "分类的名称")
    private String sortName;
    /** 当前分类下文章的数量 */
    @Excel(name = "分类的数量")
    private Integer sortNumber;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSortName(String sortName) 
    {
        this.sortName = sortName;
    }

    public String getSortName() 
    {
        return sortName;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }
}
