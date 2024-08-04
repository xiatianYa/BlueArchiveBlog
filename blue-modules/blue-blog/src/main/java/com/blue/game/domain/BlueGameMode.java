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
 * 游戏模式对象 blue_game_mode
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueGameMode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 模式名称 */
    @Excel(name = "模式名称")
    private String name;
}
