package com.blue.blog.service.impl;

import com.blue.blog.domain.BlueMusic;
import com.blue.blog.domain.dto.BlueMusicListBySort;
import com.blue.blog.mapper.BlueMusicMapper;
import com.blue.blog.service.IBlueMusicService;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BlueMusicSort;
import com.blue.sort.mapper.BlueMusicSortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private BlueMusicSortMapper blueMusicSortMapper;

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

    @Override
    public List<BlueMusicListBySort> getMusicListBySort(Long type) {
        //获取全部音乐分类
        List<BlueMusicSort> blueMusicSorts = blueMusicSortMapper.selectBlueMusicSortList(new BlueMusicSort());
        //获取全部音乐列表
        List<BlueMusic> blueMusics = blueMusicMapper.selectBlueMusicList(new BlueMusic());
        //返回列表
        List<BlueMusicListBySort> musicListBySorts=new ArrayList<>();
        for (BlueMusicSort blueMusicSort : blueMusicSorts) {
            BlueMusicListBySort musicByType = getMusicByType(blueMusics,blueMusicSort.getSortName(),blueMusicSort.getId());
            musicListBySorts.add(musicByType);
        }
        return musicListBySorts;
    }
    //根据音乐类型 获取列表
    public BlueMusicListBySort getMusicByType(List<BlueMusic> blueMusics,String sortName,Long type){
        BlueMusicListBySort blueMusicListBySort = new BlueMusicListBySort();
        blueMusicListBySort.setMusicList(new ArrayList<>());
        List<BlueMusic> musicList = blueMusicListBySort.getMusicList();
        blueMusicListBySort.setSortName(sortName);
        for (BlueMusic blueMusic : blueMusics) {
            if (StringUtils.isNotNull(type)){
                //匹配 则添加
                if (blueMusic.getSortId().equals(type)){
                    musicList.add(blueMusic);
                }
            }else {
                //type为空 则全部查询
                musicList.add(blueMusic);
            }
        }
        return blueMusicListBySort;
    }
}
