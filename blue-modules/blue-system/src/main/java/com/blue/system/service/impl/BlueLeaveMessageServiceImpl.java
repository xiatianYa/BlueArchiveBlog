package com.blue.system.service.impl;

import java.util.List;
import com.blue.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.system.mapper.BlueLeaveMessageMapper;
import com.blue.system.domain.BlueLeaveMessage;
import com.blue.system.service.IBlueLeaveMessageService;

/**
 * 弹幕Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-17
 */
@Service
public class BlueLeaveMessageServiceImpl implements IBlueLeaveMessageService {
    @Autowired
    private BlueLeaveMessageMapper blueLeaveMessageMapper;

    /**
     * 查询弹幕
     * 
     * @param id 弹幕主键
     * @return 弹幕
     */
    @Override
    public BlueLeaveMessage selectBlueLeaveMessageById(Long id) {
        return blueLeaveMessageMapper.selectBlueLeaveMessageById(id);
    }

    /**
     * 查询弹幕列表
     * 
     * @param blueLeaveMessage 弹幕
     * @return 弹幕
     */
    @Override
    public List<BlueLeaveMessage> selectBlueLeaveMessageList(BlueLeaveMessage blueLeaveMessage) {
        return blueLeaveMessageMapper.selectBlueLeaveMessageList(blueLeaveMessage);
    }

    /**
     * 新增弹幕
     * 
     * @param blueLeaveMessage 弹幕
     * @return 结果
     */
    @Override
    public int insertBlueLeaveMessage(BlueLeaveMessage blueLeaveMessage) {
        blueLeaveMessage.setCreateTime(DateUtils.getNowDate());
        return blueLeaveMessageMapper.insertBlueLeaveMessage(blueLeaveMessage);
    }

    /**
     * 修改弹幕
     * 
     * @param blueLeaveMessage 弹幕
     * @return 结果
     */
    @Override
    public int updateBlueLeaveMessage(BlueLeaveMessage blueLeaveMessage) {
        return blueLeaveMessageMapper.updateBlueLeaveMessage(blueLeaveMessage);
    }

    /**
     * 批量删除弹幕
     * 
     * @param ids 需要删除的弹幕主键
     * @return 结果
     */
    @Override
    public int deleteBlueLeaveMessageByIds(Long[] ids) {
        return blueLeaveMessageMapper.deleteBlueLeaveMessageByIds(ids);
    }

    /**
     * 删除弹幕信息
     * 
     * @param id 弹幕主键
     * @return 结果
     */
    @Override
    public int deleteBlueLeaveMessageById(Long id) {
        return blueLeaveMessageMapper.deleteBlueLeaveMessageById(id);
    }
}
