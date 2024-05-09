package com.blue.sort.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.sort.domain.BlueArticleTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章标签关联Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@Mapper
public interface BlueArticleTagMapper extends BaseMapper<BlueArticleTag>
{
    /**
     * 查询文章标签关联
     * 
     * @param id 文章标签关联主键
     * @return 文章标签关联
     */
    public BlueArticleTag selectBlueArticleTagById(Long id);

    /**
     * 查询文章标签关联列表
     * 
     * @param blueArticleTag 文章标签关联
     * @return 文章标签关联集合
     */
    public List<BlueArticleTag> selectBlueArticleTagList(BlueArticleTag blueArticleTag);

    /**
     * 新增文章标签关联
     * 
     * @param blueArticleTag 文章标签关联
     * @return 结果
     */
    public int insertBlueArticleTag(BlueArticleTag blueArticleTag);

    /**
     * 修改文章标签关联
     * 
     * @param blueArticleTag 文章标签关联
     * @return 结果
     */
    public int updateBlueArticleTag(BlueArticleTag blueArticleTag);

    /**
     * 删除文章标签关联
     * 
     * @param id 文章标签关联主键
     * @return 结果
     */
    public int deleteBlueArticleTagById(Long id);

    /**
     * 批量删除文章标签关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueArticleTagByIds(Long[] ids);
}
