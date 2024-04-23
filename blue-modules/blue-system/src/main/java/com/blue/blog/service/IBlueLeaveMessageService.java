package com.blue.blog.service;

import com.blue.blog.entry.dao.BlueLeaveMessage;

import java.util.List;

/**
 * 弹幕Service接口
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public interface IBlueLeaveMessageService 
{
    /**
     * 查询弹幕
     * 
     * @param id 弹幕主键
     * @return 弹幕
     */
    public BlueLeaveMessage selectBlueLeaveMessageById(Long id);

    /**
     * 查询弹幕列表
     * 
     * @param blueLeaveMessage 弹幕
     * @return 弹幕集合
     */
    public List<BlueLeaveMessage> selectBlueLeaveMessageList(BlueLeaveMessage blueLeaveMessage);

    /**
     * 新增弹幕
     * 
     * @param blueLeaveMessage 弹幕
     * @return 结果
     */
    public int insertBlueLeaveMessage(BlueLeaveMessage blueLeaveMessage);

    /**
     * 修改弹幕
     * 
     * @param blueLeaveMessage 弹幕
     * @return 结果
     */
    public int updateBlueLeaveMessage(BlueLeaveMessage blueLeaveMessage);

    /**
     * 批量删除弹幕
     * 
     * @param ids 需要删除的弹幕主键集合
     * @return 结果
     */
    public int deleteBlueLeaveMessageByIds(Long[] ids);

    /**
     * 删除弹幕信息
     * 
     * @param id 弹幕主键
     * @return 结果
     */
    public int deleteBlueLeaveMessageById(Long id);
}
