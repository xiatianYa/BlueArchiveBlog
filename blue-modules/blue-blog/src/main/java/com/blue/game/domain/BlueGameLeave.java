package com.blue.game.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 游戏留言对象 blue_game_leave
 * 
 * @author ruoyi
 * @date 2024-09-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueGameLeave
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 留言类型 */
    @Excel(name = "留言类型")
    private Long leaveType;

    /** 留言消息 */
    @Excel(name = "留言消息")
    private String leaveMessage;

    /** 留言图片 */
    @Excel(name = "留言图片")
    private String leaveImages;

    /** 创建用户ID */
    @Excel(name = "用户ID")
    private Long createBy;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    @TableField(exist = false)
    private String nickName;

    @TableField(exist = false)
    private String userAvatar;
}
