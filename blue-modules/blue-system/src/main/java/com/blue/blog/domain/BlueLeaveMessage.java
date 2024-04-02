package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 弹幕对象 blue_leave_message
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueLeaveMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 用户ID */
    @Excel(name = "用户名称")
    private String userName;

    /** 弹幕内容 */
    @Excel(name = "弹幕内容")
    private String content;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String userAvater;

    /** 弹幕高度 */
    @Excel(name = "弹幕高度")
    private Long barrageHeight;
}
