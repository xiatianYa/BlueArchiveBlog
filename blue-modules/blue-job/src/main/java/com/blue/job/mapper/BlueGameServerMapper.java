package com.blue.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.job.domain.BlueGameServer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 游戏服务器Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
@Mapper
public interface BlueGameServerMapper extends BaseMapper<BlueGameServer>
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
     * 删除游戏服务器
     * 
     * @param id 游戏服务器主键
     * @return 结果
     */
    public int deleteBlueGameServerById(Long id);

    /**
     * 批量删除游戏服务器
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueGameServerByIds(Long[] ids);
}
