package com.blue.job.domain;

import com.blue.common.core.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 游戏服务器对象 blue_game_server
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueGameServer
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 服务器IP */
    @Excel(name = "服务器IP")
    private String ip;

    /** 服务器IP */
    @Excel(name = "服务器名称")
    private String name;

    /** 服务器端口 */
    @Excel(name = "服务器端口")
    private String port;

    /** 社区ID */
    @Excel(name = "社区ID")
    private Long communityId;

    /** 模式ID */
    @Excel(name = "模式ID")
    private Long modeId;
}
