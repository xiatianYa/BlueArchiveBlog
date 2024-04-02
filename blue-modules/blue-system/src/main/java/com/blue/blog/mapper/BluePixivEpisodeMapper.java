package com.blue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.blog.domain.BluePixivEpisode;

import java.util.List;

/**
 * 番剧集存储Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-01
 */
public interface BluePixivEpisodeMapper extends BaseMapper<BluePixivEpisode>
{
    /**
     * 查询番剧集存储
     * 
     * @param id 番剧集存储主键
     * @return 番剧集存储
     */
    public BluePixivEpisode selectBluePixivEpisodeById(Long id);

    /**
     * 查询番剧集存储列表
     * 
     * @param bluePixivEpisode 番剧集存储
     * @return 番剧集存储集合
     */
    public List<BluePixivEpisode> selectBluePixivEpisodeList(BluePixivEpisode bluePixivEpisode);

    /**
     * 新增番剧集存储
     * 
     * @param bluePixivEpisode 番剧集存储
     * @return 结果
     */
    public int insertBluePixivEpisode(BluePixivEpisode bluePixivEpisode);

    /**
     * 修改番剧集存储
     * 
     * @param bluePixivEpisode 番剧集存储
     * @return 结果
     */
    public int updateBluePixivEpisode(BluePixivEpisode bluePixivEpisode);

    /**
     * 删除番剧集存储
     * 
     * @param id 番剧集存储主键
     * @return 结果
     */
    public int deleteBluePixivEpisodeById(Long id);

    /**
     * 批量删除番剧集存储
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBluePixivEpisodeByIds(Long[] ids);
}
