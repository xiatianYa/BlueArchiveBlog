package com.blue.elastic.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.Alias;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import com.blue.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/es")
public class ElasticSearchController {
    @Autowired
    private ElasticsearchClient elasticsearchClient;
    @PostMapping("/addIndex")
    public AjaxResult addIndex() throws IOException {
        CreateIndexResponse createIndexResponse = elasticsearchClient.indices().create(
                new CreateIndexRequest.Builder()
                        .index("product_info01")
                        .aliases("product_info_alias01",
                                new Alias.Builder().isWriteIndex(true).build()
                        ).build());
        if(createIndexResponse.acknowledged()){
            return AjaxResult.success("创建索引成功！");
        } else {
            return AjaxResult.success("创建索引失败！");
        }
    }
}
