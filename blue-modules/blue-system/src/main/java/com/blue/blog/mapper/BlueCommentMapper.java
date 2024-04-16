package com.blue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.blog.domain.BlueComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 评论Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@Mapper
public interface BlueCommentMapper extends BaseMapper<BlueComment>
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
     * 删除评论
     * 
     * @param id 评论主键
     * @return 结果
     */
    public int deleteBlueCommentById(Long id);

    /**
     * 批量删除评论
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueCommentByIds(Long[] ids);
}
