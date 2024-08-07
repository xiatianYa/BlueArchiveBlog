package com.blue.blog.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.entry.dao.BlueArticle;
import com.blue.blog.entry.dao.BlueArticleInformation;
import com.blue.blog.entry.dao.BlueComment;
import com.blue.blog.entry.dto.BlueArticleDTO;
import com.blue.blog.entry.dto.BlueArticleSearchDTO;
import com.blue.blog.entry.vo.BlueArticleBySortVo;
import com.blue.blog.entry.vo.BlueArticleSearchVo;
import com.blue.blog.mapper.BlueArticleInformationMapper;
import com.blue.blog.mapper.BlueArticleMapper;
import com.blue.blog.mapper.BlueCommentMapper;
import com.blue.blog.service.IBlueArticleService;
import com.blue.common.core.constant.ElasticSearchConstants;
import com.blue.common.core.constant.SecurityConstants;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.common.core.exception.ServiceException;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BlueArticleTag;
import com.blue.sort.domain.BlueSort;
import com.blue.sort.domain.BlueSortTag;
import com.blue.sort.mapper.BlueArticleTagMapper;
import com.blue.sort.mapper.BlueSortMapper;
import com.blue.sort.mapper.BlueSortTagMapper;
import com.blue.system.api.RemoteUserService;
import com.blue.system.api.model.LoginUser;
import com.blue.system.api.model.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 文章Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@Service
public class BlueArticleServiceImpl implements IBlueArticleService 
{
    @Resource
    private BlueArticleMapper blueArticleMapper;
    @Resource
    private BlueSortMapper blueSortMapper;
    @Resource
    private BlueArticleTagMapper blueArticleTagMapper;
    @Resource
    private BlueSortTagMapper blueSortTagMapper;
    @Resource
    private ElasticsearchClient client;
    @Resource
    private BlueArticleInformationMapper blueArticleInformationMapper;
    @Resource
    private BlueCommentMapper blueCommentMapper;
    @Resource
    private RemoteUserService remoteUserService;

    /**
     * 查询文章
     * 
     * @param id 文章主键
     * @return 文章
     */
    @Override
    public BlueArticle selectBlueArticleById(Long id)
    {
        //文章列表
        BlueArticle blueArticle = blueArticleMapper.selectBlueArticleById(id);
        LambdaQueryWrapper<BlueArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlueArticleTag::getArticleId,blueArticle.getId());
        //文章关联标签
        List<BlueArticleTag> blueArticleTags = blueArticleTagMapper.selectList(wrapper);
        //标签列表
        Map<Long, String> sortTags =
                blueSortTagMapper.selectList(new LambdaQueryWrapper<>()).stream()
                        .collect(Collectors.toMap(BlueSortTag::getId, BlueSortTag::getTagName));
        //为关联标签列表赋值
        for (BlueArticleTag blueArticleTag : blueArticleTags) {
            blueArticleTag.setTagName(sortTags.get(blueArticleTag.getTagId()));
            blueArticleTag.setArticleName(blueArticle.getArticleName());
        }
        //查询作者名称
        UserVo sysUser =
                remoteUserService.getUserInfoById(blueArticle.getUserId(), SecurityConstants.FROM_SOURCE).getData();
        //设置用户名称
        if (StringUtils.isNotNull(sysUser)){
            blueArticle.setUserName(sysUser.getUserNickName());
        }else{
            blueArticle.setUserName("未知用户");
        }
        //设置标签列表
        blueArticle.setTagList(blueArticleTags);

