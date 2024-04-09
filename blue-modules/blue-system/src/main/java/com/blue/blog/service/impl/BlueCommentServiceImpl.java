package com.blue.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.domain.BlueComment;
import com.blue.blog.mapper.BlueCommentMapper;
import com.blue.blog.service.IBlueCommentService;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.system.api.domain.SysUser;
import com.blue.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private SysUserMapper sysUserMapper;

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
        LambdaQueryWrapper<BlueComment> parentWrapper = new LambdaQueryWrapper<>();
        //匹配为父节点的数据
        parentWrapper.eq(BlueComment::getParentId,0);
        parentWrapper.eq(BlueComment::getCommentType,blueComment.getCommentType());
        if (StringUtils.isNotNull(blueComment.getCommonId())){
            parentWrapper.eq(BlueComment::getCommonId,blueComment.getCommonId());
        }
        List<BlueComment> blueComments = blueCommentMapper.selectList(parentWrapper);
        List<SysUser> sysUsers = sysUserMapper.selectUserList(new SysUser());
        //添加参数
        for (BlueComment comment : blueComments) {
            for (SysUser sysUser : sysUsers) {
                if (sysUser.getUserId().equals(Long.valueOf(comment.getCreateBy()))){
                    //设置用户名称
                    comment.setUserName(sysUser.getUserName());
                    //设置用户头像
                    comment.setUserAvatar(sysUser.getAvatar());
                }
            }
        }
        //遍历父节点 获取父节点下所有的子节点回复
        LambdaQueryWrapper<BlueComment> chileWrapper = new LambdaQueryWrapper<>();
        //匹配为父节点的数据
        chileWrapper.ne(BlueComment::getParentId,0);
        //所有子节点
        List<BlueComment> blueCommentsChile = blueCommentMapper.selectList(chileWrapper);
        for (BlueComment comment : blueComments) {
            comment.setReplyList(new ArrayList<>());
            for (BlueComment commentChile : blueCommentsChile) {
                //判断评论是不是以@开头 是则设置回复@用户 从@截取到:
                if (commentChile.getCommentContent().startsWith("@")){
                    String[] split = commentChile.getCommentContent().split(":");
                    commentChile.setAtUserName(split[0].substring(1));
                    commentChile.setCommentContent(commentChile.getCommentContent().substring(commentChile.getCommentContent().indexOf(":")+1));
                }
                if (commentChile.getParentId().equals(comment.getId())){
                    //为子留言添加用户姓名 用户头像
                    for (SysUser sysUser : sysUsers) {
                        if (sysUser.getUserId().equals(Long.valueOf(commentChile.getCreateBy()))){
                            //设置用户名称
                            commentChile.setUserName(sysUser.getUserName());
                            //设置用户头像
                            commentChile.setUserAvatar(sysUser.getAvatar());
                        }
                    }
                    comment.getReplyList().add(commentChile);
                }
            }
        }
        return blueComments;
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
        Long userId = SecurityUtils.getUserId();
        //设置用户ID
        if (StringUtils.isNotNull(userId)){
            blueComment.setCreateBy(userId.toString());
        }
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
        Long userId = SecurityUtils.getUserId();
        //设置用户ID
        if (StringUtils.isNotNull(userId)){
            blueComment.setUpdateBy(userId.toString());
        }
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
        LambdaQueryWrapper<BlueComment> wrapper=new LambdaQueryWrapper<>();
        wrapper.in(BlueComment::getParentId,ids);
        blueCommentMapper.delete(wrapper);
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
        LambdaQueryWrapper<BlueComment> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(BlueComment::getParentId,id);
        blueCommentMapper.delete(wrapper);
        return blueCommentMapper.deleteBlueCommentById(id);
    }
}
