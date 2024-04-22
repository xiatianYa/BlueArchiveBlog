package com.blue.sort.service.impl;

import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BlueProgramToolSort;
import com.blue.sort.mapper.BlueProgramToolSortMapper;
import com.blue.sort.service.IBlueProgramToolSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 编程工具分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
@Service
public class BlueProgramToolSortServiceImpl implements IBlueProgramToolSortService 
{
    @Resource
    private BlueProgramToolSortMapper blueProgramToolSortMapper;

    /**
     * 查询编程工具分类
     * 
     * @param id 编程工具分类主键
     * @return 编程工具分类
     */
    @Override
    public BlueProgramToolSort selectBlueProgramToolSortById(Long id)
    {
        return blueProgramToolSortMapper.selectBlueProgramToolSortById(id);
    }

    /**
     * 查询编程工具分类列表
     * 
     * @param blueProgramToolSort 编程工具分类
     * @return 编程工具分类
     */
    @Override
    public List<BlueProgramToolSort> selectBlueProgramToolSortList(BlueProgramToolSort blueProgramToolSort)
    {
        return blueProgramToolSortMapper.selectBlueProgramToolSortList(blueProgramToolSort);
    }

    /**
     * 新增编程工具分类
     * 
     * @param blueProgramToolSort 编程工具分类
     * @return 结果
     */
    @Override
    public int insertBlueProgramToolSort(BlueProgramToolSort blueProgramToolSort)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueProgramToolSort.setCreateBy(String.valueOf(userId));
        }
        blueProgramToolSort.setCreateTime(DateUtils.getNowDate());
        return blueProgramToolSortMapper.insertBlueProgramToolSort(blueProgramToolSort);
    }

    /**
     * 修改编程工具分类
     * 
     * @param blueProgramToolSort 编程工具分类
     * @return 结果
     */
    @Override
    public int updateBlueProgramToolSort(BlueProgramToolSort blueProgramToolSort)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueProgramToolSort.setUpdateBy(String.valueOf(userId));
        }
        blueProgramToolSort.setUpdateTime(DateUtils.getNowDate());
        return blueProgramToolSortMapper.updateBlueProgramToolSort(blueProgramToolSort);
    }

    /**
     * 批量删除编程工具分类
     * 
     * @param ids 需要删除的编程工具分类主键
     * @return 结果
     */
    @Override
    public int deleteBlueProgramToolSortByIds(Long[] ids)
    {
        return blueProgramToolSortMapper.deleteBlueProgramToolSortByIds(ids);
    }

    /**
     * 删除编程工具分类信息
     * 
     * @param id 编程工具分类主键
     * @return 结果
     */
    @Override
    public int deleteBlueProgramToolSortById(Long id)
    {
        return blueProgramToolSortMapper.deleteBlueProgramToolSortById(id);
    }
}
