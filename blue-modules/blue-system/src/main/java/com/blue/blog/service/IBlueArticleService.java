package com.blue.blog.service;

import com.blue.blog.domain.BlueArticle;
import com.blue.blog.domain.dto.BlueArticleSearchDTO;
import com.blue.blog.domain.vo.BlueArticleSearchVo;

import java.util.List;

/**
 * 文章Service接口
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
public interface IBlueArticleService 
{
    /**
     * 查询文章
     * 
     * @param id 文章主键
     * @return 文章
     */
    public BlueArticle selectBlueArticleById(Long id);

    /**
     * 查询文章列表
     * 
     * @param blueArticle 文章
     * @return 文章集合
     */
    public List<BlueArticle> selectBlueArticleList(BlueArticle blueArticle);

    /**
     * 新增文章
     * 
     * @param blueArticle 文章
     * @return 结果
     */
    public int insertBlueArticle(BlueArticle blueArticle);

    /**
     * 修改文章
     * 
     * @param blueArticle 文章
     * @return 结果
     */
    public int updateBlueArticle(BlueArticle blueArticle);

    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的文章主键集合
     * @return 结果
     */
    public int deleteBlueArticleByIds(Long[] ids);

    /**
     * 删除文章信息
     * 
     * @param id 文章主键
     * @return 结果
     */
    public int deleteBlueArticleById(Long id);

    List<BlueArticle> selectBlueArticleListByTagId(Long tagId);

    List<BlueArticle> selectBlueArticleListBySortId(Long sortId);

    List<BlueArticle> selectBlueArticleListByUser();

    List<BlueArticle> listBySortId(Long sortId);

    int auditing(BlueArticle blueArticle);

    BlueArticleSearchDTO search(BlueArticleSearchVo blueArticleSearchVo);
}