        //给文章添加浏览量
        if (StringUtils.isNotNull(SecurityUtils.getLoginUser())){
            Long userid = SecurityUtils.getLoginUser().getUserid();
            //设置匹配条件
            LambdaQueryWrapper<BlueArticleInformation> informationLambdaQueryWrapper = new LambdaQueryWrapper<>();
            informationLambdaQueryWrapper.eq(BlueArticleInformation::getArticleId,blueArticle.getId());
            informationLambdaQueryWrapper.eq(BlueArticleInformation::getUserId,userid);
            //获取匹配数据
            BlueArticleInformation blueArticleInformation = blueArticleInformationMapper.selectOne(informationLambdaQueryWrapper);
            if (StringUtils.isNull(blueArticleInformation)){
                blueArticleInformation=new BlueArticleInformation();
                blueArticleInformation.setArticleId(blueArticle.getId());
                blueArticleInformation.setUserId(userid);
                //设置状态已浏览
                blueArticleInformation.setIsBrowse("1");
                //设置是否点赞 默认未点赞
                blueArticleInformation.setIsLike("0");
                blueArticleInformationMapper.insertBlueArticleInformation(blueArticleInformation);
            }
        }
        //获取统计数据
        initArticleCount(blueArticle);
        return blueArticle;
    }

    /**
     * 查询文章列表
     * 
     * @param blueArticle 文章
     * @return 文章
     */
    @Override
    public List<BlueArticle> selectBlueArticleList(BlueArticle blueArticle)
    {
        //文章列表
        if (!StringUtils.isNotNull(blueArticle.getStatus())){
            blueArticle.setStatus(AuditingStatus.DISABLE.getCode());
        }
        List<BlueArticle> blueArticles = blueArticleMapper.selectBlueArticleList(blueArticle);
        //设置分类名称
        setSortName(blueArticles);
        //设置用户名称
        setUserName(blueArticles);
        return blueArticles;
    }

    /**
     * 新增文章
     * 
     * @param blueArticle 文章
     * @return 结果
     */
    @Override
    @Transactional
    public int insertBlueArticle(BlueArticle blueArticle)
    {
        isCheckArticle(blueArticle);
        Long userId = SecurityUtils.getLoginUser().getUserid();
        if (StringUtils.isNotNull(userId)){
            //设置用户ID
            blueArticle.setUserId(userId);
        }
        //设置删除状态
        blueArticle.setDelFlag(0);
        //设置审核状态
        blueArticle.setStatus(AuditingStatus.OK.getCode());
        //设置创建时间
        blueArticle.setCreateTime(DateUtils.getNowDate());
        //先插入文章信息
        int num = blueArticleMapper.insert(blueArticle);
        //然后向文章标签表里插入标签
        List<BlueArticleTag> tagList = blueArticle.getTagList();
        for (BlueArticleTag blueArticleTag : tagList) {
            //设置文章ID
            blueArticleTag.setArticleId(blueArticle.getId());
            //向文章标签列表插入数据
            blueArticleTagMapper.insertBlueArticleTag(blueArticleTag);
        }
        return num;
    }

    /**
     * 修改文章
     * 
     * @param blueArticle 文章
     * @return 结果
     */
    @Override
    @Transactional
    public int updateBlueArticle(BlueArticle blueArticle)
    {
        //判断用户操作权限
        isCheckUser(blueArticle.getId());
        Long userId = SecurityUtils.getLoginUser().getUserid();
        if (StringUtils.isNotNull(userId)){
            blueArticle.setUpdateBy(userId.toString());
        }
        //如果文章已审核,则不能修改
        if (AuditingStatus.DISABLE.getCode().equals(blueArticle.getStatus())){
            throw new ServiceException("文章已审核,禁止修改,请联系管理员!");
        }
        //设置文章修改时间
        blueArticle.setUpdateTime(DateUtils.getNowDate());
        //文章的标签列表
        List<BlueArticleTag> tagList = blueArticle.getTagList();
        //只对文章内容做修改
        if (StringUtils.isNull(tagList)){
            return blueArticleMapper.updateBlueArticle(blueArticle);
        }
        //默认先删除所有和文章匹配的标签数据
        LambdaQueryWrapper<BlueArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(BlueArticleTag::getArticleId,blueArticle.getId());
        blueArticleTagMapper.delete(wrapper);
        for (BlueArticleTag blueArticleTag : tagList) {
            //设置文章ID
            blueArticleTag.setArticleId(blueArticle.getId());
            //然后重新插入新的数据
            blueArticleTagMapper.insertBlueArticleTag(blueArticleTag);
        }
        //修改文章文档
        createOrUpdateArticleDocument(ElasticSearchConstants.ArticleIndex,String.valueOf(blueArticle.getId()),blueArticle);
        return blueArticleMapper.updateBlueArticle(blueArticle);
    }

    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的文章主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteBlueArticleByIds(Long[] ids)
    {
        //判断用户操作权限
        for (Long id : ids) {
            isCheckUser(id);
        }
        //同时批量删除文字标签关联表数据
        LambdaQueryWrapper<BlueArticleTag> blueArticleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blueArticleTagLambdaQueryWrapper.in(BlueArticleTag::getArticleId,ids);
        blueArticleTagMapper.delete(blueArticleTagLambdaQueryWrapper);;
        //批量删除文档
        for (Long id : ids) {
            //删除文档
            deleteArticleDocument(ElasticSearchConstants.ArticleIndex,String.valueOf(id));
        }
        return blueArticleMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除文章信息
     * 
     * @param id 文章主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteBlueArticleById(Long id)
    {
        //判断用户操作权限
        isCheckUser(id);
        //同时批量删除文字标签关联表数据
        LambdaQueryWrapper<BlueArticleTag> blueArticleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blueArticleTagLambdaQueryWrapper.eq(BlueArticleTag::getArticleId,id);
        blueArticleTagMapper.delete(blueArticleTagLambdaQueryWrapper);
        //删除文档
        deleteArticleDocument(ElasticSearchConstants.ArticleIndex,String.valueOf(id));
        return blueArticleMapper.deleteById(id);
    }

    /**
     * 根据标签ID获取文章信息
     *
     * @param tagId 标签主键
     * @return 结果
     */
    @Override
    public List<BlueArticle> selectBlueArticleListByTagId(Long tagId) {
        LambdaQueryWrapper<BlueArticleTag> blueArticleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isNotNull(tagId)){
            throw new ServiceException("标签ID为空");
        }
        //通过标签ID获取匹配的文章ID列表
        blueArticleTagLambdaQueryWrapper.eq(BlueArticleTag::getTagId,tagId);
        List<Long> articleIdList = blueArticleTagMapper.selectList(blueArticleTagLambdaQueryWrapper).
                stream().map(BlueArticleTag::getArticleId).collect(Collectors.toList());
        if (!StringUtils.isNotEmpty(articleIdList)){
            return new ArrayList<>();
        }
        //获取全部文章列表
        LambdaQueryWrapper<BlueArticle> wrapper = new LambdaQueryWrapper<>();
        //默认查询已通过审核数据
        wrapper.eq(BlueArticle::getStatus,AuditingStatus.DISABLE.getCode());
        wrapper.in(BlueArticle::getId,articleIdList);

        //设置统计数
        List<BlueArticle> blueArticles = blueArticleMapper.selectList(wrapper);
        blueArticles.forEach(this::initArticleCount);
        return blueArticles;
    }

    /**
     * 根据分类ID获取文章信息
     *
     * @param sortId 标签主键
     * @return 结果
     */
    @Override
    public List<BlueArticle> selectBlueArticleListBySortId(Long sortId) {
        LambdaQueryWrapper<BlueArticle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlueArticle::getSortId,sortId);
        //默认查询已通过审核数据
        wrapper.eq(BlueArticle::getStatus,AuditingStatus.DISABLE.getCode());
        //查询该文章下所包含的标签
        return blueArticleMapper.selectList(wrapper);
    }

    /**
     * 根据用户获取文章信息
     * @return 结果
     */
    @Override
    public List<BlueArticle> selectBlueArticleListByUser() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (!StringUtils.isNotNull(loginUser.getUserid())){
            throw new ServiceException("用户信息获取失败!");
        }
        LambdaQueryWrapper<BlueArticle> wrapper = new LambdaQueryWrapper<>();
        //查询用户发布的所有文章
        wrapper.eq(BlueArticle::getUserId,loginUser.getUserid());
        List<BlueArticle> blueArticles = blueArticleMapper.selectList(wrapper);
        //设置文章下标签列表
        setTagName(blueArticles);
        return blueArticles;
    }

    /**
     * 根据分类ID获取文章信息列表
     *
     * @param sortId 标签主键
     * @return 结果
     */
    @Override
    public List<BlueArticle> listBySortId(Long sortId) {
        LambdaQueryWrapper<BlueArticle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlueArticle::getSortId,sortId);
        wrapper.eq(BlueArticle::getStatus,AuditingStatus.DISABLE.getCode());
        //文章列表
        List<BlueArticle> blueArticles = blueArticleMapper.selectList(wrapper);
        //文章文章标签列表
        setTagName(blueArticles);
        //设置文章用户名称
        setUserName(blueArticles);
        //设置文章统计数量
        blueArticles.forEach(this::initArticleCount);
        return blueArticles;
    }

    /**
     * 文章审核
     *
     * @param blueArticle 文章对象
     * @return 结果
     */
    @Override
    @Transactional
    public int auditing(BlueArticle blueArticle) {
        //设置用户名称
        UserVo sysUser =
                remoteUserService.getUserInfoById(blueArticle.getUserId(), SecurityConstants.FROM_SOURCE).getData();
        if (StringUtils.isNotNull(sysUser)){
            blueArticle.setUserName(sysUser.getUserNickName());
        }else{
            blueArticle.setUserName("未知用户");
        }
        //设置修改时间
        blueArticle.setUpdateTime(DateUtils.getNowDate());
        //通过审核
        if (blueArticle.getStatus().equals(AuditingStatus.DISABLE.getCode())){
            //创建ElasticSearch文档
            createOrUpdateArticleDocument(ElasticSearchConstants.ArticleIndex, String.valueOf(blueArticle.getId()),blueArticle);
        }else{
            //未通过审核
            deleteArticleDocument(ElasticSearchConstants.ArticleIndex, String.valueOf(blueArticle.getId()));
        }
        return blueArticleMapper.updateBlueArticle(blueArticle);
    }

    /**
     * 通过ElasticSearch查询文章列表
     *
     * @param blueArticleSearchVo 搜索对象
     * @return 结果
     */
    @Override
    public BlueArticleSearchDTO search(BlueArticleSearchVo blueArticleSearchVo) {
        //查询回来的hits
        HitsMetadata<BlueArticleDTO> blueArticleDAOHitsMetadata =
                searchArticleDocument(blueArticleSearchVo, ElasticSearchConstants.ArticleIndex);
        //封装返回对象
        BlueArticleSearchDTO blueArticleSearchDTO = new BlueArticleSearchDTO();
        if (StringUtils.isNotNull(blueArticleDAOHitsMetadata)){
            blueArticleSearchDTO.setBlueArticleList(new ArrayList<>());
            blueArticleSearchDTO.setTotal(Objects.requireNonNull(blueArticleDAOHitsMetadata.total()).value());
            for (Hit<BlueArticleDTO> hit : blueArticleDAOHitsMetadata.hits()) {
                BlueArticleDTO source = hit.source();
                if (StringUtils.isNull(source)){
                    continue;
                }
                //高亮显示
                Map<String, List<String>> highlight = hit.highlight();
                if (StringUtils.isNotNull(highlight.get("articleName"))){
                    source.setArticleName(highlight.get("articleName").get(0));
                }
                if (StringUtils.isNotNull(highlight.get("articleDescribe"))){
                    source.setArticleDescribe(highlight.get("articleDescribe").get(0));
                }
                if (StringUtils.isNotNull(highlight.get("userName"))){
                    source.setUserName(highlight.get("userName").get(0));
                }
                //设置文章 点赞数 评论数 浏览量
                Long hotCount = blueArticleInformationMapper.selectCount(new LambdaQueryWrapper<BlueArticleInformation>()
                        .eq(BlueArticleInformation::getArticleId, source.getId()));
                Long commentCount = blueCommentMapper.selectCount(new LambdaQueryWrapper<BlueComment>()
                        .eq(BlueComment::getCommentType,"2").eq(BlueComment::getCommonId, source.getId()));
                Long likeCount = blueArticleInformationMapper.selectCount(new LambdaQueryWrapper<BlueArticleInformation>()
                        .eq(BlueArticleInformation::getArticleId, source.getId()).eq(BlueArticleInformation::getIsLike,"1"));
                source.setHot(hotCount.intValue());
                source.setComment(commentCount.intValue());
                source.setLike(likeCount.intValue());
                blueArticleSearchDTO.getBlueArticleList().add(source);
            }
            return blueArticleSearchDTO;
        }else{
            return null;
        }
    }
    /**
     * 创建修改文章文档
     */
    @Override
    public boolean createOrUpdateArticleDocument(String indexName, String id, BlueArticle blueArticle) {
        try {
            BlueArticleDTO blueArticleDTO = new BlueArticleDTO();
            BeanUtils.copyProperties(blueArticle, blueArticleDTO);
            boolean exists = client.exists(req -> req.index(indexName).id(id)).value();
            if (exists){
                client.update(req-> req
                                .index(indexName)
                                .id(id)
                                .doc(blueArticleDTO),
                        BlueArticleDTO.class);
            }else{
                client.create(req -> req
                        .index(indexName)
                        .id(id)
                        .document(blueArticleDTO)
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
    /**
     * 查询文章文档带分页
     */
    @Override
    public HitsMetadata<BlueArticleDTO> searchArticleDocument(BlueArticleSearchVo searchVo, String indexName) {
        try {
            return client.search(
                    req -> {req
                            .index(indexName)
                            //查询匹配字段 满足其中一条就匹配
                            .query(query->query
                                    .bool(bool->bool
                                            .should(should->should
                                                    .match(match->match
                                                            .field("articleName").query(searchVo.getSearchValue())
                                                    )
                                            )
                                            .should(should->should
                                                    .match(match->match
                                                            .field("articleDescribe").query(searchVo.getSearchValue())
                                                    )
                                            ).should(should->should
                                                    .match(match->match
                                                            .field("userName").query(searchVo.getSearchValue())
                                                    )
                                            )
                                    )
                            )
                            //匹配字段高亮显示
                            .highlight(high->high
                                    .fields("articleName",field->field.preTags("<span style='color:#fc5531'>")
                                            .postTags("</span>"))
                                    .fields("articleDescribe",field->field.preTags("<span style='color:#fc5531'>")
                                            .postTags("</span>"))
                                    .fields("userName",field->field.preTags("<span style='color:#fc5531'>")
                                            .postTags("</span>"))
                            )
                            .from(searchVo.getPageNum())
                            .size(searchVo.getPageSize())
                    ;
                        return req;
                    },
                    BlueArticleDTO.class
            ).hits();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 给文章点赞
     */
    @Override
    @Transactional
    public String addLike(Long id) {
        Long userid = SecurityUtils.getLoginUser().getUserid();
        //设置匹配条件
        LambdaQueryWrapper<BlueArticleInformation> informationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        informationLambdaQueryWrapper.eq(BlueArticleInformation::getArticleId,id);
        informationLambdaQueryWrapper.eq(BlueArticleInformation::getUserId,userid);
        //获取匹配数据
        BlueArticleInformation blueArticleInformation = blueArticleInformationMapper.selectOne(informationLambdaQueryWrapper);
        if (StringUtils.isNull(blueArticleInformation)){
            blueArticleInformation=new BlueArticleInformation();
            blueArticleInformation.setArticleId(id);
            blueArticleInformation.setUserId(userid);
            //设置状态已浏览
            blueArticleInformation.setIsBrowse("1");
            //设置是否点赞 默认点赞
            blueArticleInformation.setIsLike("1");
            blueArticleInformationMapper.insertBlueArticleInformation(blueArticleInformation);
            return "点赞成功";
        }else{
            if (blueArticleInformation.getIsLike().equals("1")){
                blueArticleInformation.setIsLike("0");
                blueArticleInformationMapper.updateBlueArticleInformation(blueArticleInformation);
                return "取消点赞";
            }else{
                blueArticleInformation.setIsLike("1");
                blueArticleInformationMapper.updateBlueArticleInformation(blueArticleInformation);
                return "点赞成功";
            }
        }
    }
    /**
     * 获取首页每个分类展示文章列表
     */
    @Override
    public List<BlueArticleBySortVo> listByHome() {
        //获取所有分类
        List<BlueSort> blueSortList = blueSortMapper.selectList(new LambdaQueryWrapper<>());
        //返回列表
        List<BlueArticleBySortVo> blueArticleBySortVo = new ArrayList<>();
        //查询分类下的文章 每个分类只查询6个
        blueSortList.forEach(blueSort -> {
            BlueArticleBySortVo bySortVo = new BlueArticleBySortVo();
            LambdaQueryWrapper<BlueArticle> wrapper= new LambdaQueryWrapper<>();
            //配置查询文章状态为审核通过
            wrapper.eq(BlueArticle::getStatus,AuditingStatus.DISABLE.getCode());
            wrapper.eq(BlueArticle::getSortId,blueSort.getId());
            //查询条件为最近的记录排序
            wrapper.orderByDesc(BlueArticle::getCreateTime);
            //分页 只查询前六条
            wrapper.last("limit 6");
            List<BlueArticle> blueArticles = blueArticleMapper.selectList(wrapper);
            //文章文章标签列表
            setTagName(blueArticles);
            //设置文章用户名称
            setUserName(blueArticles);
            //设置文章统计数量
            blueArticles.forEach(this::initArticleCount);
            //数据拷贝
            bySortVo.setSortId(blueSort.getId());
            bySortVo.setBlueArticleList(blueArticles);
            blueArticleBySortVo.add(bySortVo);
        });
        return blueArticleBySortVo;
    }

    @Override
    public void examine(Long[] ids) {
        if (StringUtils.isNull(ids) || ids.length == 0) throw new ServiceException("审核列表为空");
        for (Long id : ids) {
            BlueArticle article = isCheckUser(id);
            if (article.getStatus().equals(AuditingStatus.OK.getCode()) || article.getStatus().equals(AuditingStatus.DELETED.getCode())){
                article.setStatus(AuditingStatus.WAIT.getCode());
            }
            blueArticleMapper.updateById(article);
        }
    }

    /**
     * 为文章列表设置分类名称
     */
    public void setSortName(List<BlueArticle> blueArticleList){
        //初始化列表
        Map<Long, String> sortName =
                blueSortMapper.selectList(new LambdaQueryWrapper<>()).stream().collect(Collectors.toMap(BlueSort::getId, BlueSort::getSortName));
        //设置每个标签下 所有的标签分类列表
        for (BlueArticle blueArticle : blueArticleList) {
            blueArticle.setSortName(sortName.get(blueArticle.getSortId()));
        }
    }
    /**
     * 为文章列表设置标签列表
     */
    public void setTagName(List<BlueArticle> blueArticleList){
        //初始化列表
        //标签分类列表
        Map<Long, String> sortTags =
                blueSortTagMapper.selectList(new LambdaQueryWrapper<>()).stream().collect(Collectors.toMap(BlueSortTag::getId, BlueSortTag::getTagName));
        //查询文章下包含的标签列表
        List<BlueArticleTag> blueArticleTags = blueArticleTagMapper.selectList(new LambdaQueryWrapper<>());
        //设置每个标签下 所有的标签分类列表
        for (BlueArticle blueArticle : blueArticleList) {
            List<BlueArticleTag> tagList=new ArrayList<>();
            for (BlueArticleTag blueArticleTag : blueArticleTags) {
                if (blueArticle.getId().equals(blueArticleTag.getArticleId())){
                    //设置标签名称
                    blueArticleTag.setTagName(sortTags.get(blueArticleTag.getTagId()));
                    tagList.add(blueArticleTag);
                }
            }
            blueArticle.setTagList(tagList);
        }
    }
    /**
     * 为文章列表设置用户名称
     */
    public void setUserName(List<BlueArticle> blueArticleList){
        //用户列表
        for (BlueArticle blueArticle : blueArticleList) {
            //设置每个文章的作者名称
            UserVo userVo =
                    remoteUserService.getUserInfoById(blueArticle.getUserId(), SecurityConstants.FROM_SOURCE).getData();
            if (StringUtils.isNotNull(userVo)){
                blueArticle.setUserName(userVo.getUserNickName());
            }else{
                blueArticle.setUserName("未知用户");
            }
        }
    }

    /**
     * 为文章列表设置热度 评论数 点赞数
     */
    public void initArticleCount(BlueArticle blueArticle){
        //热度
        Long hotCount = blueArticleInformationMapper.selectCount(new LambdaQueryWrapper<BlueArticleInformation>()
                .eq(BlueArticleInformation::getArticleId, blueArticle.getId()));
        //评论数
        Long commentCount = blueCommentMapper.selectCount(new LambdaQueryWrapper<BlueComment>()
                .eq(BlueComment::getCommentType,"2").eq(BlueComment::getCommonId, blueArticle.getId()));
        //点赞数
        Long likeCount = blueArticleInformationMapper.selectCount(new LambdaQueryWrapper<BlueArticleInformation>()
                .eq(BlueArticleInformation::getArticleId, blueArticle.getId()).eq(BlueArticleInformation::getIsLike,"1"));
        blueArticle.setHot(hotCount.intValue());
        blueArticle.setComment(commentCount.intValue());
        blueArticle.setLike(likeCount.intValue());
    }

    /**
     * 检测字段是否合理
     */
    public void isCheckArticle(BlueArticle blueArticle){
        if (!StringUtils.isNotEmpty(blueArticle.getArticleName())){
            throw new ServiceException("文章标题为空...");
        }
        if (blueArticle.getArticleName().length()>40){
            throw new ServiceException("文章标题字数大于40");
        }
        if (!StringUtils.isNotEmpty(blueArticle.getArticleDescribe())){
            throw new ServiceException("文章描述为空...");
        }
        if (blueArticle.getArticleDescribe().length()>100){
            throw new ServiceException("文章描述字数大于100");
        }
        if (!StringUtils.isNotNull(blueArticle.getSortId())){
            throw new ServiceException("文章分类为空...");
        }
        if (!StringUtils.isNotEmpty(blueArticle.getTagList())){
            throw new ServiceException("文章标签为空...");
        }
        if (!StringUtils.isNotEmpty(blueArticle.getCover())){
            throw new ServiceException("文章封面为空...");
        }
    }
    /**
     * 检测用户操作是否合法
     */
    public BlueArticle isCheckUser(Long articleId){
        Long userId = SecurityUtils.getLoginUser().getUserid();
        BlueArticle blueArticle = blueArticleMapper.selectById(articleId);
        //管理员操作
        if(SecurityUtils.isAdmin(userId)){
            return blueArticle;
        }
        //非本人操作
        if (!userId.equals(blueArticle.getUserId())){
            throw new ServiceException("您没有权限操作该文章...");
        }
        return blueArticle;
    }
}
