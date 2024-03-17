package com.blue.sort.domain;

import java.util.List;

import com.blue.blog.domain.BlueMusic;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 音乐分类对象 blue_music_sort
 *
 * @author ruoyi
 * @date 2024-03-17
 */
public class BlueMusicSort extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 音乐分类ID
     */
    private Long id;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String sortName;

    /**
     * 音乐信息
     */
    private List<BlueMusic> blueMusicList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public List<BlueMusic> getBlueMusicList() {
        return blueMusicList;
    }

    public void setBlueMusicList(List<BlueMusic> blueMusicList) {
        this.blueMusicList = blueMusicList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("sortName", getSortName())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("blueMusicList", getBlueMusicList())
                .toString();
    }
}
