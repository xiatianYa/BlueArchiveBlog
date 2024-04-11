package com.blue.blog.service;

import com.blue.blog.domain.BlueComment;

import java.util.List;

/**
 * 评论Service接口
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
public interface IBlueCommentService 
{
    /**
     * 查询评论
     * 
     * @param id 评论主键
     * @return 评论
     */
    public BlueComment selectBlueCommentById(Long id);

    /**
     * 查询评论列表
     * 
     * @param blueComment 评论
     * @return 评论集合
     */
    public List<BlueComment> selectBlueCommentList(BlueComment blueComment);

    /**
     * 新增评论
     * 
     * @param blueComment 评论
     * @return 结果
     */
    public int insertBlueComment(BlueComment blueComment);

    /**
     * 修改评论
     * 
     * @param blueComment 评论
     * @return 结果
     */
    public int updateBlueComment(BlueComment blueComment);

    /**
     * 批量删除评论
     * 
     * @param ids 需要删除的评论主键集合
     * @return 结果
     */
    public int deleteBlueCommentByIds(Long[] ids);

    /**
     * 删除评论信息
     * 
     * @param id 评论主键
     * @return 结果
     */
    public int deleteBlueCommentById(Long id);

    List<BlueComment> selectBlueCommentListAll(BlueComment blueComment);
}
