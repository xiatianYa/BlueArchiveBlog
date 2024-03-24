package com.blue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.blog.domain.BlueAvater;

import java.util.List;

/**
 * 用户头像列存储Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public interface BlueAvaterMapper extends BaseMapper<BlueAvater>
{
    /**
     * 查询用户头像列存储
     * 
     * @param id 用户头像列存储主键
     * @return 用户头像列存储
     */
    public BlueAvater selectBlueAvaterById(Long id);

    /**
     * 查询用户头像列存储列表
     * 
     * @param blueAvater 用户头像列存储
     * @return 用户头像列存储集合
     */
    public List<BlueAvater> selectBlueAvaterList(BlueAvater blueAvater);

    /**
     * 新增用户头像列存储
     * 
     * @param blueAvater 用户头像列存储
     * @return 结果
     */
    public int insertBlueAvater(BlueAvater blueAvater);

    /**
     * 修改用户头像列存储
     * 
     * @param blueAvater 用户头像列存储
     * @return 结果
     */
    public int updateBlueAvater(BlueAvater blueAvater);

    /**
     * 删除用户头像列存储
     * 
     * @param id 用户头像列存储主键
     * @return 结果
     */
    public int deleteBlueAvaterById(Long id);

    /**
     * 批量删除用户头像列存储
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueAvaterByIds(Long[] ids);
}
