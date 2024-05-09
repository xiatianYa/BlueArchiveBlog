package com.blue.blog.entry.dao;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 友情链接对象 blue_friend
 * 
 * @author ruoyi
 * @date 2024-03-22
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueFriend extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 申请手机号 */
    @Excel(name = "申请手机号")
    private String friendPhone;

    /** 网站名称 */
    @Excel(name = "网站名称")
    private String friendName;

    /** 网站图片 */
    @Excel(name = "网站图片")
    private String friendUrl;

    /** 介绍 */
    @Excel(name = "介绍")
    private String friendIntroduce;

    /** 申请状态 */
    @Excel(name = "申请状态")
    private Long status;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pleaseTime;

}
