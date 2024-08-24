package com.blue.game.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import com.blue.game.domain.BlueGameServer;

/**
 * 游戏服务器Service接口
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
public interface IBlueGameServerService 
{
    /**
     * 查询游戏服务器
     * 
     * @param id 游戏服务器主键
     * @return 游戏服务器
     */
    public BlueGameServer selectBlueGameServerById(Long id);

    /**
     * 查询游戏服务器列表
     * 
     * @param blueGameServer 游戏服务器
     * @return 游戏服务器集合
     */
    public List<BlueGameServer> selectBlueGameServerList(BlueGameServer blueGameServer);

    /**
     * 新增游戏服务器
     * 
     * @param blueGameServer 游戏服务器
     * @return 结果
     */
    public int insertBlueGameServer(BlueGameServer blueGameServer);

    /**
     * 修改游戏服务器
     * 
     * @param blueGameServer 游戏服务器
     * @return 结果
     */
    public int updateBlueGameServer(BlueGameServer blueGameServer);

    /**
     * 批量删除游戏服务器
     * 
     * @param ids 需要删除的游戏服务器主键集合
     * @return 结果
     */
    public int deleteBlueGameServerByIds(Long[] ids);

    /**
     * 删除游戏服务器信息
     * 
     * @param id 游戏服务器主键
     * @return 结果
     */
    public int deleteBlueGameServerById(Long id);

    String getSteamApi(List<String> paths);
}
