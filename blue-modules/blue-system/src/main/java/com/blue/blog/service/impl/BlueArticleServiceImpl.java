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
import com.blue.sort.mapper.BlueArticleTagMapper;
import com.blue.sort.mapper.BlueSortMapper;
import com.blue.system.api.domain.SysUser;
import com.blue.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 查询文章
     * 
     * @param id 文章主键
     * @return 文章
     */
    @Override
    public BlueArticle selectBlueArticleById(Long id)
    {
        return blueArticleMapper.selectBlueArticleById(id);
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
    public int insertBlueArticle(BlueArticle blueArticle)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueArticle.setCreateBy(userId.toString());
            blueArticle.setUserId(userId);
            blueArticle.setStatus(AuditingStatus.OK.getCode());
        }
        blueArticle.setCreateTime(DateUtils.getNowDate());
        return blueArticleMapper.insertBlueArticle(blueArticle);
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
        blueArticle.setUpdateTime(DateUtils.getNowDate());
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

    @Override
    public List<BlueArticle> selectBlueArticleListByTagId(Long tagId) {
        LambdaQueryWrapper<BlueArticleTag> blueArticleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isNotNull(tagId)){
            throw new ServiceException("标签ID为空");
        }
        blueArticleTagLambdaQueryWrapper.eq(BlueArticleTag::getTagId,tagId);
        //通过标签ID获取文章标签列表
        List<BlueArticleTag> blueArticleTags = blueArticleTagMapper.selectList(blueArticleTagLambdaQueryWrapper);
        //获取全部文章列表
        List<BlueArticle> blueArticles = blueArticleMapper.selectList(new LambdaQueryWrapper<>());
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
}
