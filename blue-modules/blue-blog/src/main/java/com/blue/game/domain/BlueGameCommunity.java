package com.blue.game.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 游戏社区对象 blue_game_community
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueGameCommunity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 社区名称 */
    @Excel(name = "社区名称")
    private String name;

}
