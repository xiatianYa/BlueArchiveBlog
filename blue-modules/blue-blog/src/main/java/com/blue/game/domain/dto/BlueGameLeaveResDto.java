package com.blue.game.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blue.common.core.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 游戏留言对象 blue_game_leave
 *
 * @author ruoyi
 * @date 2024-09-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueGameLeaveResDto {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 留言类型
     */
    @Excel(name = "留言类型")
    private Long leaveType;

    /**
     * 留言消息
     */
    @Excel(name = "留言消息")
    private String leaveMessage;

    /**
     * 留言图片
     */
    @Excel(name = "留言图片")
    private String leaveImages;

    /**
     * 留言图片转一下，前端尽量不做逻辑
     */
    private String[] lstImages;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveStart;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveEnd;

    /**
     * 创建用户ID
     */
    @Excel(name = "用户ID")
    private Integer createBy;

    @TableField(exist = false)
    private String nickName;

    @TableField(exist = false)
    private String avatar;
}
