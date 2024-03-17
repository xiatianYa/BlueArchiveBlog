package com.blue.sort.service;

import java.util.List;

import com.blue.sort.domain.BlueMusicSort;

/**
 * 音乐分类Service接口
 *
 * @author ruoyi
 * @date 2024-03-17
 */
public interface IBlueMusicSortService {
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
     * 批量删除音乐分类
     *
     * @param ids 需要删除的音乐分类主键集合
     * @return 结果
     */
    public int deleteBlueMusicSortByIds(Long[] ids);

    /**
     * 删除音乐分类信息
     *
     * @param id 音乐分类主键
     * @return 结果
     */
    public int deleteBlueMusicSortById(Long id);
}
