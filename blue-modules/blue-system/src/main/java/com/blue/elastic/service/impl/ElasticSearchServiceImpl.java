package com.blue.elastic.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.ElasticsearchIndicesClient;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.blue.elastic.service.ElasticSearchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {
    @Resource
    private ElasticsearchClient client;

    /**
     * 创建索引
     */
    @Override
    public boolean createIndex(String indexName) {
        //客户端对象
        ElasticsearchIndicesClient indices = client.indices();
        try {
            //判断索引是否存在
            boolean flag = indices.exists(req -> req.index(indexName)).value();
            if (flag){
                //索引存在
                return true;
            }else{
                //索引不存在
                CreateIndexResponse response = indices.create(req -> req.index(indexName));
                return response.acknowledged();
            }
        }catch (Exception e){
            //索引创建失败
            return false;
        }
    }

    /**
     * 获取索引
     */
    @Override
    public GetIndexResponse getIndex(String indexName) {
        //客户端对象
        ElasticsearchIndicesClient indices = client.indices();
        try {
            //获取索引Response
            GetIndexResponse getIndexResponse = indices.get(req -> req.index(indexName));
            return getIndexResponse;
        }catch (Exception e){
            //获取索引失败
            return null;
        }
    }

    /**
     * 删除索引
     */
    @Override
    public boolean deleteIndex(String indexName) {
        //客户端对象
        ElasticsearchIndicesClient indices = client.indices();
        try {
            //删除索引
            return indices.delete(req -> req.index(indexName)).acknowledged();
        }catch (Exception e){
            //删除失败
            return false;
        }
    }
}
