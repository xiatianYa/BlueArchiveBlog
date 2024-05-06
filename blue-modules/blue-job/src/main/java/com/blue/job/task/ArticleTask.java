package com.blue.job.task;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.ElasticsearchIndicesClient;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.blue.common.core.constant.ElasticSearchConstants;
import com.blue.common.core.constant.SecurityConstants;
import com.blue.common.core.domain.R;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.common.core.utils.bean.BeanUtils;
import com.blue.job.domain.BlueArticle;
import com.blue.job.domain.BlueArticleDTO;
import com.blue.job.mapper.BlueArticleMapper;
import com.blue.system.api.RemoteUserService;
import com.blue.system.api.model.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务调度测试
 *
 * @author Summer
 */
@Component("ArticleTask")
public class ArticleTask {
    private static final Logger log = LoggerFactory.getLogger(ArticleTask.class);
    @Resource
    private BlueArticleMapper blueArticleMapper;
    @Resource
    private ElasticsearchClient client;
    @Resource
    private RemoteUserService remoteUserService;
    /**
     * 初始化ElasticSearch索引
     */
    public void initArticleTask() {
        //ElasticSearch客户端对象
        ElasticsearchIndicesClient indices = client.indices();
        //创建匹配条件
        LambdaQueryWrapper<BlueArticle> blueArticleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blueArticleLambdaQueryWrapper.eq(BlueArticle::getStatus, AuditingStatus.DISABLE.getCode());
        blueArticleLambdaQueryWrapper.eq(BlueArticle::getDelFlag,0);
        //所有匹配的文章列表
        List<BlueArticle> blueArticles = blueArticleMapper.selectList(blueArticleLambdaQueryWrapper);
        //文章DTO列表
        List<BlueArticleDTO> blueArticleDTOS=new ArrayList<>(blueArticles.size());
        blueArticles.forEach(item->{
            BlueArticleDTO blueArticleDTO=new BlueArticleDTO();
            BeanUtils.copyProperties(item,blueArticleDTO);
            R<UserVo> userInfoById = remoteUserService.getUserInfoById(item.getUserId(), SecurityConstants.INNER);
            if (R.isSuccess(userInfoById)) {
                UserVo userVo = userInfoById.getData();
                blueArticleDTO.setUserName(userVo.getUserNickName());
            }
            blueArticleDTOS.add(blueArticleDTO);
        });
        //初始话ElasticSearch索引数据
        //1.删除索引
        try {
            indices.delete(req -> req.index(ElasticSearchConstants.ArticleIndex));
        } catch (IOException e) {
            log.error("索引删除失败...");
        }
        //2.创建索引
        try {
            indices.create(req -> req.index(ElasticSearchConstants.ArticleIndex));
        } catch (IOException e) {
            log.error("索引创建失败...");
        }
        //3.添加索引文档
        blueArticleDTOS.forEach(item->{
            try {
                client.create(req -> req
                        .index(ElasticSearchConstants.ArticleIndex)
                        .id(item.getId().toString())
                        .document(item)
                );
            } catch (IOException e) {
                log.error("文档创建失败:{}", item.toString());
            }
        });
        log.info("初始化成功...");
    }
}
