package com.blue.ws.entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class gamerServerVo {
    //IP
    private String ip;
    //端口
    private Integer port;
    //在线人数
    private Integer players;
    //最大在线人数
    private Integer maxPlayers;
    //游戏状态
    private Boolean status;
}
