package com.blue.game.service.impl;

import java.util.List;
import com.blue.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.game.mapper.BlueGameModeMapper;
import com.blue.game.domain.BlueGameMode;
import com.blue.game.service.IBlueGameModeService;

import javax.annotation.Resource;

/**
 * 游戏模式Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
@Service
public class BlueGameModeServiceImpl implements IBlueGameModeService 
{
    @Resource
    private BlueGameModeMapper blueGameModeMapper;

    /**
     * 查询游戏模式
     * 
     * @param id 游戏模式主键
     * @return 游戏模式
     */
    @Override
    public BlueGameMode selectBlueGameModeById(Long id)
    {
        return blueGameModeMapper.selectBlueGameModeById(id);
    }

    /**
     * 查询游戏模式列表
     * 
     * @param blueGameMode 游戏模式
     * @return 游戏模式
     */
    @Override
    public List<BlueGameMode> selectBlueGameModeList(BlueGameMode blueGameMode)
    {
        return blueGameModeMapper.selectBlueGameModeList(blueGameMode);
    }

    /**
     * 新增游戏模式
     * 
     * @param blueGameMode 游戏模式
     * @return 结果
     */
    @Override
    public int insertBlueGameMode(BlueGameMode blueGameMode)
    {
        return blueGameModeMapper.insertBlueGameMode(blueGameMode);
    }

    /**
     * 修改游戏模式
     * 
     * @param blueGameMode 游戏模式
     * @return 结果
     */
    @Override
    public int updateBlueGameMode(BlueGameMode blueGameMode)
    {
        blueGameMode.setUpdateTime(DateUtils.getNowDate());
        return blueGameModeMapper.updateBlueGameMode(blueGameMode);
    }

    /**
     * 批量删除游戏模式
     * 
     * @param ids 需要删除的游戏模式主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameModeByIds(Long[] ids)
    {
        return blueGameModeMapper.deleteBlueGameModeByIds(ids);
    }

    /**
     * 删除游戏模式信息
     * 
     * @param id 游戏模式主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameModeById(Long id)
    {
        return blueGameModeMapper.deleteBlueGameModeById(id);
    }
}
