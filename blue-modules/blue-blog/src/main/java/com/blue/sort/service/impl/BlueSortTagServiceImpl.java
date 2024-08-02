package com.blue.sort.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.entry.dao.BlueArticle;
import com.blue.blog.mapper.BlueArticleMapper;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BlueArticleTag;
import com.blue.sort.domain.BlueSortTag;
import com.blue.sort.mapper.BlueArticleTagMapper;
import com.blue.sort.mapper.BlueSortTagMapper;
import com.blue.sort.service.IBlueSortTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Service
public class BlueSortTagServiceImpl implements IBlueSortTagService 
{
    @Resource
    private BlueSortTagMapper blueSortTagMapper;
    @Resource
    private BlueArticleTagMapper blueArticleTagMapper;
    @Resource
    private BlueArticleMapper blueArticleMapper;

    /**
     * 查询标签
     * 
     * @param id 标签主键
     * @return 标签
     */
    @Override
    public BlueSortTag selectBlueSortTagById(Long id)
    {
        return blueSortTagMapper.selectBlueSortTagById(id);
    }

    /**
     * 查询标签列表
     * 
     * @param blueSortTag 标签
     * @return 标签
     */
    @Override
    public List<BlueSortTag> selectBlueSortTagList(BlueSortTag blueSortTag)
    {
        //所有标签列表
        List<BlueSortTag> blueSortTags = blueSortTagMapper.selectBlueSortTagList(blueSortTag);
        //所有文章标签列表
        List<BlueArticleTag> blueArticleTags = blueArticleTagMapper.selectList(new LambdaQueryWrapper<>());
        for (BlueSortTag sortTag : blueSortTags) {
            //初始化参数
            sortTag.setArticleTagNumber(0);
            //获取标签列表中 某个标签下有多少文章
            for (BlueArticleTag blueArticleTag : blueArticleTags) {
                //文章标签列表有匹配Id
                if (blueArticleTag.getTagId().equals(sortTag.getId())){
                    //并且文章要已通过审核
                    BlueArticle blueArticle = blueArticleMapper.selectById(blueArticleTag.getArticleId());
                    if (StringUtils.isNotNull(blueArticle) && blueArticle.getStatus().equals(AuditingStatus.DISABLE.getCode())){
                        sortTag.setArticleTagNumber(sortTag.getArticleTagNumber()+1);
                    }
                }
            }
        }
        return blueSortTags;
    }

    /**
     * 新增标签
     * 
     * @param blueSortTag 标签
     * @return 结果
     */
    @Override
    public int insertBlueSortTag(BlueSortTag blueSortTag)
    {
        Long userId = SecurityUtils.getLoginUser().getUserid();
        if(StringUtils.isNotNull(userId)){
            blueSortTag.setCreateBy(userId.toString());
        }
        blueSortTag.setCreateTime(DateUtils.getNowDate());
        return blueSortTagMapper.insertBlueSortTag(blueSortTag);
    }

    /**
     * 修改标签
     * 
     * @param blueSortTag 标签
     * @return 结果
     */
    @Override
    public int updateBlueSortTag(BlueSortTag blueSortTag)
    {
        Long userId = SecurityUtils.getLoginUser().getUserid();
        if(StringUtils.isNotNull(userId)){
            blueSortTag.setUpdateBy(userId.toString());
        }
        blueSortTag.setUpdateTime(DateUtils.getNowDate());
        return blueSortTagMapper.updateBlueSortTag(blueSortTag);
    }

    /**
     * 批量删除标签
     * 
     * @param ids 需要删除的标签主键
     * @return 结果
     */
    @Override
    public int deleteBlueSortTagByIds(Long[] ids)
    {
        return blueSortTagMapper.deleteBlueSortTagByIds(ids);
    }

    /**
     * 删除标签信息
     * 
     * @param id 标签主键
     * @return 结果
     */
    @Override
    public int deleteBlueSortTagById(Long id)
    {
        return blueSortTagMapper.deleteBlueSortTagById(id);
    }
}
