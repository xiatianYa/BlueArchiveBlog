package com.blue.blog.service;

import com.blue.blog.entry.dao.BlueProgramTool;
import com.blue.blog.entry.dto.BlueProgramToolBySortDTO;

import java.util.List;

/**
 * 编程工具Service接口
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
public interface IBlueProgramToolService 
{
    /**
     * 查询编程工具
     * 
     * @param id 编程工具主键
     * @return 编程工具
     */
    public BlueProgramTool selectBlueProgramToolById(Long id);

    /**
     * 查询编程工具列表
     * 
     * @param blueProgramTool 编程工具
     * @return 编程工具集合
     */
    public List<BlueProgramTool> selectBlueProgramToolList(BlueProgramTool blueProgramTool);

    /**
     * 新增编程工具
     * 
     * @param blueProgramTool 编程工具
     * @return 结果
     */
    public int insertBlueProgramTool(BlueProgramTool blueProgramTool);

    /**
     * 修改编程工具
     * 
     * @param blueProgramTool 编程工具
     * @return 结果
     */
    public int updateBlueProgramTool(BlueProgramTool blueProgramTool);

    /**
     * 批量删除编程工具
     * 
     * @param ids 需要删除的编程工具主键集合
     * @return 结果
     */
    public int deleteBlueProgramToolByIds(Long[] ids);

    /**
     * 删除编程工具信息
     * 
     * @param id 编程工具主键
     * @return 结果
     */
    public int deleteBlueProgramToolById(Long id);

    List<BlueProgramToolBySortDTO> selectBlueProgramToolListBySortId(BlueProgramTool blueProgramTool);
}
