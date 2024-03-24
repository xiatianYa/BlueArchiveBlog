package com.blue.sort.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.sort.domain.BlueSortTag;

import java.util.List;

/**
 * 标签Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public interface BlueSortTagMapper extends BaseMapper<BlueSortTag>
{
    /**
     * 查询标签
     * 
     * @param id 标签主键
     * @return 标签
     */
    public BlueSortTag selectBlueSortTagById(Long id);

    /**
     * 查询标签列表
     * 
     * @param blueSortTag 标签
     * @return 标签集合
     */
    public List<BlueSortTag> selectBlueSortTagList(BlueSortTag blueSortTag);

    /**
     * 新增标签
     * 
     * @param blueSortTag 标签
     * @return 结果
     */
    public int insertBlueSortTag(BlueSortTag blueSortTag);

    /**
     * 修改标签
     * 
     * @param blueSortTag 标签
     * @return 结果
     */
    public int updateBlueSortTag(BlueSortTag blueSortTag);

    /**
     * 删除标签
     * 
     * @param id 标签主键
     * @return 结果
     */
    public int deleteBlueSortTagById(Long id);

    /**
     * 批量删除标签
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueSortTagByIds(Long[] ids);
}
