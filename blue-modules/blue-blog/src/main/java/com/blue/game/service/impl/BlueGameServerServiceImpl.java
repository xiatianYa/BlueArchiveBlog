package com.blue.game.service.impl;

import java.util.List;

import com.blue.common.core.enums.GameModeStatus;
import com.blue.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.game.mapper.BlueGameServerMapper;
import com.blue.game.domain.BlueGameServer;
import com.blue.game.service.IBlueGameServerService;

/**
 * 游戏服务器Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
@Service
public class BlueGameServerServiceImpl implements IBlueGameServerService 
{
    @Autowired
    private BlueGameServerMapper blueGameServerMapper;

    /**
     * 查询游戏服务器
     * 
     * @param id 游戏服务器主键
     * @return 游戏服务器
     */
    @Override
    public BlueGameServer selectBlueGameServerById(Long id)
    {
        return blueGameServerMapper.selectBlueGameServerById(id);
    }

    /**
     * 查询游戏服务器列表
     * 
     * @param blueGameServer 游戏服务器
     * @return 游戏服务器
     */
    @Override
    public List<BlueGameServer> selectBlueGameServerList(BlueGameServer blueGameServer)
    {
        List<BlueGameServer> blueGameServers = blueGameServerMapper.selectBlueGameServerList(blueGameServer);
        blueGameServers.forEach(item->{
            item.setModeName(GameModeStatus.getInfoByCode(item.getModeId()));
        });
        return blueGameServers;
    }

    /**
     * 新增游戏服务器
     * 
     * @param blueGameServer 游戏服务器
     * @return 结果
     */
    @Override
    public int insertBlueGameServer(BlueGameServer blueGameServer)
    {
        blueGameServer.setCreateTime(DateUtils.getNowDate());
        return blueGameServerMapper.insertBlueGameServer(blueGameServer);
    }

    /**
     * 修改游戏服务器
     * 
     * @param blueGameServer 游戏服务器
     * @return 结果
     */
    @Override
    public int updateBlueGameServer(BlueGameServer blueGameServer)
    {
        blueGameServer.setUpdateTime(DateUtils.getNowDate());
        return blueGameServerMapper.updateBlueGameServer(blueGameServer);
    }

    /**
     * 批量删除游戏服务器
     * 
     * @param ids 需要删除的游戏服务器主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameServerByIds(Long[] ids)
    {
        return blueGameServerMapper.deleteBlueGameServerByIds(ids);
    }

    /**
     * 删除游戏服务器信息
     * 
     * @param id 游戏服务器主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameServerById(Long id)
    {
        return blueGameServerMapper.deleteBlueGameServerById(id);
    }
}
