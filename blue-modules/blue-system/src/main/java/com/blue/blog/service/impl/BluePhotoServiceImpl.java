package com.blue.system.service.impl;

import java.util.List;

import com.blue.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.system.mapper.BluePhotoMapper;
import com.blue.system.domain.BluePhoto;
import com.blue.system.service.IBluePhotoService;

/**
 * 相册Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-17
 */
@Service
public class BluePhotoServiceImpl implements IBluePhotoService {
    @Autowired
    private BluePhotoMapper bluePhotoMapper;

    /**
     * 查询相册
     *
     * @param id 相册主键
     * @return 相册
     */
    @Override
    public BluePhoto selectBluePhotoById(Long id) {
        return bluePhotoMapper.selectBluePhotoById(id);
    }

    /**
     * 查询相册列表
     *
     * @param bluePhoto 相册
     * @return 相册
     */
    @Override
    public List<BluePhoto> selectBluePhotoList(BluePhoto bluePhoto) {
        return bluePhotoMapper.selectBluePhotoList(bluePhoto);
    }

    /**
     * 新增相册
     *
     * @param bluePhoto 相册
     * @return 结果
     */
    @Override
    public int insertBluePhoto(BluePhoto bluePhoto) {
        bluePhoto.setCreateTime(DateUtils.getNowDate());
        bluePhoto.setUpdateTime(DateUtils.getNowDate());
        bluePhoto.setStatus(0l);
        return bluePhotoMapper.insertBluePhoto(bluePhoto);
    }

    /**
     * 修改相册
     *
     * @param bluePhoto 相册
     * @return 结果
     */
    @Override
    public int updateBluePhoto(BluePhoto bluePhoto) {
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
    public int deleteBluePhotoByIds(Long[] ids) {
        return bluePhotoMapper.deleteBluePhotoByIds(ids);
    }

    /**
     * 删除相册信息
     *
     * @param id 相册主键
     * @return 结果
     */
    @Override
    public int deleteBluePhotoById(Long id) {
        return bluePhotoMapper.deleteBluePhotoById(id);
    }
}
