package com.blue.blog.service.impl;

import com.blue.blog.domain.BlueLeaveMessage;
import com.blue.blog.mapper.BlueLeaveMessageMapper;
import com.blue.blog.service.IBlueLeaveMessageService;
import com.blue.common.core.exception.ServiceException;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.system.api.domain.SysUser;
import com.blue.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 弹幕Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Service
public class BlueLeaveMessageServiceImpl implements IBlueLeaveMessageService 
{
    @Resource
    private BlueLeaveMessageMapper blueLeaveMessageMapper;
    @Resource
    private SysUserMapper userMapper;

    /**
     * 查询弹幕
     * 
     * @param id 弹幕主键
     * @return 弹幕
     */
    @Override
    public BlueLeaveMessage selectBlueLeaveMessageById(Long id)
    {
        return blueLeaveMessageMapper.selectBlueLeaveMessageById(id);
    }

    /**
     * 查询弹幕列表
     * 
     * @param blueLeaveMessage 弹幕
     * @return 弹幕
     */
    @Override
    public List<BlueLeaveMessage> selectBlueLeaveMessageList(BlueLeaveMessage blueLeaveMessage)
    {
        List<BlueLeaveMessage> blueLeaveMessages = blueLeaveMessageMapper.selectBlueLeaveMessageList(blueLeaveMessage);
        List<SysUser> UserList = userMapper.selectUserList(new SysUser());
        //遍历获取用户名称
        for (BlueLeaveMessage leaveMessage : blueLeaveMessages) {
            for (SysUser user : UserList) {
                if (user.getUserId().equals(leaveMessage.getUserId())){
                    leaveMessage.setUserName(user.getUserName());
                }
            }
            if (!StringUtils.isNotNull(leaveMessage.getUserName())){
                leaveMessage.setUserName("游客");
            }
        }
        return blueLeaveMessages;
    }

    /**
     * 新增弹幕
     * 
     * @param blueLeaveMessage 弹幕
     * @return 结果
     */
    @Override
    public int insertBlueLeaveMessage(BlueLeaveMessage blueLeaveMessage)
    {
        //判断弹幕是否为空
        if (!StringUtils.isNotEmpty(blueLeaveMessage.getContent())){
            throw new ServiceException("请填写弹幕内容");
        }
        //判断弹幕输入字符必须大于4 不能是纯数字
        // 使用正则表达式判断是否为纯数字
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(blueLeaveMessage.getContent());
        if (blueLeaveMessage.getContent().length()<4 || matcher.matches()){
            throw new ServiceException("弹幕内容少于4个字或内容为纯数字");
        }
        if (!StringUtils.isNotNull(blueLeaveMessage.getUserId()) ||
                !StringUtils.isNotEmpty(blueLeaveMessage.getBarrageHeight().toString()) ||
                !StringUtils.isNotEmpty(blueLeaveMessage.getUserAvater())){
            throw new ServiceException("出错了,请刷新页面");
        }
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            blueLeaveMessage.setCreateBy(userId.toString());
        }
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
    public int updateBlueLeaveMessage(BlueLeaveMessage blueLeaveMessage)
    {
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            blueLeaveMessage.setUpdateBy(userId.toString());
        }
        blueLeaveMessage.setUpdateTime(DateUtils.getNowDate());
        return blueLeaveMessageMapper.updateBlueLeaveMessage(blueLeaveMessage);
    }

    /**
     * 批量删除弹幕
     * 
     * @param ids 需要删除的弹幕主键
     * @return 结果
     */
    @Override
    public int deleteBlueLeaveMessageByIds(Long[] ids)
    {
        return blueLeaveMessageMapper.deleteBlueLeaveMessageByIds(ids);
    }

    /**
     * 删除弹幕信息
     * 
     * @param id 弹幕主键
     * @return 结果
     */
    @Override
    public int deleteBlueLeaveMessageById(Long id)
    {
        return blueLeaveMessageMapper.deleteBlueLeaveMessageById(id);
    }
}
