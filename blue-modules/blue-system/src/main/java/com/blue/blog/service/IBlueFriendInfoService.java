package com.blue.blog.service;

import com.blue.blog.domain.BlueFriendInfo;

import java.util.List;

/**
 * 友链申请信息Service接口
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
public interface IBlueFriendInfoService 
{
    /**
     * 查询友链申请信息
     * 
     * @param id 友链申请信息主键
     * @return 友链申请信息
     */
    public BlueFriendInfo selectBlueFriendInfoById(Long id);

    /**
     * 查询友链申请信息列表
     * 
     * @param blueFriendInfo 友链申请信息
     * @return 友链申请信息集合
     */
    public List<BlueFriendInfo> selectBlueFriendInfoList(BlueFriendInfo blueFriendInfo);

    /**
     * 新增友链申请信息
     * 
     * @param blueFriendInfo 友链申请信息
     * @return 结果
     */
    public int insertBlueFriendInfo(BlueFriendInfo blueFriendInfo);

    /**
     * 修改友链申请信息
     * 
     * @param blueFriendInfo 友链申请信息
     * @return 结果
     */
    public int updateBlueFriendInfo(BlueFriendInfo blueFriendInfo);

    /**
     * 批量删除友链申请信息
     * 
     * @param ids 需要删除的友链申请信息主键集合
     * @return 结果
     */
    public int deleteBlueFriendInfoByIds(Long[] ids);

    /**
     * 删除友链申请信息信息
     * 
     * @param id 友链申请信息主键
     * @return 结果
     */
    public int deleteBlueFriendInfoById(Long id);
}
