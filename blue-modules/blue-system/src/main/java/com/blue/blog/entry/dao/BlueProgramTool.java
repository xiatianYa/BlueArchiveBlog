package com.blue.blog.entry.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 编程工具对象 blue_program_tool
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueProgramTool extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 工具分类ID */
    @Excel(name = "工具分类ID")
    private Long sortId;

    /** 工具分类名称 */
    @Excel(name = "工具分类名称")
    @TableField(exist = false)
    private String sortName;

    /** 工具名称 */
    @Excel(name = "工具名称")
    private String toolName;

    /** 工具简介 */
    @Excel(name = "工具简介")
    private String toolDescribe;
    /** 工具路径 */
    @Excel(name = "工具简介")
    private String toolUrl;
    /** 工具图片 */
    @Excel(name = "工具图片")
    private String toolAvater;

}
