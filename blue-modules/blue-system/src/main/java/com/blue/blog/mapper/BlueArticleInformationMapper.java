package com.blue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.blog.entry.dao.BlueArticleInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-07
 */
@Mapper
public interface BlueArticleInformationMapper extends BaseMapper<BlueArticleInformation>
{
    /**
     * 查询文章信息
     * 
     * @param id 文章信息主键
     * @return 文章信息
     */
    public BlueArticleInformation selectBlueArticleInformationById(Long id);

    /**
     * 查询文章信息列表
     * 
     * @param blueArticleInformation 文章信息
     * @return 文章信息集合
     */
    public List<BlueArticleInformation> selectBlueArticleInformationList(BlueArticleInformation blueArticleInformation);

    /**
     * 新增文章信息
     * 
     * @param blueArticleInformation 文章信息
     * @return 结果
     */
    public int insertBlueArticleInformation(BlueArticleInformation blueArticleInformation);

    /**
     * 修改文章信息
     * 
     * @param blueArticleInformation 文章信息
     * @return 结果
     */
    public int updateBlueArticleInformation(BlueArticleInformation blueArticleInformation);

    /**
     * 删除文章信息
     * 
     * @param id 文章信息主键
     * @return 结果
     */
    public int deleteBlueArticleInformationById(Long id);

    /**
     * 批量删除文章信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueArticleInformationByIds(Long[] ids);
}
