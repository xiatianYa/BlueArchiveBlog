package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 相册对象 blue_photo
 * 
 * @author ruoyi
 * @date 2024-03-19
 */

public class BluePhoto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 分类id */
    @Excel(name = "分类id")
    private Long sortId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;
    /** 图片名称 */
    @Excel(name = "图片名称")
    private String photoName;
    /** 相册图片地址 */
    @Excel(name = "相册图片地址")
    private String photoUrl;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setSortId(Long sortId) 
    {
        this.sortId = sortId;
    }

    public Long getSortId() 
    {
        return sortId;
    }
    public void setPhotoUrl(String photoUrl) 
    {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() 
    {
        return photoUrl;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    @Override
    public String toString() {
        return "BluePhoto{" +
                "id=" + id +
                ", userId=" + userId +
                ", sortId=" + sortId +
                ", userName='" + userName + '\'' +
                ", photoName='" + photoName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", status=" + status +
                '}';
    }
}
