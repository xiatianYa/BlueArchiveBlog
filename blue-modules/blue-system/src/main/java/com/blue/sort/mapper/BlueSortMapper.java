package com.blue.sort.mapper;

import java.util.List;

import com.blue.sort.domain.BlueSort;
import com.blue.sort.domain.BlueSortTag;

/**
 * 分类Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-17
 */
public interface BlueSortMapper {
    /**
     * 查询分类
     *
     * @param id 分类主键
     * @return 分类
     */
    public BlueSort selectBlueSortById(Long id);

    /**
     * 查询分类列表
     *
     * @param blueSort 分类
     * @return 分类集合
     */
    public List<BlueSort> selectBlueSortList(BlueSort blueSort);

    /**
     * 新增分类
     *
     * @param blueSort 分类
     * @return 结果
     */
    public int insertBlueSort(BlueSort blueSort);

    /**
     * 修改分类
     *
     * @param blueSort 分类
     * @return 结果
     */
    public int updateBlueSort(BlueSort blueSort);

    /**
     * 删除分类
     *
     * @param id 分类主键
     * @return 结果
     */
    public int deleteBlueSortById(Long id);

    /**
     * 批量删除分类
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueSortByIds(Long[] ids);

    /**
     * 批量删除标签
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueSortTagBySortIds(Long[] ids);

    /**
     * 批量新增标签
     *
     * @param blueSortTagList 标签列表
     * @return 结果
     */
    public int batchBlueSortTag(List<BlueSortTag> blueSortTagList);


    /**
     * 通过分类主键删除标签信息
     *
     * @param id 分类ID
     * @return 结果
     */
    public int deleteBlueSortTagBySortId(Long id);
}
