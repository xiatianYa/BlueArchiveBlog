package com.blue.blog.service;

import com.blue.blog.domain.BlueMusic;
import com.blue.blog.domain.dto.BlueMusicListBySort;

import java.util.List;

/**
 * 音乐Service接口
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public interface IBlueMusicService 
{
    /**
     * 查询音乐
     * 
     * @param id 音乐主键
     * @return 音乐
     */
    public BlueMusic selectBlueMusicById(Long id);

    /**
     * 查询音乐列表
     * 
     * @param blueMusic 音乐
     * @return 音乐集合
     */
    public List<BlueMusic> selectBlueMusicList(BlueMusic blueMusic);

    /**
     * 新增音乐
     * 
     * @param blueMusic 音乐
     * @return 结果
     */
    public int insertBlueMusic(BlueMusic blueMusic);

    /**
     * 修改音乐
     * 
     * @param blueMusic 音乐
     * @return 结果
     */
    public int updateBlueMusic(BlueMusic blueMusic);

    /**
     * 批量删除音乐
     * 
     * @param ids 需要删除的音乐主键集合
     * @return 结果
     */
    public int deleteBlueMusicByIds(Long[] ids);

    /**
     * 删除音乐信息
     * 
     * @param id 音乐主键
     * @return 结果
     */
    public int deleteBlueMusicById(Long id);

    List<BlueMusicListBySort> getMusicListBySort(Long type);
}
