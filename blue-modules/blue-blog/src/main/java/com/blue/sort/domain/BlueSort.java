package com.blue.sort.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分类对象 blue_sort
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueSort extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类id */
    private Long id;

    /** 分类的名称 */
    @Excel(name = "分类名称")
    private String sortName;
    @Excel(name="分类描述")
    private String sortDescribe;
    /** 当前分类下文章的数量 */
    @Excel(name = "分类的数量")
    @TableField(exist = false)
    private Integer sortNumber;

    /** 当前分类下标签列表 */
    @Excel(name = "当前分类下标签列表")
    @TableField(exist = false)
    private List<BlueSortTag> tagList;


}
