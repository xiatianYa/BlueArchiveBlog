package com.blue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.blog.entry.dao.BlueWebsite;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 网站信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@Mapper
public interface BlueWebsiteMapper extends BaseMapper<BlueWebsite>
{
    /**
     * 查询网站信息
     * 
     * @param id 网站信息主键
     * @return 网站信息
     */
    public BlueWebsite selectBlueWebsiteById(Long id);

    /**
     * 查询网站信息列表
     * 
     * @param blueWebsite 网站信息
     * @return 网站信息集合
     */
    public List<BlueWebsite> selectBlueWebsiteList(BlueWebsite blueWebsite);

    /**
     * 新增网站信息
     * 
     * @param blueWebsite 网站信息
     * @return 结果
     */
    public int insertBlueWebsite(BlueWebsite blueWebsite);

    /**
     * 修改网站信息
     * 
     * @param blueWebsite 网站信息
     * @return 结果
     */
    public int updateBlueWebsite(BlueWebsite blueWebsite);

    /**
     * 删除网站信息
     * 
     * @param id 网站信息主键
     * @return 结果
     */
    public int deleteBlueWebsiteById(Long id);

    /**
     * 批量删除网站信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueWebsiteByIds(Long[] ids);
}
