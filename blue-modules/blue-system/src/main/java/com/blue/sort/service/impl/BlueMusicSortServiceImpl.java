package com.blue.sort.service.impl;

import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BlueMusicSort;
import com.blue.sort.mapper.BlueMusicSortMapper;
import com.blue.sort.service.IBlueMusicSortService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 音乐分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Service
public class BlueMusicSortServiceImpl implements IBlueMusicSortService 
{
    @Resource
    private BlueMusicSortMapper blueMusicSortMapper;

    /**
     * 查询音乐分类
     * 
     * @param id 音乐分类主键
     * @return 音乐分类
     */
    @Override
    public BlueMusicSort selectBlueMusicSortById(Long id)
    {
        return blueMusicSortMapper.selectBlueMusicSortById(id);
    }

    /**
     * 查询音乐分类列表
     * 
     * @param blueMusicSort 音乐分类
     * @return 音乐分类
     */
    @Override
    public List<BlueMusicSort> selectBlueMusicSortList(BlueMusicSort blueMusicSort)
    {
        return blueMusicSortMapper.selectBlueMusicSortList(blueMusicSort);
    }

    /**
     * 新增音乐分类
     * 
     * @param blueMusicSort 音乐分类
     * @return 结果
     */
    @Override
    public int insertBlueMusicSort(BlueMusicSort blueMusicSort)
    {
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            blueMusicSort.setCreateBy(userId.toString());
        }
        blueMusicSort.setCreateTime(DateUtils.getNowDate());
        return blueMusicSortMapper.insertBlueMusicSort(blueMusicSort);
    }

    /**
     * 修改音乐分类
     * 
     * @param blueMusicSort 音乐分类
     * @return 结果
     */
    @Override
    public int updateBlueMusicSort(BlueMusicSort blueMusicSort)
    {
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            blueMusicSort.setUpdateBy(userId.toString());
        }
        blueMusicSort.setUpdateTime(DateUtils.getNowDate());
        return blueMusicSortMapper.updateBlueMusicSort(blueMusicSort);
    }

    /**
     * 批量删除音乐分类
     * 
     * @param ids 需要删除的音乐分类主键
     * @return 结果
     */
    @Override
    public int deleteBlueMusicSortByIds(Long[] ids)
    {
        return blueMusicSortMapper.deleteBlueMusicSortByIds(ids);
    }

    /**
     * 删除音乐分类信息
     * 
     * @param id 音乐分类主键
     * @return 结果
     */
    @Override
    public int deleteBlueMusicSortById(Long id)
    {
        return blueMusicSortMapper.deleteBlueMusicSortById(id);
    }
}
