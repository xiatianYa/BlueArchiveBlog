package com.blue.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 音乐对象 blue_music
 *
 * @author ruoyi
 * @date 2024-03-17
 */
public class BlueMusic extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 音乐ID
     */
    private Long id;

    /**
     * 图片路径
     */
    @Excel(name = "图片路径")
    private String imgUrl;

    /**
     * 路径
     */
    @Excel(name = "路径")
    private String musicUrl;

    /**
     * 分类ID
     */
    @Excel(name = "分类ID")
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

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("imgUrl", getImgUrl())
                .append("musicUrl", getMusicUrl())
                .append("sortId", getSortId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
