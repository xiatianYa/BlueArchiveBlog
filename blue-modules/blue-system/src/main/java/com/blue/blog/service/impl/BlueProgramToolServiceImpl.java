package com.blue.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.domain.BlueProgramTool;
import com.blue.blog.mapper.BlueProgramToolMapper;
import com.blue.blog.service.IBlueProgramToolService;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BlueProgramToolSort;
import com.blue.sort.mapper.BlueProgramToolSortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 编程工具Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
@Service
public class BlueProgramToolServiceImpl implements IBlueProgramToolService 
{
    @Autowired
    private BlueProgramToolMapper blueProgramToolMapper;
    @Autowired
    private BlueProgramToolSortMapper blueProgramToolSortMapper;

    /**
     * 查询编程工具
     * 
     * @param id 编程工具主键
     * @return 编程工具
     */
    @Override
    public BlueProgramTool selectBlueProgramToolById(Long id)
    {
        return blueProgramToolMapper.selectBlueProgramToolById(id);
    }

    /**
     * 查询编程工具列表
     * 
     * @param blueProgramTool 编程工具
     * @return 编程工具
     */
    @Override
    public List<BlueProgramTool> selectBlueProgramToolList(BlueProgramTool blueProgramTool)
    {
        List<BlueProgramTool> blueProgramTools = blueProgramToolMapper.selectBlueProgramToolList(blueProgramTool);
        List<BlueProgramToolSort> blueProgramToolSorts = blueProgramToolSortMapper.selectList(new LambdaQueryWrapper<>());
        for (BlueProgramToolSort blueProgramToolSort : blueProgramToolSorts) {
            for (BlueProgramTool programTool : blueProgramTools) {
                if (programTool.getSortId().equals(blueProgramToolSort.getId())){
                    programTool.setSortName(blueProgramToolSort.getSortName());
                }
            }
        }
        return blueProgramTools;
    }

    /**
     * 新增编程工具
     * 
     * @param blueProgramTool 编程工具
     * @return 结果
     */
    @Override
    public int insertBlueProgramTool(BlueProgramTool blueProgramTool)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueProgramTool.setCreateBy(String.valueOf(userId));
        }
        blueProgramTool.setCreateTime(DateUtils.getNowDate());
        return blueProgramToolMapper.insertBlueProgramTool(blueProgramTool);
    }

    /**
     * 修改编程工具
     * 
     * @param blueProgramTool 编程工具
     * @return 结果
     */
    @Override
    public int updateBlueProgramTool(BlueProgramTool blueProgramTool)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueProgramTool.setUpdateBy(String.valueOf(userId));
        }
        blueProgramTool.setUpdateTime(DateUtils.getNowDate());
        return blueProgramToolMapper.updateBlueProgramTool(blueProgramTool);
    }

    /**
     * 批量删除编程工具
     * 
     * @param ids 需要删除的编程工具主键
     * @return 结果
     */
    @Override
    public int deleteBlueProgramToolByIds(Long[] ids)
    {
        return blueProgramToolMapper.deleteBlueProgramToolByIds(ids);
    }

    /**
     * 删除编程工具信息
     * 
     * @param id 编程工具主键
     * @return 结果
     */
    @Override
    public int deleteBlueProgramToolById(Long id)
    {
        return blueProgramToolMapper.deleteBlueProgramToolById(id);
    }
}
