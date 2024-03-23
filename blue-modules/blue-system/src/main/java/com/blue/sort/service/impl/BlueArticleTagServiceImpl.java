package com.blue.sort.service.impl;

import com.blue.blog.domain.BlueArticle;
import com.blue.blog.mapper.BlueArticleMapper;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BlueArticleTag;
import com.blue.sort.domain.BlueSortTag;
import com.blue.sort.mapper.BlueArticleTagMapper;
import com.blue.sort.mapper.BlueSortTagMapper;
import com.blue.sort.service.IBlueArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章标签关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@Service
public class BlueArticleTagServiceImpl implements IBlueArticleTagService 
{
    @Autowired
    private BlueArticleTagMapper blueArticleTagMapper;
    @Autowired
    private BlueArticleMapper blueArticleMapper;
    @Autowired
    private BlueSortTagMapper blueSortTagMapper;

    /**
     * 查询文章标签关联
     * 
     * @param id 文章标签关联主键
     * @return 文章标签关联
     */
    @Override
    public BlueArticleTag selectBlueArticleTagById(Long id)
    {
        return blueArticleTagMapper.selectBlueArticleTagById(id);
    }

    /**
     * 查询文章标签关联列表
     * 
     * @param blueArticleTag 文章标签关联
     * @return 文章标签关联
     */
    @Override
    public List<BlueArticleTag> selectBlueArticleTagList(BlueArticleTag blueArticleTag)
    {
        //文章标签列表
        List<BlueArticleTag> blueArticleTags = blueArticleTagMapper.selectBlueArticleTagList(blueArticleTag);
        //文章列表
        List<BlueArticle> blueArticles = blueArticleMapper.selectBlueArticleList(new BlueArticle());
        //文章标签列表
        List<BlueSortTag> blueSortTags = blueSortTagMapper.selectBlueSortTagList(new BlueSortTag());
        for (BlueArticleTag articleTag : blueArticleTags) {
            //设置当前文章标签文章名称
            for (BlueArticle blueArticle : blueArticles) {
                if (blueArticle.getId().equals(articleTag.getArticleId())){
                    articleTag.setArticleName(blueArticle.getName());
                }
            }
            //设置当前文章标签标签名称
            for (BlueSortTag blueSortTag : blueSortTags) {
                if (blueSortTag.getId().equals(articleTag.getTagId())){
                    articleTag.setTagName(blueSortTag.getTagName());
                }
            }
        }
        return blueArticleTags;
    }

    /**
     * 新增文章标签关联
     * 
     * @param blueArticleTag 文章标签关联
     * @return 结果
     */
    @Override
    public int insertBlueArticleTag(BlueArticleTag blueArticleTag)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueArticleTag.setCreateBy(userId.toString());
        }
        blueArticleTag.setCreateTime(DateUtils.getNowDate());
        return blueArticleTagMapper.insertBlueArticleTag(blueArticleTag);
    }

    /**
     * 修改文章标签关联
     * 
     * @param blueArticleTag 文章标签关联
     * @return 结果
     */
    @Override
    public int updateBlueArticleTag(BlueArticleTag blueArticleTag)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueArticleTag.setUpdateBy(userId.toString());
        }
        blueArticleTag.setUpdateTime(DateUtils.getNowDate());
        return blueArticleTagMapper.updateBlueArticleTag(blueArticleTag);
    }

    /**
     * 批量删除文章标签关联
     * 
     * @param ids 需要删除的文章标签关联主键
     * @return 结果
     */
    @Override
    public int deleteBlueArticleTagByIds(Long[] ids)
    {
        return blueArticleTagMapper.deleteBlueArticleTagByIds(ids);
    }

    /**
     * 删除文章标签关联信息
     * 
     * @param id 文章标签关联主键
     * @return 结果
     */
    @Override
    public int deleteBlueArticleTagById(Long id)
    {
        return blueArticleTagMapper.deleteBlueArticleTagById(id);
    }
}
