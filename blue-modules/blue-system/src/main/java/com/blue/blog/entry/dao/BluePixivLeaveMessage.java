package com.blue.blog.entry.dao;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 番剧弹幕对象 blue_pixiv_leave_message
 * 
 * @author ruoyi
 * @date 2024-04-05
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
