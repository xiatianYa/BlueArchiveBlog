package com.blue.blog.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 番剧信息对象 blue_pixiv_tv
 * 
 * @author ruoyi
 * @date 2024-04-01
 */
public class BluePixivTv extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 番剧名称 */
    @Excel(name = "番剧名称")
    private String pixivName;

    /** 番剧图片 */
    @Excel(name = "番剧图片")
    private String pixivAvater;

    /** 番剧发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "番剧发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pixivPublish;

    /** 评分 */
    @Excel(name = "评分")
    private String pixivScore;

    /** 番剧类型 */
    @Excel(name = "番剧类型")
    private Long pixivType;

    /** 番剧类型名称 */
    @Excel(name = "番剧类型名称")
    @TableField(exist = false)
    private String typeName;


    /** 声优 */
    @Excel(name = "声优")
    private String pixivVoiceActor;

    /** 番剧简介 */
    @Excel(name = "番剧简介")
    private String pixivSynopsis;

    /** 播放数 */
    @Excel(name = "播放数")
    private Long pixivPlay;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long status;

    /** 是否完结 */
    @Excel(name = "是否完结")
    private Long isEnd;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPixivName(String pixivName) 
    {
        this.pixivName = pixivName;
    }

    public String getPixivName() 
    {
        return pixivName;
    }
    public void setPixivAvater(String pixivAvater) 
    {
        this.pixivAvater = pixivAvater;
    }

    public String getPixivAvater() 
    {
        return pixivAvater;
    }
    public void setPixivPublish(Date pixivPublish) 
    {
        this.pixivPublish = pixivPublish;
    }

    public Date getPixivPublish() 
    {
        return pixivPublish;
    }
    public void setPixivScore(String pixivScore) 
    {
        this.pixivScore = pixivScore;
    }

    public String getPixivScore() 
    {
        return pixivScore;
    }
    public void setPixivType(Long pixivType) 
    {
        this.pixivType = pixivType;
    }

    public Long getPixivType() 
    {
        return pixivType;
    }
    public void setPixivVoiceActor(String pixivVoiceActor) 
    {
        this.pixivVoiceActor = pixivVoiceActor;
    }

    public String getPixivVoiceActor() 
    {
        return pixivVoiceActor;
    }
    public void setPixivSynopsis(String pixivSynopsis) 
    {
        this.pixivSynopsis = pixivSynopsis;
    }

    public String getPixivSynopsis() 
    {
        return pixivSynopsis;
    }
    public void setPixivPlay(Long pixivPlay) 
    {
        this.pixivPlay = pixivPlay;
    }

    public Long getPixivPlay() 
    {
        return pixivPlay;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setIsEnd(Long isEnd) 
    {
        this.isEnd = isEnd;
    }

    public Long getIsEnd() 
    {
        return isEnd;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
