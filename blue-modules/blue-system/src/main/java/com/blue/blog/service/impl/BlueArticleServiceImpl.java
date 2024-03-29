package com.blue.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.domain.BlueArticle;
import com.blue.blog.mapper.BlueArticleMapper;
import com.blue.blog.service.IBlueArticleService;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.common.core.exception.ServiceException;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BlueArticleTag;
import com.blue.sort.domain.BlueSort;
import com.blue.sort.domain.BlueSortTag;
import com.blue.sort.mapper.BlueArticleTagMapper;
import com.blue.sort.mapper.BlueSortMapper;
import com.blue.sort.mapper.BlueSortTagMapper;
import com.blue.system.api.domain.SysUser;
import com.blue.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@Service
public class BlueArticleServiceImpl implements IBlueArticleService 
{
    @Autowired
    private BlueArticleMapper blueArticleMapper;
    @Autowired
    private BlueSortMapper blueSortMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private BlueArticleTagMapper blueArticleTagMapper;
    @Autowired
    private BlueSortTagMapper blueSortTagMapper;

    /**
     * 查询文章
     * 
     * @param id 文章主键
     * @return 文章
     */
    @Override
    public BlueArticle selectBlueArticleById(Long id)
    {
        //文章列表
        BlueArticle blueArticle = blueArticleMapper.selectBlueArticleById(id);
        LambdaQueryWrapper<BlueArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlueArticleTag::getArticleId,blueArticle.getId());
        //文章关联标签
        List<BlueArticleTag> blueArticleTags = blueArticleTagMapper.selectList(wrapper);
        //标签列表
        List<BlueSortTag> blueSortTags = blueSortTagMapper.selectList(new LambdaQueryWrapper<>());
        //为关联标签列表赋值
        for (BlueArticleTag blueArticleTag : blueArticleTags) {
            for (BlueSortTag blueSortTag : blueSortTags) {
                if (blueArticleTag.getTagId().equals(blueSortTag.getId())){
                    blueArticleTag.setTagName(blueSortTag.getTagName());
                    blueArticleTag.setArticleName(blueArticle.getArticleName());
                }
            }
        }
        blueArticle.setTagList(blueArticleTags);
        return blueArticle;
    }

    /**
     * 查询文章列表
     * 
     * @param blueArticle 文章
     * @return 文章
     */
    @Override
    public List<BlueArticle> selectBlueArticleList(BlueArticle blueArticle)
    {
        //文章列表
        List<BlueArticle> blueArticles = blueArticleMapper.selectBlueArticleList(blueArticle);
        //分类列表
        List<BlueSort> blueSorts = blueSortMapper.selectList(new LambdaQueryWrapper<>());
        //用户列表
        List<SysUser> sysUsers = userMapper.selectUserList(new SysUser());
        for (BlueArticle article : blueArticles) {
            for (BlueSort blueSort : blueSorts) {
                //判断分类ID是否一致
                if (blueSort.getId().equals(article.getSortId())){
                    article.setSortName(blueSort.getSortName());
                }
            }
            for (SysUser sysUser : sysUsers) {
                //判断用户id是否一致
                if (sysUser.getUserId().equals(article.getUserId())){
                    article.setUserName(sysUser.getUserName());
                }
            }
        }
        return blueArticles;
    }

    /**
     * 新增文章
     * 
     * @param blueArticle 文章
     * @return 结果
     */
    @Override
    @Transactional
    public int insertBlueArticle(BlueArticle blueArticle)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            //设置创建者ID
            blueArticle.setCreateBy(userId.toString());
            //设置用户ID
            blueArticle.setUserId(userId);
        }
        //设置审核状态
        blueArticle.setStatus(AuditingStatus.OK.getCode());
        //设置创建时间
        blueArticle.setCreateTime(DateUtils.getNowDate());
        //先插入文章信息
        int num = blueArticleMapper.insertBlueArticle(blueArticle);
        //然后向文章标签表里插入标签
        List<BlueArticleTag> tagList = blueArticle.getTagList();
        for (BlueArticleTag blueArticleTag : tagList) {
            //设置文章ID
            blueArticleTag.setArticleId(blueArticle.getId());
            //设置创建人ID
            blueArticleTag.setCreateBy(String.valueOf(userId));
            //向文章标签列表插入数据
            blueArticleTagMapper.insertBlueArticleTag(blueArticleTag);
        }
        return num;
    }

    /**
     * 修改文章
     * 
     * @param blueArticle 文章
     * @return 结果
     */
    @Override
    public int updateBlueArticle(BlueArticle blueArticle)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueArticle.setUpdateBy(userId.toString());
        }
        //然后向文章标签表里插入标签
        if (StringUtils.isNotNull(blueArticle.getTagList())){
            //文章的标签列表
            List<BlueArticleTag> tagList = blueArticle.getTagList();
            for (BlueArticleTag blueArticleTag : tagList) {
                //默认先删除所有和文章匹配的标签数据
                LambdaQueryWrapper<BlueArticleTag> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(BlueArticleTag::getArticleId,blueArticle.getId());
                wrapper.eq(BlueArticleTag::getTagId,blueArticleTag.getTagId());
                blueArticleTagMapper.delete(wrapper);
                //设置文章ID
                blueArticleTag.setArticleId(blueArticle.getId());
                //设置创建人ID
                blueArticleTag.setCreateBy(String.valueOf(userId));
                //设置修改人ID
                blueArticleTag.setUpdateBy(String.valueOf(userId));
                //然后重新插入新的数据
                blueArticleTagMapper.insertBlueArticleTag(blueArticleTag);
            }
        }
        return blueArticleMapper.updateBlueArticle(blueArticle);
    }

    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的文章主键
     * @return 结果
     */
    @Override
    public int deleteBlueArticleByIds(Long[] ids)
    {
        return blueArticleMapper.deleteBlueArticleByIds(ids);
    }

    /**
     * 删除文章信息
     * 
     * @param id 文章主键
     * @return 结果
     */
    @Override
    public int deleteBlueArticleById(Long id)
    {
        return blueArticleMapper.deleteBlueArticleById(id);
    }

    /**
     * 根据标签ID获取文章信息
     *
     * @param tagId 标签主键
     * @return 结果
     */
    @Override
    public List<BlueArticle> selectBlueArticleListByTagId(Long tagId) {
        LambdaQueryWrapper<BlueArticleTag> blueArticleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isNotNull(tagId)){
            throw new ServiceException("标签ID为空");
        }
        blueArticleTagLambdaQueryWrapper.eq(BlueArticleTag::getTagId,tagId);
        //通过标签ID获取文章标签列表
        List<BlueArticleTag> blueArticleTags = blueArticleTagMapper.selectList(blueArticleTagLambdaQueryWrapper);
        LambdaQueryWrapper<BlueArticle> wrapper = new LambdaQueryWrapper<>();
        //默认查询已通过审核数据
        wrapper.eq(BlueArticle::getStatus,AuditingStatus.DISABLE.getCode());
        //获取全部文章列表
        List<BlueArticle> blueArticles = blueArticleMapper.selectList(wrapper);
        //返回列表
        List<BlueArticle> blueArticleList=new ArrayList<>();
        for (BlueArticleTag blueArticleTag : blueArticleTags) {
            //通过文章标签中文章ID获取文章
            for (BlueArticle blueArticle : blueArticles) {
                //命中
                if (blueArticle.getId().equals(blueArticleTag.getArticleId())){
                    blueArticleList.add(blueArticle);
                }
            }
        }
        return blueArticleList;
    }

    @Override
    public List<BlueArticle> selectBlueArticleListBySortId(Long sortId) {
        LambdaQueryWrapper<BlueArticle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlueArticle::getSortId,sortId);
        //默认查询已通过审核数据
        wrapper.eq(BlueArticle::getStatus,AuditingStatus.DISABLE.getCode());
        //查询改文章下所包含的标签
        return blueArticleMapper.selectList(wrapper);
    }
}
