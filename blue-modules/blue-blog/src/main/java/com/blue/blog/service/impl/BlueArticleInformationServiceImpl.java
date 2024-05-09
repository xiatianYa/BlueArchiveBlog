package com.blue.blog.service.impl;

import com.blue.blog.entry.dao.BlueArticleInformation;
import com.blue.blog.mapper.BlueArticleInformationMapper;
import com.blue.blog.service.IBlueArticleInformationService;
import com.blue.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-07
 */
@Service
public class BlueArticleInformationServiceImpl implements IBlueArticleInformationService 
{
    @Autowired
    private BlueArticleInformationMapper blueArticleInformationMapper;

    /**
     * 查询文章信息
     * 
     * @param id 文章信息主键
     * @return 文章信息
     */
    @Override
    public BlueArticleInformation selectBlueArticleInformationById(Long id)
    {
        return blueArticleInformationMapper.selectBlueArticleInformationById(id);
    }

    /**
     * 查询文章信息列表
     * 
     * @param blueArticleInformation 文章信息
     * @return 文章信息
     */
    @Override
    public List<BlueArticleInformation> selectBlueArticleInformationList(BlueArticleInformation blueArticleInformation)
    {
        return blueArticleInformationMapper.selectBlueArticleInformationList(blueArticleInformation);
    }

    /**
     * 新增文章信息
     * 
     * @param blueArticleInformation 文章信息
     * @return 结果
     */
    @Override
    public int insertBlueArticleInformation(BlueArticleInformation blueArticleInformation)
    {
        blueArticleInformation.setCreateTime(DateUtils.getNowDate());
        return blueArticleInformationMapper.insertBlueArticleInformation(blueArticleInformation);
    }

    /**
     * 修改文章信息
     * 
     * @param blueArticleInformation 文章信息
     * @return 结果
     */
    @Override
    public int updateBlueArticleInformation(BlueArticleInformation blueArticleInformation)
    {
        blueArticleInformation.setUpdateTime(DateUtils.getNowDate());
        return blueArticleInformationMapper.updateBlueArticleInformation(blueArticleInformation);
    }

    /**
     * 批量删除文章信息
     * 
     * @param ids 需要删除的文章信息主键
     * @return 结果
     */
    @Override
    public int deleteBlueArticleInformationByIds(Long[] ids)
    {
        return blueArticleInformationMapper.deleteBlueArticleInformationByIds(ids);
    }

    /**
     * 删除文章信息信息
     * 
     * @param id 文章信息主键
     * @return 结果
     */
    @Override
    public int deleteBlueArticleInformationById(Long id)
    {
        return blueArticleInformationMapper.deleteBlueArticleInformationById(id);
    }
}
