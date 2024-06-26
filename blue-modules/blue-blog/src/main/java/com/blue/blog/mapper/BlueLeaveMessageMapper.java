package com.blue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.blog.entry.dao.BlueLeaveMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 弹幕Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Mapper
public interface BlueLeaveMessageMapper extends BaseMapper<BlueLeaveMessage>
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
     * 删除弹幕
     * 
     * @param id 弹幕主键
     * @return 结果
     */
    public int deleteBlueLeaveMessageById(Long id);

    /**
     * 批量删除弹幕
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueLeaveMessageByIds(Long[] ids);
}
