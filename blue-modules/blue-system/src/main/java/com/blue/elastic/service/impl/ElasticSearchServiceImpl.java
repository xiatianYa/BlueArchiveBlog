package com.blue.elastic.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.UpdateRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.ElasticsearchIndicesClient;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.domain.BlueArticle;
import com.blue.blog.mapper.BlueArticleMapper;
import com.blue.blog.service.IBlueArticleService;
import com.blue.common.core.constant.ElasticSearchConstants;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.elastic.dao.BlueArticleDAO;
import com.blue.elastic.service.ElasticSearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


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
            //判断索引是否操作
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

    /**
     * 创建修改文章文档
     */
    @Override
    public boolean createOrUpdateArticleDocument(String indexName, String id, BlueArticle blueArticle) {
        try {
            BlueArticleDAO blueArticleDAO = new BlueArticleDAO();
            BeanUtils.copyProperties(blueArticle,blueArticleDAO);
            boolean exists = client.exists(req -> req.index(indexName).id(blueArticleDAO.getId().toString())).value();
            if (exists){
                client.update(req-> req
                                .index(indexName)
                                .id(id)
                                .doc(blueArticleDAO),
                        BlueArticleDAO.class);
            }else{
                client.create(req -> req
                                .index(indexName)
                                .id(id)
                                .document(blueArticleDAO)
                );
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 删除文章文档
     */
    @Override
    public boolean deleteArticleDocument(String indexName, String id) {
        try {
            client.delete(req -> req
                    .index(indexName)
                    .id(id));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
