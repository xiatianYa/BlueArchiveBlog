package com.blue.job.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameServerEntry {
    //服务器地址
    private final String addr;
    //服务器端口
    private final Integer gameport;
    //服务器名称
    private final String name;
    //在线玩家
    private final Integer players;
    //最大在线玩家
    @JSONField(alternateNames = {"max_players","maxplayers"},name = "max_players")
    private final Integer maxplayers;
    //产品
    private final String product;
    //SteamId
    private final Long steamid;
    //appid
    private final Integer appid;
    //GameDir
    private final String gamedir;
    //版本
    private final String version;
    //区域
    private final String region;
    //机器人
    private final Integer bots;
    //地图
    private final String map;
    //安全验证
    private final Boolean secure;
    //educated
    private final Boolean dedicated;
    //os
    private final String os;
    //gameType
    private final String gametype;

}
