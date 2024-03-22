package com.blue.blog.mapper;

import com.blue.blog.domain.BlueFriend;

import java.util.List;

/**
 * 友情链接Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-22
 */
public interface BlueFriendMapper 
{
    /**
     * 查询友情链接
     * 
     * @param id 友情链接主键
     * @return 友情链接
     */
    public BlueFriend selectBlueFriendById(Long id);

    /**
     * 查询友情链接列表
     * 
     * @param blueFriend 友情链接
     * @return 友情链接集合
     */
    public List<BlueFriend> selectBlueFriendList(BlueFriend blueFriend);

    /**
     * 新增友情链接
     * 
     * @param blueFriend 友情链接
     * @return 结果
     */
    public int insertBlueFriend(BlueFriend blueFriend);

    /**
     * 修改友情链接
     * 
     * @param blueFriend 友情链接
     * @return 结果
     */
    public int updateBlueFriend(BlueFriend blueFriend);

    /**
     * 删除友情链接
     * 
     * @param id 友情链接主键
     * @return 结果
     */
    public int deleteBlueFriendById(Long id);

    /**
     * 批量删除友情链接
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueFriendByIds(Long[] ids);
}
