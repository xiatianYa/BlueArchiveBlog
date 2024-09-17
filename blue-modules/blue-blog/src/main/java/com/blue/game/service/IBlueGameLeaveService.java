package com.blue.game.service;

import java.util.List;
import com.blue.game.domain.BlueGameLeave;
import com.blue.game.domain.dto.BlueGameLeaveResDto;

/**
 * 游戏留言Service接口
 * 
 * @author ruoyi
 * @date 2024-09-17
 */
public interface IBlueGameLeaveService 
{
    /**
     * 查询游戏留言
     * 
     * @param id 游戏留言主键
     * @return 游戏留言
     */
    public BlueGameLeave selectBlueGameLeaveById(Long id);

    /**
     * 查询游戏留言列表
     * 
     * @param blueGameLeave 游戏留言
     * @return 游戏留言集合
     */
    public List<BlueGameLeave> selectBlueGameLeaveList(BlueGameLeave blueGameLeave);

    /**
     * 新增游戏留言
     * 
     * @param blueGameLeave 游戏留言
     * @return 结果
     */
    public int insertBlueGameLeave(BlueGameLeave blueGameLeave);

    /**
     * 修改游戏留言
     * 
     * @param blueGameLeave 游戏留言
     * @return 结果
     */
    public int updateBlueGameLeave(BlueGameLeave blueGameLeave);

    /**
     * 批量删除游戏留言
     * 
     * @param ids 需要删除的游戏留言主键集合
     * @return 结果
     */
    public int deleteBlueGameLeaveByIds(Long[] ids);

    /**
     * 删除游戏留言信息
     * 
     * @param id 游戏留言主键
     * @return 结果
     */
    public int deleteBlueGameLeaveById(Long id);

    List<BlueGameLeaveResDto> selectBlueGameLeaveListV2(BlueGameLeave blueGameLeave);
}
