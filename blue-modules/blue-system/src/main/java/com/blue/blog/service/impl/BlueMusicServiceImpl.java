package com.blue.blog.service.impl;

import com.blue.blog.domain.BlueMusic;
import com.blue.blog.mapper.BlueMusicMapper;
import com.blue.blog.service.IBlueMusicService;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 音乐Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Service
public class BlueMusicServiceImpl implements IBlueMusicService 
{
    @Autowired
    private BlueMusicMapper blueMusicMapper;

    /**
     * 查询音乐
     * 
     * @param id 音乐主键
     * @return 音乐
     */
    @Override
    public BlueMusic selectBlueMusicById(Long id)
    {
        return blueMusicMapper.selectBlueMusicById(id);
    }

    /**
     * 查询音乐列表
     * 
     * @param blueMusic 音乐
     * @return 音乐
     */
    @Override
    public List<BlueMusic> selectBlueMusicList(BlueMusic blueMusic)
    {
        return blueMusicMapper.selectBlueMusicList(blueMusic);
    }

    /**
     * 新增音乐
     * 
     * @param blueMusic 音乐
     * @return 结果
     */
    @Override
    public int insertBlueMusic(BlueMusic blueMusic)
    {
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            blueMusic.setCreateBy(userId.toString());
        }
        blueMusic.setCreateTime(DateUtils.getNowDate());
        return blueMusicMapper.insertBlueMusic(blueMusic);
    }

    /**
     * 修改音乐
     * 
     * @param blueMusic 音乐
     * @return 结果
     */
    @Override
    public int updateBlueMusic(BlueMusic blueMusic)
    {
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            blueMusic.setUpdateBy(userId.toString());
        }
        blueMusic.setUpdateTime(DateUtils.getNowDate());
        return blueMusicMapper.updateBlueMusic(blueMusic);
    }

    /**
     * 批量删除音乐
     * 
     * @param ids 需要删除的音乐主键
     * @return 结果
     */
    @Override
    public int deleteBlueMusicByIds(Long[] ids)
    {
        return blueMusicMapper.deleteBlueMusicByIds(ids);
    }

    /**
     * 删除音乐信息
     * 
     * @param id 音乐主键
     * @return 结果
     */
    @Override
    public int deleteBlueMusicById(Long id)
    {
        return blueMusicMapper.deleteBlueMusicById(id);
    }
}
