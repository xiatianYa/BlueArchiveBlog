package com.blue.blog.service.impl;

import com.blue.blog.entry.dao.BlueFriend;
import com.blue.blog.mapper.BlueFriendMapper;
import com.blue.blog.service.IBlueFriendService;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 友情链接Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-22
 */
@Service
public class BlueFriendServiceImpl implements IBlueFriendService 
{
    @Resource
    private BlueFriendMapper blueFriendMapper;

    /**
     * 查询友情链接
     * 
     * @param id 友情链接主键
     * @return 友情链接
     */
    @Override
    public BlueFriend selectBlueFriendById(Long id)
    {
        return blueFriendMapper.selectBlueFriendById(id);
    }

    /**
     * 查询友情链接列表
     * 
     * @param blueFriend 友情链接
     * @return 友情链接
     */
    @Override
    public List<BlueFriend> selectBlueFriendList(BlueFriend blueFriend)
    {
        return blueFriendMapper.selectBlueFriendList(blueFriend);
    }

    /**
     * 新增友情链接
     * 
     * @param blueFriend 友情链接
     * @return 结果
     */
    @Override
    public int insertBlueFriend(BlueFriend blueFriend)
    {
        //设置创建人
        Long userId = SecurityUtils.getLoginUser().getUserid();
        if (StringUtils.isNotNull(userId)){
            blueFriend.setCreateBy(String.valueOf(userId));
        }
        //设置申请状态
        blueFriend.setStatus(AuditingStatus.OK.getCode());
        blueFriend.setCreateTime(DateUtils.getNowDate());
        return blueFriendMapper.insertBlueFriend(blueFriend);
    }

    /**
     * 修改友情链接
     * 
     * @param blueFriend 友情链接
     * @return 结果
     */
    @Override
    public int updateBlueFriend(BlueFriend blueFriend)
    {
        //设置修改人
        Long userId = SecurityUtils.getLoginUser().getUserid();
        if (StringUtils.isNotNull(userId)){
            blueFriend.setUpdateBy(String.valueOf(userId));
        }
        blueFriend.setUpdateTime(DateUtils.getNowDate());
        return blueFriendMapper.updateBlueFriend(blueFriend);
    }

    /**
     * 批量删除友情链接
     * 
     * @param ids 需要删除的友情链接主键
     * @return 结果
     */
    @Override
    public int deleteBlueFriendByIds(Long[] ids)
    {
        return blueFriendMapper.deleteBlueFriendByIds(ids);
    }

    /**
     * 删除友情链接信息
     * 
     * @param id 友情链接主键
     * @return 结果
     */
    @Override
    public int deleteBlueFriendById(Long id)
    {
        return blueFriendMapper.deleteBlueFriendById(id);
    }
}
