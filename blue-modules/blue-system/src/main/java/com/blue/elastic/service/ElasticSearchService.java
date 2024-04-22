package com.blue.elastic.service;

import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.blue.blog.domain.BlueArticle;
import com.blue.blog.domain.vo.BlueArticleSearchVo;
import com.blue.elastic.dao.BlueArticleDAO;

public interface ElasticSearchService {
    /**
     * 创建索引
     */
    boolean createIndex(String indexName);
    /**
     * 查询索引
     */
    GetIndexResponse getIndex(String indexName);
    /**
     * 删除索引
     */
    boolean deleteIndex(String indexName);
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
    HitsMetadata<BlueArticleDAO> searchArticleDocument(BlueArticleSearchVo searchVo, String indexName);
}
