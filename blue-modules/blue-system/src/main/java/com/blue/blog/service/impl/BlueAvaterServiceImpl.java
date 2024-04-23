package com.blue.blog.service.impl;

import com.blue.blog.entry.dao.BlueAvater;
import com.blue.blog.mapper.BlueAvaterMapper;
import com.blue.blog.service.IBlueAvaterService;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户头像列存储Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Service
public class BlueAvaterServiceImpl implements IBlueAvaterService 
{
    @Resource
    private BlueAvaterMapper blueAvaterMapper;

    /**
     * 查询用户头像列存储
     * 
     * @param id 用户头像列存储主键
     * @return 用户头像列存储
     */
    @Override
    public BlueAvater selectBlueAvaterById(Long id)
    {
        return blueAvaterMapper.selectBlueAvaterById(id);
    }

    /**
     * 查询用户头像列存储列表
     * 
     * @param blueAvater 用户头像列存储
     * @return 用户头像列存储
     */
    @Override
    public List<BlueAvater> selectBlueAvaterList(BlueAvater blueAvater)
    {
        return blueAvaterMapper.selectBlueAvaterList(blueAvater);
    }

    /**
     * 新增用户头像列存储
     * 
     * @param blueAvater 用户头像列存储
     * @return 结果
     */
    @Override
    public int insertBlueAvater(BlueAvater blueAvater)
    {
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            blueAvater.setCreateBy(userId.toString());
        }
        blueAvater.setCreateTime(DateUtils.getNowDate());
        return blueAvaterMapper.insertBlueAvater(blueAvater);
    }

    /**
     * 修改用户头像列存储
     * 
     * @param blueAvater 用户头像列存储
     * @return 结果
     */
    @Override
    public int updateBlueAvater(BlueAvater blueAvater)
    {
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            blueAvater.setUpdateBy(userId.toString());
        }
        blueAvater.setUpdateTime(DateUtils.getNowDate());
        return blueAvaterMapper.updateBlueAvater(blueAvater);
    }

    /**
     * 批量删除用户头像列存储
     * 
     * @param ids 需要删除的用户头像列存储主键
     * @return 结果
     */
    @Override
    public int deleteBlueAvaterByIds(Long[] ids)
    {
        return blueAvaterMapper.deleteBlueAvaterByIds(ids);
    }

    /**
     * 删除用户头像列存储信息
     * 
     * @param id 用户头像列存储主键
     * @return 结果
     */
    @Override
    public int deleteBlueAvaterById(Long id)
    {
        return blueAvaterMapper.deleteBlueAvaterById(id);
    }
}
