package com.blue.blog.domain.dto;

import com.blue.blog.domain.BlueMusic;
import com.blue.common.core.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueMusicListBySort {
    /** 音乐分类名称 */
    @Excel(name = "音乐分类")
    private String sortName;

    /** 音乐列表 */
    @Excel(name = "音乐列表")
    private List<BlueMusic> musicList;

}
