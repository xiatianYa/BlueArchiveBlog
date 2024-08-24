package com.blue.game.mapper;

import java.util.List;
import com.blue.game.domain.BlueGameMap;

/**
 * 游戏地图Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-24
 */
public interface BlueGameMapMapper 
{
    /**
     * 查询游戏地图
     * 
     * @param id 游戏地图主键
     * @return 游戏地图
     */
    public BlueGameMap selectBlueGameMapById(Long id);

    /**
     * 查询游戏地图列表
     * 
     * @param blueGameMap 游戏地图
     * @return 游戏地图集合
     */
    public List<BlueGameMap> selectBlueGameMapList(BlueGameMap blueGameMap);

    /**
     * 新增游戏地图
     * 
     * @param blueGameMap 游戏地图
     * @return 结果
     */
    public int insertBlueGameMap(BlueGameMap blueGameMap);

    /**
     * 修改游戏地图
     * 
     * @param blueGameMap 游戏地图
     * @return 结果
     */
    public int updateBlueGameMap(BlueGameMap blueGameMap);

    /**
     * 删除游戏地图
     * 
     * @param id 游戏地图主键
     * @return 结果
     */
    public int deleteBlueGameMapById(Long id);

    /**
     * 批量删除游戏地图
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueGameMapByIds(Long[] ids);
}
