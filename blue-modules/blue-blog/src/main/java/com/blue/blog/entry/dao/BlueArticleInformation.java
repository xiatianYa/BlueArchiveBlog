package com.blue.blog.entry.dao;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文章信息对象 blue_article_information
 * 
 * @author ruoyi
 * @date 2024-05-07
 */
public class BlueArticleInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 文章ID */
    @Excel(name = "文章ID")
    private Long articleId;

    /** 是否浏览 */
    @Excel(name = "是否浏览")
    private String isBrowse;

    /** 是否点赞 */
    @Excel(name = "是否点赞")
    private String isLike;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setArticleId(Long articleId) 
    {
        this.articleId = articleId;
    }

    public Long getArticleId() 
    {
        return articleId;
    }
    public void setIsBrowse(String isBrowse) 
    {
        this.isBrowse = isBrowse;
    }

    public String getIsBrowse() 
    {
        return isBrowse;
    }
    public void setIsLike(String isLike) 
    {
        this.isLike = isLike;
    }

    public String getIsLike() 
    {
        return isLike;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("articleId", getArticleId())
            .append("isBrowse", getIsBrowse())
            .append("isLike", getIsLike())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
