package com.blue.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.job.domain.BlueArticle;
import com.blue.job.domain.BlueGameStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据统计Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-01
 */
@Mapper
public interface BlueGameStatisticsMapper extends BaseMapper<BlueGameStatistics>
{
    /**
     * 查询数据统计
     * 
     * @param id 数据统计主键
     * @return 数据统计
     */
    public BlueGameStatistics selectBlueGameStatisticsById(Long id);

    /**
     * 查询数据统计列表
     * 
     * @param blueGameStatistics 数据统计
     * @return 数据统计集合
     */
    public List<BlueGameStatistics> selectBlueGameStatisticsList(BlueGameStatistics blueGameStatistics);

    /**
     * 新增数据统计
     * 
     * @param blueGameStatistics 数据统计
     * @return 结果
     */
    public int insertBlueGameStatistics(BlueGameStatistics blueGameStatistics);

    /**
     * 修改数据统计
     * 
     * @param blueGameStatistics 数据统计
     * @return 结果
     */
    public int updateBlueGameStatistics(BlueGameStatistics blueGameStatistics);

    /**
     * 删除数据统计
     * 
     * @param id 数据统计主键
     * @return 结果
     */
    public int deleteBlueGameStatisticsById(Long id);

    /**
     * 批量删除数据统计
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueGameStatisticsByIds(Long[] ids);
}
