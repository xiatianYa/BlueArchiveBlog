package com.blue.blog.service.impl;

import com.blue.blog.domain.BlueComment;
import com.blue.blog.mapper.BlueCommentMapper;
import com.blue.blog.service.IBlueCommentService;
import com.blue.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@Service
public class BlueCommentServiceImpl implements IBlueCommentService 
{
    @Autowired
    private BlueCommentMapper blueCommentMapper;

    /**
     * 查询评论
     * 
     * @param id 评论主键
     * @return 评论
     */
    @Override
    public BlueComment selectBlueCommentById(Long id)
    {
        return blueCommentMapper.selectBlueCommentById(id);
    }

    /**
     * 查询评论列表
     * 
     * @param blueComment 评论
     * @return 评论
     */
    @Override
    public List<BlueComment> selectBlueCommentList(BlueComment blueComment)
    {
        return blueCommentMapper.selectBlueCommentList(blueComment);
    }

    /**
     * 新增评论
     * 
     * @param blueComment 评论
     * @return 结果
     */
    @Override
    public int insertBlueComment(BlueComment blueComment)
    {
        blueComment.setCreateTime(DateUtils.getNowDate());
        return blueCommentMapper.insertBlueComment(blueComment);
    }

    /**
     * 修改评论
     * 
     * @param blueComment 评论
     * @return 结果
     */
    @Override
    public int updateBlueComment(BlueComment blueComment)
    {
        blueComment.setUpdateTime(DateUtils.getNowDate());
        return blueCommentMapper.updateBlueComment(blueComment);
    }

    /**
     * 批量删除评论
     * 
     * @param ids 需要删除的评论主键
     * @return 结果
     */
    @Override
    public int deleteBlueCommentByIds(Long[] ids)
    {
        return blueCommentMapper.deleteBlueCommentByIds(ids);
    }

    /**
     * 删除评论信息
     * 
     * @param id 评论主键
     * @return 结果
     */
    @Override
    public int deleteBlueCommentById(Long id)
    {
        return blueCommentMapper.deleteBlueCommentById(id);
    }
}
