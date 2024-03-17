package com.blue.sort.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.blue.common.core.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.blue.sort.domain.BlueSortTag;
import com.blue.sort.mapper.BlueSortMapper;
import com.blue.sort.domain.BlueSort;
import com.blue.sort.service.IBlueSortService;

/**
 * 分类Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-17
 */
@Service
public class BlueSortServiceImpl implements IBlueSortService {
    @Autowired
    private BlueSortMapper blueSortMapper;

    /**
     * 查询分类
     *
     * @param id 分类主键
     * @return 分类
     */
    @Override
    public BlueSort selectBlueSortById(Long id) {
        return blueSortMapper.selectBlueSortById(id);
    }

    /**
     * 查询分类列表
     *
     * @param blueSort 分类
     * @return 分类
     */
    @Override
    public List<BlueSort> selectBlueSortList(BlueSort blueSort) {
        return blueSortMapper.selectBlueSortList(blueSort);
    }

    /**
     * 新增分类
     *
     * @param blueSort 分类
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBlueSort(BlueSort blueSort) {
        int rows = blueSortMapper.insertBlueSort(blueSort);
        insertBlueSortTag(blueSort);
        return rows;
    }

    /**
     * 修改分类
     *
     * @param blueSort 分类
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBlueSort(BlueSort blueSort) {
        blueSortMapper.deleteBlueSortTagBySortId(blueSort.getId());
        insertBlueSortTag(blueSort);
        return blueSortMapper.updateBlueSort(blueSort);
    }

    /**
     * 批量删除分类
     *
     * @param ids 需要删除的分类主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBlueSortByIds(Long[] ids) {
        blueSortMapper.deleteBlueSortTagBySortIds(ids);
        return blueSortMapper.deleteBlueSortByIds(ids);
    }

    /**
     * 删除分类信息
     *
     * @param id 分类主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBlueSortById(Long id) {
        blueSortMapper.deleteBlueSortTagBySortId(id);
        return blueSortMapper.deleteBlueSortById(id);
    }

    /**
     * 新增标签信息
     *
     * @param blueSort 分类对象
     */
    public void insertBlueSortTag(BlueSort blueSort) {
        List<BlueSortTag> blueSortTagList = blueSort.getBlueSortTagList();
        Long id = blueSort.getId();
        if (StringUtils.isNotNull(blueSortTagList)) {
            List<BlueSortTag> list = new ArrayList<BlueSortTag>();
            for (BlueSortTag blueSortTag : blueSortTagList) {
                blueSortTag.setSortId(id);
                list.add(blueSortTag);
            }
            if (list.size() > 0) {
                blueSortMapper.batchBlueSortTag(list);
            }
        }
    }
}
