package com.blue.sort.service.impl;

import java.util.List;

import com.blue.sort.domain.BlueSortTag;
import com.blue.sort.mapper.BlueSortTagMapper;
import com.blue.sort.service.IBlueSortTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 标签Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-17
 */
@Service
public class BlueSortTagServiceImpl implements IBlueSortTagService {
    @Autowired
    private BlueSortTagMapper blueSortTagMapper;

    /**
     * 查询标签
     *
     * @param id 标签主键
     * @return 标签
     */
    @Override
    public BlueSortTag selectBlueSortTagById(Long id) {
        return blueSortTagMapper.selectBlueSortTagById(id);
    }

    /**
     * 查询标签列表
     *
     * @param blueSortTag 标签
     * @return 标签
     */
    @Override
    public List<BlueSortTag> selectBlueSortTagList(BlueSortTag blueSortTag) {
        return blueSortTagMapper.selectBlueSortTagList(blueSortTag);
    }

    /**
     * 新增标签
     *
     * @param blueSortTag 标签
     * @return 结果
     */
    @Override
    public int insertBlueSortTag(BlueSortTag blueSortTag) {
        return blueSortTagMapper.insertBlueSortTag(blueSortTag);
    }

    /**
     * 修改标签
     *
     * @param blueSortTag 标签
     * @return 结果
     */
    @Override
    public int updateBlueSortTag(BlueSortTag blueSortTag) {
        return blueSortTagMapper.updateBlueSortTag(blueSortTag);
    }

    /**
     * 批量删除标签
     *
     * @param ids 需要删除的标签主键
     * @return 结果
     */
    @Override
    public int deleteBlueSortTagByIds(Long[] ids) {
        return blueSortTagMapper.deleteBlueSortTagByIds(ids);
    }

    /**
     * 删除标签信息
     *
     * @param id 标签主键
     * @return 结果
     */
    @Override
    public int deleteBlueSortTagById(Long id) {
        return blueSortTagMapper.deleteBlueSortTagById(id);
    }
}
