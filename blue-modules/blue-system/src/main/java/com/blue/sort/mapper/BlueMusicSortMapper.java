package com.blue.sort.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.sort.domain.BlueMusicSort;

import java.util.List;

/**
 * 音乐分类Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public interface BlueMusicSortMapper extends BaseMapper<BlueMusicSort>
{
    /**
     * 查询音乐分类
     * 
     * @param id 音乐分类主键
     * @return 音乐分类
     */
    public BlueMusicSort selectBlueMusicSortById(Long id);

    /**
     * 查询音乐分类列表
     * 
     * @param blueMusicSort 音乐分类
     * @return 音乐分类集合
     */
    public List<BlueMusicSort> selectBlueMusicSortList(BlueMusicSort blueMusicSort);

    /**
     * 新增音乐分类
     * 
     * @param blueMusicSort 音乐分类
     * @return 结果
     */
    public int insertBlueMusicSort(BlueMusicSort blueMusicSort);

    /**
     * 修改音乐分类
     * 
     * @param blueMusicSort 音乐分类
     * @return 结果
     */
    public int updateBlueMusicSort(BlueMusicSort blueMusicSort);

    /**
     * 删除音乐分类
     * 
     * @param id 音乐分类主键
     * @return 结果
     */
    public int deleteBlueMusicSortById(Long id);

    /**
     * 批量删除音乐分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueMusicSortByIds(Long[] ids);
}
