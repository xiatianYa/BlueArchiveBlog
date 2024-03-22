package com.blue.blog.service.impl;

import com.blue.blog.domain.BluePhoto;
import com.blue.blog.mapper.BluePhotoMapper;
import com.blue.blog.service.IBluePhotoService;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.system.api.domain.SysUser;
import com.blue.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private BluePhotoMapper bluePhotoMapper;
    @Autowired
    private ISysUserService userService;
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
        List<SysUser> sysUsers = userService.selectUserList(new SysUser());
        for (BluePhoto photo : bluePhotos) {
            for (SysUser sysUser : sysUsers) {
                if (sysUser.getUserId().equals(photo.getUserId())){
                    photo.setUserName(sysUser.getUserName());
                }
            }
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
        //设置创建用户ID
        bluePhoto.setCreateBy(SecurityUtils.getLoginUser().getUserid().toString());
        bluePhoto.setCreateTime(DateUtils.getNowDate());
        return bluePhotoMapper.insertBluePhoto(bluePhoto);
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
        return bluePhotoMapper.deleteBluePhotoByIds(ids);
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
        return bluePhotoMapper.deleteBluePhotoById(id);
    }
}
