package com.blue.job.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Long id;

    /** 社区名称 */
    private String name;
}
