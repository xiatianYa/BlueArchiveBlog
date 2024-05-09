package com.blue.blog.entry.dto;

import com.blue.blog.entry.dao.BlueMusic;
import com.blue.common.core.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueMusicListBySortDTO {
    /** 音乐分类名称 */
    @Excel(name = "音乐分类")
    private String sortName;

    /** 音乐列表 */
    @Excel(name = "音乐列表")
    private List<BlueMusic> musicList;

}
