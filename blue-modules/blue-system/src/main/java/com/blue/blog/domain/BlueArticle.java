package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

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
    private Long id;

    /** 用户ID */
    @Excel(name = "用户名称")
    private Long userId;

    /** 分类ID */
    @Excel(name = "分类名称")
    private Long sortId;
    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String sortName;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long status;

    /** 文章视频 */
    @Excel(name = "文章视频")
    private String videoUrl;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String content;

    /** 文章图片 */
    @Excel(name = "文章图片")
    private String cover;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
