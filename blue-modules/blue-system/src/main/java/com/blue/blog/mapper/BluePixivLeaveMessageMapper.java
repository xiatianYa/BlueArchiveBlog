package com.blue.blog.mapper;

import com.blue.blog.domain.BluePixivLeaveMessage;

import java.util.List;

/**
 * 番剧弹幕Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-05
 */
public interface BluePixivLeaveMessageMapper 
{
    /**
     * 查询番剧弹幕
     * 
     * @param id 番剧弹幕主键
     * @return 番剧弹幕
     */
    public BluePixivLeaveMessage selectBluePixivLeaveMessageById(Long id);

    /**
     * 查询番剧弹幕列表
     * 
     * @param bluePixivLeaveMessage 番剧弹幕
     * @return 番剧弹幕集合
     */
    public List<BluePixivLeaveMessage> selectBluePixivLeaveMessageList(BluePixivLeaveMessage bluePixivLeaveMessage);

    /**
     * 新增番剧弹幕
     * 
     * @param bluePixivLeaveMessage 番剧弹幕
     * @return 结果
     */
    public int insertBluePixivLeaveMessage(BluePixivLeaveMessage bluePixivLeaveMessage);

    /**
     * 修改番剧弹幕
     * 
     * @param bluePixivLeaveMessage 番剧弹幕
     * @return 结果
     */
    public int updateBluePixivLeaveMessage(BluePixivLeaveMessage bluePixivLeaveMessage);

    /**
     * 删除番剧弹幕
     * 
     * @param id 番剧弹幕主键
     * @return 结果
     */
    public int deleteBluePixivLeaveMessageById(Long id);

    /**
     * 批量删除番剧弹幕
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBluePixivLeaveMessageByIds(Long[] ids);
}
