package com.blue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.blog.entry.dao.BlueNotice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 公告信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@Mapper
public interface BlueNoticeMapper extends BaseMapper<BlueNotice>
{
    /**
     * 查询公告信息
     * 
     * @param id 公告信息主键
     * @return 公告信息
     */
    public BlueNotice selectBlueNoticeById(Long id);

    /**
     * 查询公告信息列表
     * 
     * @param blueNotice 公告信息
     * @return 公告信息集合
     */
    public List<BlueNotice> selectBlueNoticeList(BlueNotice blueNotice);

    /**
     * 新增公告信息
     * 
     * @param blueNotice 公告信息
     * @return 结果
     */
    public int insertBlueNotice(BlueNotice blueNotice);

    /**
     * 修改公告信息
     * 
     * @param blueNotice 公告信息
     * @return 结果
     */
    public int updateBlueNotice(BlueNotice blueNotice);

    /**
     * 删除公告信息
     * 
     * @param id 公告信息主键
     * @return 结果
     */
    public int deleteBlueNoticeById(Long id);

    /**
     * 批量删除公告信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueNoticeByIds(Long[] ids);
}
