package com.blue.game.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 游戏地图对象 blue_game_map
 * 
 * @author ruoyi
 * @date 2024-08-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueGameMap extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 地图名称 */
    @Excel(name = "地图名称")
    private String name;

    /** 译名 */
    @Excel(name = "译名")
    private String label;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String mapUrl;

    /** 模式名称 */
    @Excel(name = "模式名称")
    private Long modeId;

    /** 地图难度 */
    @Excel(name = "地图难度")
    private Long type;

    /** 地图难度名称 */
    @Excel(name = "地图难度名称")
    private String typeName;

    /** 地图标签 */
    @Excel(name = "地图标签")
    private String tag;

    /** 地图标签名称 */
    @Excel(name = "地图标签名称")
    private String tagName;


}
