package com.blue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.blog.domain.BluePhoto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 相册Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Mapper
public interface BluePhotoMapper extends BaseMapper<BluePhoto>
{
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
     * 删除相册
     * 
     * @param id 相册主键
     * @return 结果
     */
    public int deleteBluePhotoById(Long id);

    /**
     * 批量删除相册
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBluePhotoByIds(Long[] ids);
}
