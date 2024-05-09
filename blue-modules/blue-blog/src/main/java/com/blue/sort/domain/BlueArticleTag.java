package com.blue.sort.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 文章标签关联对象 blue_article_tag
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueArticleTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 文章ID */
    @Excel(name = "文章ID")
    private Long articleId;

    /** 标签ID */
    @Excel(name = "标签ID")
    private Long tagId;

    /** 文章名称 */
    @Excel(name = "文章名称")
    @TableField(exist = false)
    private String articleName;

    /** 标签名称 */
    @Excel(name = "标签名称")
    @TableField(exist = false)
    private String tagName;
}
