package com.blue.blog.service.impl;

import com.blue.blog.domain.BlueWebsite;
import com.blue.blog.mapper.BlueWebsiteMapper;
import com.blue.blog.service.IBlueWebsiteService;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 网站信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@Service
public class BlueWebsiteServiceImpl implements IBlueWebsiteService 
{
    @Resource
    private BlueWebsiteMapper blueWebsiteMapper;

    /**
     * 查询网站信息
     * 
     * @param id 网站信息主键
     * @return 网站信息
     */
    @Override
    public BlueWebsite selectBlueWebsiteById(Long id)
    {
        return blueWebsiteMapper.selectBlueWebsiteById(id);
    }

    /**
     * 查询网站信息列表
     * 
     * @param blueWebsite 网站信息
     * @return 网站信息
     */
    @Override
    public List<BlueWebsite> selectBlueWebsiteList(BlueWebsite blueWebsite)
    {
        return blueWebsiteMapper.selectBlueWebsiteList(blueWebsite);
    }

    /**
     * 新增网站信息
     * 
     * @param blueWebsite 网站信息
     * @return 结果
     */
    @Override
    public int insertBlueWebsite(BlueWebsite blueWebsite)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueWebsite.setCreateBy(String.valueOf(userId));
        }
        blueWebsite.setCreateTime(DateUtils.getNowDate());
        return blueWebsiteMapper.insertBlueWebsite(blueWebsite);
    }

    /**
     * 修改网站信息
     * 
     * @param blueWebsite 网站信息
     * @return 结果
     */
    @Override
    public int updateBlueWebsite(BlueWebsite blueWebsite)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueWebsite.setUpdateBy(String.valueOf(userId));
        }
        blueWebsite.setUpdateTime(DateUtils.getNowDate());
        return blueWebsiteMapper.updateBlueWebsite(blueWebsite);
    }

    /**
     * 批量删除网站信息
     * 
     * @param ids 需要删除的网站信息主键
     * @return 结果
     */
    @Override
    public int deleteBlueWebsiteByIds(Long[] ids)
    {
        return blueWebsiteMapper.deleteBlueWebsiteByIds(ids);
    }

    /**
     * 删除网站信息信息
     * 
     * @param id 网站信息主键
     * @return 结果
     */
    @Override
    public int deleteBlueWebsiteById(Long id)
    {
        return blueWebsiteMapper.deleteBlueWebsiteById(id);
    }
}
