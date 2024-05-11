package com.blue.blog.entry.dao;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 音乐对象 blue_music
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long status;

}
