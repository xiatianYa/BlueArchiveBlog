package com.blue.game.service;

import java.util.List;
import com.blue.game.domain.BlueGameCommunity;

/**
 * 游戏社区Service接口
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
public interface IBlueGameCommunityService 
{
    /**
     * 查询游戏社区
     * 
     * @param id 游戏社区主键
     * @return 游戏社区
     */
    public BlueGameCommunity selectBlueGameCommunityById(Long id);

    /**
     * 查询游戏社区列表
     * 
     * @param blueGameCommunity 游戏社区
     * @return 游戏社区集合
     */
    public List<BlueGameCommunity> selectBlueGameCommunityList(BlueGameCommunity blueGameCommunity);

    /**
     * 新增游戏社区
     * 
     * @param blueGameCommunity 游戏社区
     * @return 结果
     */
    public int insertBlueGameCommunity(BlueGameCommunity blueGameCommunity);

    /**
     * 修改游戏社区
     * 
     * @param blueGameCommunity 游戏社区
     * @return 结果
     */
    public int updateBlueGameCommunity(BlueGameCommunity blueGameCommunity);

    /**
     * 批量删除游戏社区
     * 
     * @param ids 需要删除的游戏社区主键集合
     * @return 结果
     */
    public int deleteBlueGameCommunityByIds(Long[] ids);

    /**
     * 删除游戏社区信息
     * 
     * @param id 游戏社区主键
     * @return 结果
     */
    public int deleteBlueGameCommunityById(Long id);
}
