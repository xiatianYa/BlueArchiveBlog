package com.blue.sort.service;

import java.util.List;

import com.blue.sort.domain.BlueSort;

/**
 * 分类Service接口
 *
 * @author ruoyi
 * @date 2024-03-17
 */
public interface IBlueSortService {
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
     * 批量删除分类
     *
     * @param ids 需要删除的分类主键集合
     * @return 结果
     */
    public int deleteBlueSortByIds(Long[] ids);

    /**
     * 删除分类信息
     *
     * @param id 分类主键
     * @return 结果
     */
    public int deleteBlueSortById(Long id);
}
