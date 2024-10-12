package com.blue.ws.entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class gameServerMessageVo {
    //IP
    private String ip;
    //端口
    private Integer port;
    //社区ID
    private Integer communityId;
    //服务器名称
    private String name;
    //最小在线人数
    private Integer minPlayers;
    //返回码
    private Integer type;
    //返回信息
    private String message;
}
