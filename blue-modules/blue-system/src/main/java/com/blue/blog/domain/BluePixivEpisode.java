package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 番剧集存储对象 blue_pixiv_episode
 * 
 * @author ruoyi
 * @date 2024-04-01
 */
public class BluePixivEpisode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 番剧ID */
    @Excel(name = "番剧ID")
    private Long pixivId;

    /** 番剧集 */
    @Excel(name = "番剧集")
    private Long pixivChapters;

    /** 番剧地址 */
    @Excel(name = "番剧地址")
    private String pixivUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPixivId(Long pixivId) 
    {
        this.pixivId = pixivId;
    }

    public Long getPixivId() 
    {
        return pixivId;
    }
    public void setPixivChapters(Long pixivChapters) 
    {
        this.pixivChapters = pixivChapters;
    }

    public Long getPixivChapters() 
    {
        return pixivChapters;
    }
    public void setPixivUrl(String pixivUrl) 
    {
        this.pixivUrl = pixivUrl;
    }

    public String getPixivUrl() 
    {
        return pixivUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pixivId", getPixivId())
            .append("pixivChapters", getPixivChapters())
            .append("pixivUrl", getPixivUrl())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
