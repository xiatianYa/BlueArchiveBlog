package com.blue.game.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blue.game.domain.vo.BiliUserDataVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 游戏直播对象 blue_game_live
 * 
 * @author ruoyi
 * @date 2024-09-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueGameLive
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** BiliBili用户Uid */
    @Excel(name = "BiliBili用户Uid")
    private String uid;

    @Excel(name = "用户头像")
    private String avatar;

    @Excel(name = "背景图")
    private String bgUrl;

    @Excel(name = "创建的用户ID")
    private Long createBy;

    @TableField(exist = false)
    private BiliUserDataVo biliUserDataVo;
}
