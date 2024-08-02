package com.blue.blog.service.impl;

import com.blue.blog.entry.dao.BluePixivLeaveMessage;
import com.blue.blog.mapper.BluePixivLeaveMessageMapper;
import com.blue.blog.service.IBluePixivLeaveMessageService;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 番剧弹幕Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-05
 */
@Service
public class BluePixivLeaveMessageServiceImpl implements IBluePixivLeaveMessageService 
{
    @Resource
    private BluePixivLeaveMessageMapper bluePixivLeaveMessageMapper;

    /**
     * 查询番剧弹幕
     * 
     * @param id 番剧弹幕主键
     * @return 番剧弹幕
     */
    @Override
    public BluePixivLeaveMessage selectBluePixivLeaveMessageById(Long id)
    {
        return bluePixivLeaveMessageMapper.selectBluePixivLeaveMessageById(id);
    }

    /**
     * 查询番剧弹幕列表
     * 
     * @param bluePixivLeaveMessage 番剧弹幕
     * @return 番剧弹幕
     */
    @Override
    public List<BluePixivLeaveMessage> selectBluePixivLeaveMessageList(BluePixivLeaveMessage bluePixivLeaveMessage)
    {
        return bluePixivLeaveMessageMapper.selectBluePixivLeaveMessageList(bluePixivLeaveMessage);
    }

    /**
     * 新增番剧弹幕
     * 
     * @param bluePixivLeaveMessage 番剧弹幕
     * @return 结果
     */
    @Override
    public int insertBluePixivLeaveMessage(BluePixivLeaveMessage bluePixivLeaveMessage)
    {
        return bluePixivLeaveMessageMapper.insertBluePixivLeaveMessage(bluePixivLeaveMessage);
    }

    /**
     * 修改番剧弹幕
     * 
     * @param bluePixivLeaveMessage 番剧弹幕
     * @return 结果
     */
    @Override
    public int updateBluePixivLeaveMessage(BluePixivLeaveMessage bluePixivLeaveMessage)
    {
        return bluePixivLeaveMessageMapper.updateBluePixivLeaveMessage(bluePixivLeaveMessage);
    }

    /**
     * 批量删除番剧弹幕
     * 
     * @param ids 需要删除的番剧弹幕主键
     * @return 结果
     */
    @Override
    public int deleteBluePixivLeaveMessageByIds(Long[] ids)
    {
        return bluePixivLeaveMessageMapper.deleteBluePixivLeaveMessageByIds(ids);
    }

    /**
     * 删除番剧弹幕信息
     * 
     * @param id 番剧弹幕主键
     * @return 结果
     */
    @Override
    public int deleteBluePixivLeaveMessageById(Long id)
    {
        return bluePixivLeaveMessageMapper.deleteBluePixivLeaveMessageById(id);
    }
}
