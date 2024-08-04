package com.blue.game.service;

import java.util.List;
import com.blue.game.domain.BlueGameMode;

/**
 * 游戏模式Service接口
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
public interface IBlueGameModeService 
{
    /**
     * 查询游戏模式
     * 
     * @param id 游戏模式主键
     * @return 游戏模式
     */
    public BlueGameMode selectBlueGameModeById(Long id);

    /**
     * 查询游戏模式列表
     * 
     * @param blueGameMode 游戏模式
     * @return 游戏模式集合
     */
    public List<BlueGameMode> selectBlueGameModeList(BlueGameMode blueGameMode);

    /**
     * 新增游戏模式
     * 
     * @param blueGameMode 游戏模式
     * @return 结果
     */
    public int insertBlueGameMode(BlueGameMode blueGameMode);

    /**
     * 修改游戏模式
     * 
     * @param blueGameMode 游戏模式
     * @return 结果
     */
    public int updateBlueGameMode(BlueGameMode blueGameMode);

    /**
     * 批量删除游戏模式
     * 
     * @param ids 需要删除的游戏模式主键集合
     * @return 结果
     */
    public int deleteBlueGameModeByIds(Long[] ids);

    /**
     * 删除游戏模式信息
     * 
     * @param id 游戏模式主键
     * @return 结果
     */
    public int deleteBlueGameModeById(Long id);
}
