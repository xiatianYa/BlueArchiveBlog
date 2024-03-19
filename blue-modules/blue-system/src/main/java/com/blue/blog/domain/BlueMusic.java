package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 音乐对象 blue_music
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public class BlueMusic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String imgUrl;

    /** 音乐路径 */
    @Excel(name = "音乐路径")
    private String musicUrl;

    /** 音乐分类 */
    @Excel(name = "音乐分类")
    private Long sortId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }
    public void setMusicUrl(String musicUrl) 
    {
        this.musicUrl = musicUrl;
    }

    public String getMusicUrl() 
    {
        return musicUrl;
    }
    public void setSortId(Long sortId) 
    {
        this.sortId = sortId;
    }

    public Long getSortId() 
    {
        return sortId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("imgUrl", getImgUrl())
            .append("musicUrl", getMusicUrl())
            .append("sortId", getSortId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
