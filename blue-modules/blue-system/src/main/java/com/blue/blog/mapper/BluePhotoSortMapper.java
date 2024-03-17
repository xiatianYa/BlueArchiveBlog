package com.blue.blog.mapper;

import com.blue.blog.domain.BluePhotoSort;

import java.util.List;

/**
 * 相册分类信息Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-17
 */
public interface BluePhotoSortMapper {
    /**
     * 查询相册分类信息
     *
     * @param id 相册分类信息主键
     * @return 相册分类信息
     */
    public BluePhotoSort selectBluePhotoSortById(Long id);

    /**
     * 查询相册分类信息列表
     *
     * @param bluePhotoSort 相册分类信息
     * @return 相册分类信息集合
     */
    public List<BluePhotoSort> selectBluePhotoSortList(BluePhotoSort bluePhotoSort);

    /**
     * 新增相册分类信息
     *
     * @param bluePhotoSort 相册分类信息
     * @return 结果
     */
    public int insertBluePhotoSort(BluePhotoSort bluePhotoSort);

    /**
     * 修改相册分类信息
     *
     * @param bluePhotoSort 相册分类信息
     * @return 结果
     */
    public int updateBluePhotoSort(BluePhotoSort bluePhotoSort);

    /**
     * 删除相册分类信息
     *
     * @param id 相册分类信息主键
     * @return 结果
     */
    public int deleteBluePhotoSortById(Long id);

    /**
     * 批量删除相册分类信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBluePhotoSortByIds(Long[] ids);
}
