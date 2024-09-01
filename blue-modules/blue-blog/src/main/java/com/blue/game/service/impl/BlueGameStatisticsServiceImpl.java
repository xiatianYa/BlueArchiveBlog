package com.blue.game.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.game.mapper.BlueGameStatisticsMapper;
import com.blue.game.domain.BlueGameStatistics;
import com.blue.game.service.IBlueGameStatisticsService;

/**
 * 数据统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-01
 */
@Service
public class BlueGameStatisticsServiceImpl implements IBlueGameStatisticsService 
{
    @Autowired
    private BlueGameStatisticsMapper blueGameStatisticsMapper;

    /**
     * 查询数据统计
     * 
     * @param id 数据统计主键
     * @return 数据统计
     */
    @Override
    public BlueGameStatistics selectBlueGameStatisticsById(Long id)
    {
        return blueGameStatisticsMapper.selectBlueGameStatisticsById(id);
    }

    /**
     * 查询数据统计列表
     * 
     * @param blueGameStatistics 数据统计
     * @return 数据统计
     */
    @Override
    public List<BlueGameStatistics> selectBlueGameStatisticsList(BlueGameStatistics blueGameStatistics)
    {
        return blueGameStatisticsMapper.selectBlueGameStatisticsList(blueGameStatistics);
    }

    /**
     * 新增数据统计
     * 
     * @param blueGameStatistics 数据统计
     * @return 结果
     */
    @Override
    public int insertBlueGameStatistics(BlueGameStatistics blueGameStatistics)
    {
        return blueGameStatisticsMapper.insertBlueGameStatistics(blueGameStatistics);
    }

    /**
     * 修改数据统计
     * 
     * @param blueGameStatistics 数据统计
     * @return 结果
     */
    @Override
    public int updateBlueGameStatistics(BlueGameStatistics blueGameStatistics)
    {
        return blueGameStatisticsMapper.updateBlueGameStatistics(blueGameStatistics);
    }

    /**
     * 批量删除数据统计
     * 
     * @param ids 需要删除的数据统计主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameStatisticsByIds(Long[] ids)
    {
        return blueGameStatisticsMapper.deleteBlueGameStatisticsByIds(ids);
    }

    /**
     * 删除数据统计信息
     * 
     * @param id 数据统计主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameStatisticsById(Long id)
    {
        return blueGameStatisticsMapper.deleteBlueGameStatisticsById(id);
    }
}
