package com.blue.elastic.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ElasticSearchConfig{
    @Value("${spring.elasticsearch.uris}")
    private String uris;
    @Bean("restClientBuilder")
    public RestClientBuilder elasticsearchClient() {
        String[] split = uris.split(":");
        String hostname= split[0];
        int port = Integer.parseInt(split[1]);
        // 基本的用户名密码认证
        BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(hostname, port, "http"))
                .setRequestConfigCallback(requestConfigBuilder ->requestConfigBuilder
                        .setConnectTimeout(10000)
                        .setSocketTimeout(60000));
        restClientBuilder.setHttpClientConfigCallback(httpAsyncClientBuilder ->
                httpAsyncClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider));
        return restClientBuilder;
    }
    @Bean
    public ElasticsearchClient elasticsearchClient(RestClientBuilder restClientBuilder) {
        RestClient restClient = restClientBuilder.build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }
}
