package com.blue.sort.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.domain.BlueArticle;
import com.blue.blog.mapper.BlueArticleMapper;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BlueSort;
import com.blue.sort.domain.BlueSortTag;
import com.blue.sort.mapper.BlueSortMapper;
import com.blue.sort.mapper.BlueSortTagMapper;
import com.blue.sort.service.IBlueSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Service
public class BlueSortServiceImpl implements IBlueSortService 
{
    @Resource
    private BlueSortMapper blueSortMapper;
    @Resource
    private BlueArticleMapper blueArticleMapper;
    @Resource
    private BlueSortTagMapper blueSortTagMapper;

    /**
     * 查询分类
     * 
     * @param id 分类主键
     * @return 分类
     */
    @Override
    public BlueSort selectBlueSortById(Long id)
    {
        return blueSortMapper.selectBlueSortById(id);
    }

    /**
     * 查询分类列表
     * 
     * @param blueSort 分类
     * @return 分类
     */
    @Override
    public List<BlueSort> selectBlueSortList(BlueSort blueSort)
    {
        //获取分类列表
        List<BlueSort> blueSorts = blueSortMapper.selectBlueSortList(blueSort);
        //获取文章列表
        List<BlueArticle> blueArticles = blueArticleMapper.selectList(new LambdaQueryWrapper<>());
        //获取所有标签列表
        List<BlueSortTag> blueSortTags = blueSortTagMapper.selectList(new LambdaQueryWrapper<>());
        //获取分类下文章数量
        for (BlueSort sort : blueSorts) {
            //初始化数量
            sort.setSortNumber(0);
            sort.setTagList(new ArrayList<>());
            for (BlueArticle blueArticle : blueArticles) {
                //分类ID相同
                if (blueArticle.getSortId().equals(sort.getId()) && blueArticle.getStatus().equals(AuditingStatus.DISABLE.getCode())){
                    sort.setSortNumber(sort.getSortNumber()+1);
                }
            }
            for (BlueSortTag blueSortTag : blueSortTags) {
                if(blueSortTag.getSortId().equals(sort.getId())){
                    sort.getTagList().add(blueSortTag);
                }
            }
        }
        return blueSorts;
    }

    /**
     * 新增分类
     * 
     * @param blueSort 分类
     * @return 结果
     */
    @Override
    public int insertBlueSort(BlueSort blueSort)
    {
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            blueSort.setCreateBy(userId.toString());
        }
        blueSort.setCreateTime(DateUtils.getNowDate());
        return blueSortMapper.insertBlueSort(blueSort);
    }

    /**
     * 修改分类
     * 
     * @param blueSort 分类
     * @return 结果
     */
    @Override
    public int updateBlueSort(BlueSort blueSort)
    {
        Long userId = SecurityUtils.getUserId();
        if(StringUtils.isNotNull(userId)){
            blueSort.setUpdateBy(userId.toString());
        }
        blueSort.setUpdateTime(DateUtils.getNowDate());
        return blueSortMapper.updateBlueSort(blueSort);
    }

    /**
     * 批量删除分类
     * 
     * @param ids 需要删除的分类主键
     * @return 结果
     */
    @Override
    public int deleteBlueSortByIds(Long[] ids)
    {
        return blueSortMapper.deleteBlueSortByIds(ids);
    }

    /**
     * 删除分类信息
     * 
     * @param id 分类主键
     * @return 结果
     */
    @Override
    public int deleteBlueSortById(Long id)
    {
        return blueSortMapper.deleteBlueSortById(id);
    }
}
