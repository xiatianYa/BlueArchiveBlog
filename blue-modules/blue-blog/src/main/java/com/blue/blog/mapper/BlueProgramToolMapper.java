package com.blue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.blog.entry.dao.BlueProgramTool;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 编程工具Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
@Mapper
public interface BlueProgramToolMapper extends BaseMapper<BlueProgramTool>
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
     * 删除编程工具
     * 
     * @param id 编程工具主键
     * @return 结果
     */
    public int deleteBlueProgramToolById(Long id);

    /**
     * 批量删除编程工具
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueProgramToolByIds(Long[] ids);
}
