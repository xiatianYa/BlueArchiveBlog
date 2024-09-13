package com.blue.game.service;

import java.util.List;
import com.blue.game.domain.BlueGameLive;
import com.blue.game.domain.vo.BiliUserDataVo;

/**
 * 游戏直播Service接口
 * 
 * @author ruoyi
 * @date 2024-09-11
 */
public interface IBlueGameLiveService 
{
    /**
     * 查询游戏直播
     * 
     * @param id 游戏直播主键
     * @return 游戏直播
     */
    public BlueGameLive selectBlueGameLiveById(Long id);

    /**
     * 查询游戏直播列表
     * 
     * @param blueGameLive 游戏直播
     * @return 游戏直播集合
     */
    public List<BiliUserDataVo> selectBlueGameLiveList(BlueGameLive blueGameLive);

    /**
     * 新增游戏直播
     * 
     * @param blueGameLive 游戏直播
     * @return 结果
     */
    public int insertBlueGameLive(BlueGameLive blueGameLive);

    /**
     * 修改游戏直播
     * 
     * @param blueGameLive 游戏直播
     * @return 结果
     */
    public int updateBlueGameLive(BlueGameLive blueGameLive);

    /**
     * 批量删除游戏直播
     * 
     * @param ids 需要删除的游戏直播主键集合
     * @return 结果
     */
    public int deleteBlueGameLiveByIds(Long[] ids);

    /**
     * 删除游戏直播信息
     * 
     * @param id 游戏直播主键
     * @return 结果
     */
    public int deleteBlueGameLiveById(Long id);
}
