package com.blue.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.entry.dao.BluePixivEpisode;
import com.blue.blog.mapper.BluePixivEpisodeMapper;
import com.blue.blog.service.IBluePixivEpisodeService;
import com.blue.common.core.exception.ServiceException;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 番剧集存储Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-01
 */
@Service
public class BluePixivEpisodeServiceImpl implements IBluePixivEpisodeService 
{
    @Resource
    private BluePixivEpisodeMapper bluePixivEpisodeMapper;

    /**
     * 查询番剧集存储
     * 
     * @param id 番剧集存储主键
     * @return 番剧集存储
     */
    @Override
    public BluePixivEpisode selectBluePixivEpisodeById(Long id)
    {
        return bluePixivEpisodeMapper.selectBluePixivEpisodeById(id);
    }

    /**
     * 查询番剧集存储列表
     * 
     * @param bluePixivEpisode 番剧集存储
     * @return 番剧集存储
     */
    @Override
    public List<BluePixivEpisode> selectBluePixivEpisodeList(BluePixivEpisode bluePixivEpisode)
    {
        return bluePixivEpisodeMapper.selectBluePixivEpisodeList(bluePixivEpisode);
    }

    /**
     * 新增番剧集存储
     * 
     * @param bluePixivEpisode 番剧集存储
     * @return 结果
     */
    @Override
    public int insertBluePixivEpisode(BluePixivEpisode bluePixivEpisode)
    {
        //查询有没有番剧集是一样的数据
        LambdaQueryWrapper<BluePixivEpisode> bluePixivEpisodeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        bluePixivEpisodeLambdaQueryWrapper.eq(BluePixivEpisode::getPixivId,bluePixivEpisode.getPixivId());
        bluePixivEpisodeLambdaQueryWrapper.eq(BluePixivEpisode::getPixivChapters,bluePixivEpisode.getPixivChapters());
        BluePixivEpisode selectOne = bluePixivEpisodeMapper.selectOne(bluePixivEpisodeLambdaQueryWrapper);
        if (StringUtils.isNotNull(selectOne)){
            throw new ServiceException("番剧集存储已存在");
        }
        return bluePixivEpisodeMapper.insertBluePixivEpisode(bluePixivEpisode);
    }

    /**
     * 修改番剧集存储
     * 
     * @param bluePixivEpisode 番剧集存储
     * @return 结果
     */
    @Override
    public int updateBluePixivEpisode(BluePixivEpisode bluePixivEpisode)
    {
        return bluePixivEpisodeMapper.updateBluePixivEpisode(bluePixivEpisode);
    }

    /**
     * 批量删除番剧集存储
     * 
     * @param ids 需要删除的番剧集存储主键
     * @return 结果
     */
    @Override
    public int deleteBluePixivEpisodeByIds(Long[] ids)
    {
        return bluePixivEpisodeMapper.deleteBluePixivEpisodeByIds(ids);
    }

    /**
     * 删除番剧集存储信息
     * 
     * @param id 番剧集存储主键
     * @return 结果
     */
    @Override
    public int deleteBluePixivEpisodeById(Long id)
    {
        return bluePixivEpisodeMapper.deleteBluePixivEpisodeById(id);
    }
}
