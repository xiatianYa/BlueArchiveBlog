package com.blue.elastic.service;

import co.elastic.clients.elasticsearch.indices.GetIndexResponse;

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
}
