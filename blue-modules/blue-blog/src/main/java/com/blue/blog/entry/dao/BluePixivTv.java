package com.blue.blog.entry.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 番剧信息对象 blue_pixiv_tv
 * 
 * @author ruoyi
 * @date 2024-04-01
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Integer pixivPlay;

    /** 弹幕数 */
    @Excel(name = "弹幕数")
    private Long pixivDanmaku;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long status;

    /** 是否完结 */
    @Excel(name = "是否完结")
    private Long isEnd;


}
