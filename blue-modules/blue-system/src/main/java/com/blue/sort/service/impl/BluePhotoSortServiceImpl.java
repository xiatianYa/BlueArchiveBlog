package com.blue.sort.service.impl;

import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BluePhotoSort;
import com.blue.sort.mapper.BluePhotoSortMapper;
import com.blue.sort.service.IBluePhotoSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 相册分类信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Service
public class BluePhotoSortServiceImpl implements IBluePhotoSortService 
{
    @Autowired
    private BluePhotoSortMapper bluePhotoSortMapper;

    /**
     * 查询相册分类信息
     * 
     * @param id 相册分类信息主键
     * @return 相册分类信息
     */
    @Override
    public BluePhotoSort selectBluePhotoSortById(Long id)
    {
        return bluePhotoSortMapper.selectBluePhotoSortById(id);
    }

    /**
     * 查询相册分类信息列表
     * 
     * @param bluePhotoSort 相册分类信息
     * @return 相册分类信息
     */
    @Override
    public List<BluePhotoSort> selectBluePhotoSortList(BluePhotoSort bluePhotoSort)
    {
        return bluePhotoSortMapper.selectBluePhotoSortList(bluePhotoSort);
    }

    /**
     * 新增相册分类信息
     * 
     * @param bluePhotoSort 相册分类信息
     * @return 结果
     */
    @Override
    public int insertBluePhotoSort(BluePhotoSort bluePhotoSort)
    {
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            bluePhotoSort.setCreateBy(userId.toString());
        }
        bluePhotoSort.setCreateTime(DateUtils.getNowDate());
        return bluePhotoSortMapper.insertBluePhotoSort(bluePhotoSort);
    }

    /**
     * 修改相册分类信息
     * 
     * @param bluePhotoSort 相册分类信息
     * @return 结果
     */
    @Override
    public int updateBluePhotoSort(BluePhotoSort bluePhotoSort)
    {
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            bluePhotoSort.setUpdateBy(userId.toString());
        }
        bluePhotoSort.setUpdateTime(DateUtils.getNowDate());
        return bluePhotoSortMapper.updateBluePhotoSort(bluePhotoSort);
    }

    /**
     * 批量删除相册分类信息
     * 
     * @param ids 需要删除的相册分类信息主键
     * @return 结果
     */
    @Override
    public int deleteBluePhotoSortByIds(Long[] ids)
    {
        return bluePhotoSortMapper.deleteBluePhotoSortByIds(ids);
    }

    /**
     * 删除相册分类信息信息
     * 
     * @param id 相册分类信息主键
     * @return 结果
     */
    @Override
    public int deleteBluePhotoSortById(Long id)
    {
        return bluePhotoSortMapper.deleteBluePhotoSortById(id);
    }
}
