package com.blue.game.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.blue.common.core.utils.StringUtils;
import com.blue.game.domain.BlueGameCommunity;
import com.blue.game.domain.vo.PersonnelDataVo;
import com.blue.game.mapper.BlueGameCommunityMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.game.mapper.BlueGameStatisticsMapper;
import com.blue.game.domain.BlueGameStatistics;
import com.blue.game.service.IBlueGameStatisticsService;

import javax.annotation.Resource;

/**
 * 数据统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-01
 */
@Service
public class BlueGameStatisticsServiceImpl implements IBlueGameStatisticsService 
{
    @Resource
    private BlueGameStatisticsMapper blueGameStatisticsMapper;
    @Resource
    private BlueGameCommunityMapper blueGameCommunityMapper;

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

    @Override
    public PersonnelDataVo selectBlueGameStatisticsListAll() {
        //返回数据对象
        PersonnelDataVo personnelDataVo = PersonnelDataVo.builder()
                .communityNameList(new ArrayList<>())
                .xAxisData(new ArrayList<>())
                .yAxisData(new ArrayList<>())
                .build();
        //返回数据结果
        List<BlueGameStatistics> blueGameStatisticsList = blueGameStatisticsMapper.selectBlueGameStatisticsListAll();
        //获取所有的社区
        List<BlueGameCommunity> blueGameCommunities = blueGameCommunityMapper.selectBlueGameCommunityList(null);
        //封装数据
        for (BlueGameCommunity blueGameCommunity : blueGameCommunities) {
            //添加社区名称
            personnelDataVo.getCommunityNameList().add(blueGameCommunity.getName());
            //查询当前社区下的数据
            List<BlueGameStatistics> blueGameStatisticsListByCommunity = blueGameStatisticsList.stream()
                    .filter(item -> item.getCommunityId().equals(blueGameCommunity.getId()))
                    .collect(Collectors.toList());
            if (StringUtils.isNotEmpty(blueGameStatisticsListByCommunity)) {
                List<Integer> data = new ArrayList<>();
                for (BlueGameStatistics item : blueGameStatisticsListByCommunity) {
                    boolean contains = personnelDataVo.getXAxisData().contains(item.getTimeMinute());
                    if (!contains){
                        personnelDataVo.getXAxisData().add(item.getTimeMinute());
                    }
                    data.add(item.getCommunityPlay());
                }
                personnelDataVo.getYAxisData().add(data);
            }
        }
        return personnelDataVo;
    }
}
