package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 番剧弹幕对象 blue_pixiv_leave_message
 * 
 * @author ruoyi
 * @date 2024-04-05
 */
public class BluePixivLeaveMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 番剧ID */
    @Excel(name = "番剧ID")
    private Long pixivId;

    /** 集ID */
    @Excel(name = "集ID")
    private Long episodeId;

    /** 是否描边 0表示描边,1不描边 */
    @Excel(name = "是否描边 0表示描边,1不描边")
    private Integer border;

    /** 背景色 */
    @Excel(name = "背景色")
    private String color;

    /** 弹幕内容 */
    @Excel(name = "弹幕内容")
    private String text;

    /** 弹幕发送时间 */
    @Excel(name = "弹幕发送时间")
    private BigDecimal time;

    /** 弹幕模式: 0表示滚动, 1静止 */
    @Excel(name = "弹幕模式: 0表示滚动, 1静止")
    private Integer mode;

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
    public void setEpisodeId(Long episodeId) 
    {
        this.episodeId = episodeId;
    }

    public Long getEpisodeId() 
    {
        return episodeId;
    }
    public void setBorder(Integer border) 
    {
        this.border = border;
    }

    public Integer getBorder() 
    {
        return border;
    }
    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
    }
    public void setText(String text) 
    {
        this.text = text;
    }

    public String getText() 
    {
        return text;
    }
    public void setTime(BigDecimal time) 
    {
        this.time = time;
    }

    public BigDecimal getTime() 
    {
        return time;
    }
    public void setMode(Integer mode) 
    {
        this.mode = mode;
    }

    public Integer getMode() 
    {
        return mode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pixivId", getPixivId())
            .append("episodeId", getEpisodeId())
            .append("border", getBorder())
            .append("color", getColor())
            .append("text", getText())
            .append("time", getTime())
            .append("mode", getMode())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
