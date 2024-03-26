package com.blue.blog.domain.dto;

import com.blue.blog.domain.BlueMusic;
import com.blue.common.core.annotation.Excel;

import java.util.List;

public class BlueMusicListBySort {
    /** 音乐分类名称 */
    @Excel(name = "音乐分类")
    private String sortName;

    /** 音乐列表 */
    @Excel(name = "音乐列表")
    private List<BlueMusic> musicList;

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public List<BlueMusic> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<BlueMusic> musicList) {
        this.musicList = musicList;
    }

    @Override
    public String toString() {
        return "BlueMusicListBySort{" +
                "sortName='" + sortName + '\'' +
                ", musicList=" + musicList +
                '}';
    }
}
