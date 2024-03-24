package com.blue.blog.service.impl;

import com.blue.blog.domain.BlueFriendInfo;
import com.blue.blog.mapper.BlueFriendInfoMapper;
import com.blue.blog.service.IBlueFriendInfoService;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链申请信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@Service
public class BlueFriendInfoServiceImpl implements IBlueFriendInfoService 
{
    @Autowired
    private BlueFriendInfoMapper blueFriendInfoMapper;

    /**
     * 查询友链申请信息
     * 
     * @param id 友链申请信息主键
     * @return 友链申请信息
     */
    @Override
    public BlueFriendInfo selectBlueFriendInfoById(Long id)
    {
        return blueFriendInfoMapper.selectBlueFriendInfoById(id);
    }

    /**
     * 查询友链申请信息列表
     * 
     * @param blueFriendInfo 友链申请信息
     * @return 友链申请信息
     */
    @Override
    public List<BlueFriendInfo> selectBlueFriendInfoList(BlueFriendInfo blueFriendInfo)
    {
        return blueFriendInfoMapper.selectBlueFriendInfoList(blueFriendInfo);
    }

    /**
     * 新增友链申请信息
     * 
     * @param blueFriendInfo 友链申请信息
     * @return 结果
     */
    @Override
    public int insertBlueFriendInfo(BlueFriendInfo blueFriendInfo)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueFriendInfo.setCreateBy(String.valueOf(userId));
        }
        blueFriendInfo.setCreateTime(DateUtils.getNowDate());
        return blueFriendInfoMapper.insertBlueFriendInfo(blueFriendInfo);
    }

    /**
     * 修改友链申请信息
     * 
     * @param blueFriendInfo 友链申请信息
     * @return 结果
     */
    @Override
    public int updateBlueFriendInfo(BlueFriendInfo blueFriendInfo)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueFriendInfo.setUpdateBy(String.valueOf(userId));
        }
        blueFriendInfo.setUpdateTime(DateUtils.getNowDate());
        return blueFriendInfoMapper.updateBlueFriendInfo(blueFriendInfo);
    }

    /**
     * 批量删除友链申请信息
     * 
     * @param ids 需要删除的友链申请信息主键
     * @return 结果
     */
    @Override
    public int deleteBlueFriendInfoByIds(Long[] ids)
    {
        return blueFriendInfoMapper.deleteBlueFriendInfoByIds(ids);
    }

    /**
     * 删除友链申请信息信息
     * 
     * @param id 友链申请信息主键
     * @return 结果
     */
    @Override
    public int deleteBlueFriendInfoById(Long id)
    {
        return blueFriendInfoMapper.deleteBlueFriendInfoById(id);
    }
}
