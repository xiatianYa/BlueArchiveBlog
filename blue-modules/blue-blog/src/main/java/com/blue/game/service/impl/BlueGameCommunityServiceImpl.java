package com.blue.game.service.impl;

import java.util.List;
import com.blue.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.game.mapper.BlueGameCommunityMapper;
import com.blue.game.domain.BlueGameCommunity;
import com.blue.game.service.IBlueGameCommunityService;

/**
 * 游戏社区Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
@Service
public class BlueGameCommunityServiceImpl implements IBlueGameCommunityService 
{
    @Autowired
    private BlueGameCommunityMapper blueGameCommunityMapper;

    /**
     * 查询游戏社区
     * 
     * @param id 游戏社区主键
     * @return 游戏社区
     */
    @Override
    public BlueGameCommunity selectBlueGameCommunityById(Long id)
    {
        return blueGameCommunityMapper.selectBlueGameCommunityById(id);
    }

    /**
     * 查询游戏社区列表
     * 
     * @param blueGameCommunity 游戏社区
     * @return 游戏社区
     */
    @Override
    public List<BlueGameCommunity> selectBlueGameCommunityList(BlueGameCommunity blueGameCommunity)
    {
        return blueGameCommunityMapper.selectBlueGameCommunityList(blueGameCommunity);
    }

    /**
     * 新增游戏社区
     * 
     * @param blueGameCommunity 游戏社区
     * @return 结果
     */
    @Override
    public int insertBlueGameCommunity(BlueGameCommunity blueGameCommunity)
    {
        blueGameCommunity.setCreateTime(DateUtils.getNowDate());
        return blueGameCommunityMapper.insertBlueGameCommunity(blueGameCommunity);
    }

    /**
     * 修改游戏社区
     * 
     * @param blueGameCommunity 游戏社区
     * @return 结果
     */
    @Override
    public int updateBlueGameCommunity(BlueGameCommunity blueGameCommunity)
    {
        blueGameCommunity.setUpdateTime(DateUtils.getNowDate());
        return blueGameCommunityMapper.updateBlueGameCommunity(blueGameCommunity);
    }

    /**
     * 批量删除游戏社区
     * 
     * @param ids 需要删除的游戏社区主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameCommunityByIds(Long[] ids)
    {
        return blueGameCommunityMapper.deleteBlueGameCommunityByIds(ids);
    }

    /**
     * 删除游戏社区信息
     * 
     * @param id 游戏社区主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameCommunityById(Long id)
    {
        return blueGameCommunityMapper.deleteBlueGameCommunityById(id);
    }
}
