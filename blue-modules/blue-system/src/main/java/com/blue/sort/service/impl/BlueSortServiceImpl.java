package com.blue.sort.service.impl;

import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BlueSort;
import com.blue.sort.mapper.BlueSortMapper;
import com.blue.sort.service.IBlueSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Service
public class BlueSortServiceImpl implements IBlueSortService 
{
    @Autowired
    private BlueSortMapper blueSortMapper;

    /**
     * 查询分类
     * 
     * @param id 分类主键
     * @return 分类
     */
    @Override
    public BlueSort selectBlueSortById(Long id)
    {
        return blueSortMapper.selectBlueSortById(id);
    }

    /**
     * 查询分类列表
     * 
     * @param blueSort 分类
     * @return 分类
     */
    @Override
    public List<BlueSort> selectBlueSortList(BlueSort blueSort)
    {
        return blueSortMapper.selectBlueSortList(blueSort);
    }

    /**
     * 新增分类
     * 
     * @param blueSort 分类
     * @return 结果
     */
    @Override
    public int insertBlueSort(BlueSort blueSort)
    {
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            blueSort.setCreateBy(userId.toString());
        }
        blueSort.setCreateTime(DateUtils.getNowDate());
        return blueSortMapper.insertBlueSort(blueSort);
    }

    /**
     * 修改分类
     * 
     * @param blueSort 分类
     * @return 结果
     */
    @Override
    public int updateBlueSort(BlueSort blueSort)
    {
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            blueSort.setUpdateBy(userId.toString());
        }
        blueSort.setUpdateTime(DateUtils.getNowDate());
        return blueSortMapper.updateBlueSort(blueSort);
    }

    /**
     * 批量删除分类
     * 
     * @param ids 需要删除的分类主键
     * @return 结果
     */
    @Override
    public int deleteBlueSortByIds(Long[] ids)
    {
        return blueSortMapper.deleteBlueSortByIds(ids);
    }

    /**
     * 删除分类信息
     * 
     * @param id 分类主键
     * @return 结果
     */
    @Override
    public int deleteBlueSortById(Long id)
    {
        return blueSortMapper.deleteBlueSortById(id);
    }
}
