package com.blue.blog.service;

import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.blue.blog.entry.dao.BlueArticle;
import com.blue.blog.entry.dto.BlueArticleDTO;
import com.blue.blog.entry.dto.BlueArticleSearchDTO;
import com.blue.blog.entry.vo.BlueArticleBySortVo;
import com.blue.blog.entry.vo.BlueArticleSearchVo;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public int insertBlueArticle(BlueArticle blueArticle);

    /**
     * 修改文章
     * 
     * @param blueArticle 文章
     * @return 结果
     */
    @Transactional
    public int updateBlueArticle(BlueArticle blueArticle);

    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的文章主键集合
     * @return 结果
     */
    @Transactional
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
    /**
     * 搜索文章根据用户
     */

    List<BlueArticle> selectBlueArticleListByUser();

    /**
     * 搜索文章根据分类ID
     */
    List<BlueArticle> listBySortId(Long sortId);
    /**
     * 文章审核
     */
    @Transactional
    int auditing(BlueArticle blueArticle);
    /**
     * 搜索文章列表
     */
    BlueArticleSearchDTO search(BlueArticleSearchVo blueArticleSearchVo);
    /**
     * 添加文章文档
     */
    boolean createOrUpdateArticleDocument(String indexName, String id, BlueArticle blueArticle);

    /**
     * 删除文章文档
     */
    boolean deleteArticleDocument(String indexName, String id);
    /**
     * 搜索文章文档
     */
    HitsMetadata<BlueArticleDTO> searchArticleDocument(BlueArticleSearchVo searchVo, String indexName);
    /**
     * 文章点赞
     */
    @Transactional
    String addLike(Long id);
    /**
     * 获取首页每个分类展示文章列表
     */
    List<BlueArticleBySortVo> listByHome();

    @Transactional
    void examine(Long[] ids);
}
