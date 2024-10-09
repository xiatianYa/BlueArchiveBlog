package com.blue.game.service.impl;

import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.common.core.enums.GameMapTagStatus;
import com.blue.common.core.enums.GameTypeStatus;
import com.blue.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.game.mapper.BlueGameMapMapper;
import com.blue.game.domain.BlueGameMap;
import com.blue.game.service.IBlueGameMapService;

import javax.annotation.Resource;

/**
 * 游戏地图Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-24
 */
@Service
public class BlueGameMapServiceImpl implements IBlueGameMapService 
{
    @Resource
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
            item.setTagName("");
            if (StringUtils.isNotNull(item.getTag())){
                String[] tags = item.getTag().split(",");
                if (StringUtils.isNotEmpty(tags)){
                    for (String tag : tags) {
                        String tagName = GameMapTagStatus.getInfoByCode(Long.valueOf(tag));
                        if (StringUtils.isNotNull(tagName)){
                            item.setTagName(item.getTagName() + tagName + ",");
                        }
                    }
                }
            }
            if (StringUtils.isNotNull(item.getType())){
                item.setTypeName(GameTypeStatus.getInfoByCode(item.getType()));
            }
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

    @Override
    public List<BlueGameMap> listAll() {
        List<BlueGameMap> blueGameMaps = blueGameMapMapper.selectList(new LambdaQueryWrapper<>());
        blueGameMaps.forEach(item -> {
            item.setTagName("");
            if (StringUtils.isNotNull(item.getTag())){
                String[] tags = item.getTag().split(",");
                if (StringUtils.isNotEmpty(tags)){
                    for (String tag : tags) {
                        String tagName = GameMapTagStatus.getInfoByCode(Long.valueOf(tag));
                        if (StringUtils.isNotNull(tagName)){
                            item.setTagName(item.getTagName() + tagName + ",");
                        }
                    }
                }
            }
            if (StringUtils.isNotNull(item.getType())){
                item.setTypeName(GameTypeStatus.getInfoByCode(item.getType()));
            }
        });
        return blueGameMaps;
    }
}
