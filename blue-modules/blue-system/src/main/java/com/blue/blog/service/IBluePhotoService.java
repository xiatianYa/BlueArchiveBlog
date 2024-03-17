package com.blue.system.service;

import java.util.List;

import com.blue.system.domain.BluePhoto;

/**
 * 相册Service接口
 *
 * @author ruoyi
 * @date 2024-03-17
 */
public interface IBluePhotoService {
    /**
     * 查询相册
     *
     * @param id 相册主键
     * @return 相册
     */
    public BluePhoto selectBluePhotoById(Long id);

    /**
     * 查询相册列表
     *
     * @param bluePhoto 相册
     * @return 相册集合
     */
    public List<BluePhoto> selectBluePhotoList(BluePhoto bluePhoto);

    /**
     * 新增相册
     *
     * @param bluePhoto 相册
     * @return 结果
     */
    public int insertBluePhoto(BluePhoto bluePhoto);

    /**
     * 修改相册
     *
     * @param bluePhoto 相册
     * @return 结果
     */
    public int updateBluePhoto(BluePhoto bluePhoto);

    /**
     * 批量删除相册
     *
     * @param ids 需要删除的相册主键集合
     * @return 结果
     */
    public int deleteBluePhotoByIds(Long[] ids);

    /**
     * 删除相册信息
     *
     * @param id 相册主键
     * @return 结果
     */
    public int deleteBluePhotoById(Long id);
}
