package com.blue.blog.entry.dao;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 公告信息对象 blue_notice
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 公告的标题 */
    @Excel(name = "公告的标题")
    private String noticeTitle;

    /** 公告的内容 */
    @Excel(name = "公告的内容")
    private String noticeContent;
}
