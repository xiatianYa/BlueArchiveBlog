package com.blue.sort.service.impl;

import java.util.List;

import com.blue.blog.domain.BlueMusic;
import com.blue.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.blue.common.core.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.blue.sort.mapper.BlueMusicSortMapper;
import com.blue.sort.domain.BlueMusicSort;
import com.blue.sort.service.IBlueMusicSortService;

/**
 * 音乐分类Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-17
 */
@Service
public class BlueMusicSortServiceImpl implements IBlueMusicSortService {
    @Autowired
    private BlueMusicSortMapper blueMusicSortMapper;

    /**
     * 查询音乐分类
     *
     * @param id 音乐分类主键
     * @return 音乐分类
     */
    @Override
    public BlueMusicSort selectBlueMusicSortById(Long id) {
        return blueMusicSortMapper.selectBlueMusicSortById(id);
    }

    /**
     * 查询音乐分类列表
     *
     * @param blueMusicSort 音乐分类
     * @return 音乐分类
     */
    @Override
    public List<BlueMusicSort> selectBlueMusicSortList(BlueMusicSort blueMusicSort) {
        return blueMusicSortMapper.selectBlueMusicSortList(blueMusicSort);
    }

    /**
     * 新增音乐分类
     *
     * @param blueMusicSort 音乐分类
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBlueMusicSort(BlueMusicSort blueMusicSort) {
        blueMusicSort.setCreateTime(DateUtils.getNowDate());
        blueMusicSort.setUpdateTime(DateUtils.getNowDate());
        int rows = blueMusicSortMapper.insertBlueMusicSort(blueMusicSort);
        insertBlueMusic(blueMusicSort);
        return rows;
    }

    /**
     * 修改音乐分类
     *
     * @param blueMusicSort 音乐分类
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBlueMusicSort(BlueMusicSort blueMusicSort) {
        blueMusicSort.setUpdateTime(DateUtils.getNowDate());
        blueMusicSortMapper.deleteBlueMusicBySortId(blueMusicSort.getId());
        insertBlueMusic(blueMusicSort);
        return blueMusicSortMapper.updateBlueMusicSort(blueMusicSort);
    }

    /**
     * 批量删除音乐分类
     *
     * @param ids 需要删除的音乐分类主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBlueMusicSortByIds(Long[] ids) {
        blueMusicSortMapper.deleteBlueMusicBySortIds(ids);
        return blueMusicSortMapper.deleteBlueMusicSortByIds(ids);
    }

    /**
     * 删除音乐分类信息
     *
     * @param id 音乐分类主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBlueMusicSortById(Long id) {
        blueMusicSortMapper.deleteBlueMusicBySortId(id);
        return blueMusicSortMapper.deleteBlueMusicSortById(id);
    }

    /**
     * 新增音乐信息
     *
     * @param blueMusicSort 音乐分类对象
     */
    public void insertBlueMusic(BlueMusicSort blueMusicSort) {
        List<BlueMusic> blueMusicList = blueMusicSort.getBlueMusicList();
        Long id = blueMusicSort.getId();
        if (StringUtils.isNotNull(blueMusicList)) {
            List<BlueMusic> list = new ArrayList<BlueMusic>();
            for (BlueMusic blueMusic : blueMusicList) {
                blueMusic.setSortId(id);
                list.add(blueMusic);
            }
            if (list.size() > 0) {
                blueMusicSortMapper.batchBlueMusic(list);
            }
        }
    }
}
