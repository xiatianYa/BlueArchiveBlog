package com.blue.game.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 数据统计对象 blue_game_statistics
 * 
 * @author ruoyi
 * @date 2024-09-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueGameStatistics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 社区id */
    @Excel(name = "社区id")
    private Long communityId;

    /** 社区在线人数 */
    @Excel(name = "社区在线人数")
    private Long communityPlay;

    /** 统计时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "统计时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

}
