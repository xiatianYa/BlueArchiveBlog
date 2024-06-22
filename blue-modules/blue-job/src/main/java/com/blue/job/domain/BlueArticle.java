package com.blue.job.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

import java.util.List;

/**
 * 文章对象 blue_article
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
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

    /** 逻辑删除 */
    @TableField("del_flag")
    private Integer delFlag;

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getArticleDescribe() {
        return articleDescribe;
    }

    public void setArticleDescribe(String articleDescribe) {
        this.articleDescribe = articleDescribe;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}