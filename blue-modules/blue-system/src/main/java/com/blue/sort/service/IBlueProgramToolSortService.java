package com.blue.sort.service;

import com.blue.sort.domain.BlueProgramToolSort;

import java.util.List;

/**
 * 编程工具分类Service接口
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
public interface IBlueProgramToolSortService 
{
    /**
     * 查询编程工具分类
     * 
     * @param id 编程工具分类主键
     * @return 编程工具分类
     */
    public BlueProgramToolSort selectBlueProgramToolSortById(Long id);

    /**
     * 查询编程工具分类列表
     * 
     * @param blueProgramToolSort 编程工具分类
     * @return 编程工具分类集合
     */
    public List<BlueProgramToolSort> selectBlueProgramToolSortList(BlueProgramToolSort blueProgramToolSort);

    /**
     * 新增编程工具分类
     * 
     * @param blueProgramToolSort 编程工具分类
     * @return 结果
     */
    public int insertBlueProgramToolSort(BlueProgramToolSort blueProgramToolSort);

    /**
     * 修改编程工具分类
     * 
     * @param blueProgramToolSort 编程工具分类
     * @return 结果
     */
    public int updateBlueProgramToolSort(BlueProgramToolSort blueProgramToolSort);

    /**
     * 批量删除编程工具分类
     * 
     * @param ids 需要删除的编程工具分类主键集合
     * @return 结果
     */
    public int deleteBlueProgramToolSortByIds(Long[] ids);

    /**
     * 删除编程工具分类信息
     * 
     * @param id 编程工具分类主键
     * @return 结果
     */
    public int deleteBlueProgramToolSortById(Long id);
}
