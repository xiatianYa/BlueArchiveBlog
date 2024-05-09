package com.blue.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.entry.dao.BluePhoto;
import com.blue.blog.mapper.BluePhotoMapper;
import com.blue.blog.service.IBluePhotoService;
import com.blue.common.core.constant.SecurityConstants;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.common.core.exception.ServiceException;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.system.api.RemoteUserService;
import com.blue.system.api.domain.SysUser;
import com.blue.system.api.model.LoginUser;
import com.blue.system.api.model.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 相册Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Service
public class BluePhotoServiceImpl implements IBluePhotoService 
{
    @Resource
    private BluePhotoMapper bluePhotoMapper;
    @Resource
    private RemoteUserService remoteUserService;
    /**
     * 查询相册
     * 
     * @param id 相册主键
     * @return 相册
     */
    @Override
    public BluePhoto selectBluePhotoById(Long id)
    {
        return bluePhotoMapper.selectBluePhotoById(id);
    }

    /**
     * 查询相册列表
     * 
     * @param bluePhoto 相册
     * @return 相册
     */
    @Override
    public List<BluePhoto> selectBluePhotoList(BluePhoto bluePhoto)
    {
        //如果没有携带查询state 则默认查询审核已通过的
        if (!StringUtils.isNotNull(bluePhoto.getStatus())){
             bluePhoto.setStatus(AuditingStatus.DISABLE.getCode());
        }
        //查询用户设置用户名称
        List<BluePhoto> bluePhotos = bluePhotoMapper.selectBluePhotoList(bluePhoto);
        for (BluePhoto photo : bluePhotos) {
            UserVo userVo =
                    remoteUserService.getUserInfoById(photo.getUserId(), SecurityConstants.FROM_SOURCE).getData();
            photo.setUserName(userVo.getUserNickName());
        }
        return bluePhotos;
    }

    /**
     * 新增相册
     * 
     * @param bluePhoto 相册
     * @return 结果
     */
    @Override
    public int insertBluePhoto(BluePhoto bluePhoto)
    {
        //设置默认新增相册状态
        bluePhoto.setStatus(AuditingStatus.OK.getCode());
        //设置逻辑删除状态
        bluePhoto.setDelFlag(0);
        //设置创建用户ID
        bluePhoto.setUserId(SecurityUtils.getLoginUser().getUserid());
        bluePhoto.setCreateBy(SecurityUtils.getLoginUser().getUserid().toString());
        bluePhoto.setCreateTime(DateUtils.getNowDate());
        return bluePhotoMapper.insert(bluePhoto);
    }

    /**
     * 修改相册
     * 
     * @param bluePhoto 相册
     * @return 结果
     */
    @Override
    public int updateBluePhoto(BluePhoto bluePhoto)
    {
        //检测用户操作权限
        isCheckUser(bluePhoto.getUserId());
        //设置修改用户ID
        bluePhoto.setUpdateBy(SecurityUtils.getLoginUser().getUserid().toString());
        bluePhoto.setUpdateTime(DateUtils.getNowDate());
        return bluePhotoMapper.updateBluePhoto(bluePhoto);
    }

    /**
     * 批量删除相册
     * 
     * @param ids 需要删除的相册主键
     * @return 结果
     */
    @Override
    public int deleteBluePhotoByIds(Long[] ids)
    {
        for (Long id : ids) {
            //检测用户操作权限
            isCheckUser(id);
        }
        return bluePhotoMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除相册信息
     * 
     * @param id 相册主键
     * @return 结果
     */
    @Override
    public int deleteBluePhotoById(Long id)
    {
        //检测用户操作权限
        isCheckUser(id);
        return bluePhotoMapper.deleteById(id);
    }


    /**
     * 查询用户下的相册列表
     */
    @Override
    public List<BluePhoto> selectBluePhotoListByUser() {
        LambdaQueryWrapper<BluePhoto> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BluePhoto::getUserId, SecurityUtils.getLoginUser().getUserid());
        //查询用户设置用户名称
        List<BluePhoto> bluePhotos = bluePhotoMapper.selectList(wrapper);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        for (BluePhoto photo : bluePhotos) {
            photo.setUserName(loginUser.getUsername());
        }
        return bluePhotos;
    }

    /**
     * 检测用户操作是否合法
     */
    public void isCheckUser(Long photoId){
        Long userId = SecurityUtils.getLoginUser().getUserid();
        //管理员操作
        if(SecurityUtils.isAdmin(userId)){
            return;
        }
        BluePhoto bluePhoto = bluePhotoMapper.selectById(photoId);
        //非本人操作
        if (!userId.equals(bluePhoto.getUserId())){
            throw new ServiceException("您没有权限操作该文章...");
        }
    }
}
