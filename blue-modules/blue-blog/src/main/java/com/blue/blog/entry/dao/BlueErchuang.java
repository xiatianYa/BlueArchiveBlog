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
 * 二创信息对象 blue_erchuang
 * 
 * @author ruoyi
 * @date 2024-04-02
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueErchuang extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 二创作者 */
    @Excel(name = "二创作者")
    private String ecAuthor;

    /** 二创标题 */
    @Excel(name = "二创标题")
    private String ecName;

    /** 二创图片 */
    @Excel(name = "二创图片")
    private String ecAvater;

    /** 二创路径 */
    @Excel(name = "二创路径")
    private String ecUrl;

    /** 二创发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "二创发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ecPublish;

    /** 二创简介 */
    @Excel(name = "二创简介")
    private String ecSynopsis;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long status;
}
