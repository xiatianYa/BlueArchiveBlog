package com.blue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.blog.domain.BluePixivTv;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 番剧信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-01
 */
@Mapper
public interface BluePixivTvMapper extends BaseMapper<BluePixivTv>
{
    /**
     * 查询番剧信息
     * 
     * @param id 番剧信息主键
     * @return 番剧信息
     */
    public BluePixivTv selectBluePixivTvById(Long id);

    /**
     * 查询番剧信息列表
     * 
     * @param bluePixivTv 番剧信息
     * @return 番剧信息集合
     */
    public List<BluePixivTv> selectBluePixivTvList(BluePixivTv bluePixivTv);

    /**
     * 新增番剧信息
     * 
     * @param bluePixivTv 番剧信息
     * @return 结果
     */
    public int insertBluePixivTv(BluePixivTv bluePixivTv);

    /**
     * 修改番剧信息
     * 
     * @param bluePixivTv 番剧信息
     * @return 结果
     */
    public int updateBluePixivTv(BluePixivTv bluePixivTv);

    /**
     * 删除番剧信息
     * 
     * @param id 番剧信息主键
     * @return 结果
     */
    public int deleteBluePixivTvById(Long id);

    /**
     * 批量删除番剧信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBluePixivTvByIds(Long[] ids);
}
