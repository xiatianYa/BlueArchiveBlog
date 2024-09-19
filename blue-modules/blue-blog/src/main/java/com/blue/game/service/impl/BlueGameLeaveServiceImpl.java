package com.blue.game.service.impl;

import java.util.Date;
import java.util.List;

import com.blue.common.core.constant.SecurityConstants;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.system.api.RemoteUserService;
import com.blue.system.api.model.UserVo;
import org.springframework.stereotype.Service;
import com.blue.game.mapper.BlueGameLeaveMapper;
import com.blue.game.domain.BlueGameLeave;
import com.blue.game.service.IBlueGameLeaveService;

import javax.annotation.Resource;

/**
 * 游戏留言Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-17
 */
@Service
public class BlueGameLeaveServiceImpl implements IBlueGameLeaveService {
    @Resource
    private BlueGameLeaveMapper blueGameLeaveMapper;
    @Resource
    private RemoteUserService remoteUserService;

    /**
     * 查询游戏留言
     *
     * @param id 游戏留言主键
     * @return 游戏留言
     */
    @Override
    public BlueGameLeave selectBlueGameLeaveById(Long id) {
        return blueGameLeaveMapper.selectBlueGameLeaveById(id);
    }

    /**
     * 查询游戏留言列表
     *
     * @param blueGameLeave 游戏留言
     * @return 游戏留言
     */
    @Override
    public List<BlueGameLeave> selectBlueGameLeaveList(BlueGameLeave blueGameLeave) {
        //获取留言列表
        List<BlueGameLeave> blueGameLeaves = blueGameLeaveMapper.selectBlueGameLeaveList(blueGameLeave);
        for (BlueGameLeave blueGameLeaf : blueGameLeaves) {
            UserVo userVo =
                    remoteUserService.getUserInfoById(blueGameLeaf.getCreateBy(), SecurityConstants.FROM_SOURCE).getData();
            if (StringUtils.isNotNull(userVo.getUserNickName())){
                blueGameLeaf.setNickName(userVo.getUserNickName());
                blueGameLeaf.setUserAvatar(userVo.getUserAvatar());
            }else{
                blueGameLeaf.setNickName("未知用户");
            }
        }
        return blueGameLeaves;
    }

    /**
     * 新增游戏留言
     *
     * @param blueGameLeave 游戏留言
     * @return 结果
     */
    @Override
    public int insertBlueGameLeave(BlueGameLeave blueGameLeave) {
        Long userid = SecurityUtils.getLoginUser().getUserid();
        blueGameLeave.setCreateBy(userid);
        blueGameLeave.setCreateTime(new Date());
        return blueGameLeaveMapper.insertBlueGameLeave(blueGameLeave);
    }

    /**
     * 修改游戏留言
     *
     * @param blueGameLeave 游戏留言
     * @return 结果
     */
    @Override
    public int updateBlueGameLeave(BlueGameLeave blueGameLeave) {
        return blueGameLeaveMapper.updateBlueGameLeave(blueGameLeave);
    }

    /**
     * 批量删除游戏留言
     *
     * @param ids 需要删除的游戏留言主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameLeaveByIds(Long[] ids) {
        return blueGameLeaveMapper.deleteBlueGameLeaveByIds(ids);
    }

    /**
     * 删除游戏留言信息
     *
     * @param id 游戏留言主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameLeaveById(Long id) {
        return blueGameLeaveMapper.deleteBlueGameLeaveById(id);
    }
}
