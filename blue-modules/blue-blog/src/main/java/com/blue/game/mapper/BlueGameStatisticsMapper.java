package com.blue.game.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.game.domain.BlueGameStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 数据统计Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-01
 */
@Mapper
public interface BlueGameStatisticsMapper  extends BaseMapper<BlueGameStatistics>
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

    @Select("SELECT community_id,MAX(community_play) AS communityPlay,DATE_FORMAT(time, '%Y-%m-%d %H:%i') AS timeMinute FROM blue_game_statistics WHERE time >= NOW() - INTERVAL 1 DAY GROUP BY community_id,DATE_FORMAT(time, '%Y-%m-%d %H:%i') ORDER BY community_id,timeMinute")
    List<BlueGameStatistics> selectBlueGameStatisticsListAll();
}
