package com.blue.sort.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 标签对象 blue_sort_tag
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueSortTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 分类ID */
    @Excel(name = "分类名称")
    private Long sortId;

    /** 标签的名称 */
    @Excel(name = "标签的名称")
    private String tagName;
    /** 文章标签包含数量 */
    @Excel(name = "文章标签包含数量")
    @TableField(exist = false)
    private Integer articleTagNumber;
}
