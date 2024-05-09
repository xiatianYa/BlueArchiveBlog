package com.blue.blog.entry.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import com.blue.sort.domain.BlueArticleTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 文章对象 blue_article
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 用户ID */
    @Excel(name = "用户名称")
    @TableField("user_id")
    private Long userId;

    /** 分类ID */
    @Excel(name = "分类ID")
    @TableField("sort_id")
    private Long sortId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    @TableField(exist = false)
    private String sortName;

    /** 用户名称 */
    @Excel(name = "用户名称")
    @TableField(exist = false)
    private String userName;
    /** 文章名称 */
    @Excel(name = "文章名称")
    @TableField("article_name")
    private String articleName;

    /** 文章描述 */
    @Excel(name = "文章描述")
    @TableField("article_describe")
    private String articleDescribe;

    /** 审核状态 */
    @Excel(name = "审核状态")
    @TableField("status")
    private Long status;

    /** 文章视频 */
    @Excel(name = "文章视频")
    @TableField("video_url")
    private String videoUrl;

    /** 文章内容 */
    @Excel(name = "文章内容")
    @TableField("content")
    private String content;

    /** 文章图片 */
    @Excel(name = "文章图片")
    @TableField("cover")
    private String cover;

    /** 关联标签列表 */
    @TableField(exist = false)
    private List<BlueArticleTag> tagList;

    /** 文章热度数 */
    @TableField(exist = false)
    private Integer hot;

    /** 文章评论数 */
    @TableField(exist = false)
    private Integer comment;
    /** 文章点赞数 */
    @TableField(exist = false)
    private Integer like;

    /** 逻辑删除 */
    @TableField("del_flag")
    private Integer delFlag;
}