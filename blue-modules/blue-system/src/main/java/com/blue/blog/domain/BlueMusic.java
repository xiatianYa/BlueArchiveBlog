package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 音乐对象 blue_music
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public class BlueMusic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String imgUrl;

    /** 音乐路径 */
    @Excel(name = "音乐路径")
    private String musicUrl;
    /** 音乐名称 */
    @Excel(name = "音乐名称")
    private String musicName;

    /** 音乐分类 */
    @Excel(name = "音乐分类")
    private Long sortId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    @Override
    public String toString() {
        return "BlueMusic{" +
                "id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                ", musicName='" + musicName + '\'' +
                ", sortId=" + sortId +
                '}';
    }
}
