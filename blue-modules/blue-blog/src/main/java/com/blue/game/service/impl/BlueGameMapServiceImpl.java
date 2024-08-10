package com.blue.game.service.impl;

import java.util.List;

import com.blue.common.core.enums.GameTagStatus;
import com.blue.common.core.enums.GameTypeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.game.mapper.BlueGameMapMapper;
import com.blue.game.domain.BlueGameMap;
import com.blue.game.service.IBlueGameMapService;

/**
 * 游戏地图Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-10
 */
@Service
public class BlueGameMapServiceImpl implements IBlueGameMapService 
{
    @Autowired
    private BlueGameMapMapper blueGameMapMapper;

    /**
     * 查询游戏地图
     * 
     * @param id 游戏地图主键
     * @return 游戏地图
     */
    @Override
    public BlueGameMap selectBlueGameMapById(Long id)
    {
        return blueGameMapMapper.selectBlueGameMapById(id);
    }

    /**
     * 查询游戏地图列表
     * 
     * @param blueGameMap 游戏地图
     * @return 游戏地图
     */
    @Override
    public List<BlueGameMap> selectBlueGameMapList(BlueGameMap blueGameMap)
    {
        List<BlueGameMap> blueGameMaps = blueGameMapMapper.selectBlueGameMapList(blueGameMap);
        blueGameMaps.forEach(item -> {
            item.setTagName(GameTagStatus.getInfoByCode(item.getTag()));
            item.setTypeName(GameTypeStatus.getInfoByCode(item.getType()));
        });
        return blueGameMaps;
    }

    /**
     * 新增游戏地图
     * 
     * @param blueGameMap 游戏地图
     * @return 结果
     */
    @Override
    public int insertBlueGameMap(BlueGameMap blueGameMap)
    {
        return blueGameMapMapper.insertBlueGameMap(blueGameMap);
    }

    /**
     * 修改游戏地图
     * 
     * @param blueGameMap 游戏地图
     * @return 结果
     */
    @Override
    public int updateBlueGameMap(BlueGameMap blueGameMap)
    {
        return blueGameMapMapper.updateBlueGameMap(blueGameMap);
    }

    /**
     * 批量删除游戏地图
     * 
     * @param ids 需要删除的游戏地图主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameMapByIds(Long[] ids)
    {
        return blueGameMapMapper.deleteBlueGameMapByIds(ids);
    }

    /**
     * 删除游戏地图信息
     * 
     * @param id 游戏地图主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameMapById(Long id)
    {
        return blueGameMapMapper.deleteBlueGameMapById(id);
    }
}
