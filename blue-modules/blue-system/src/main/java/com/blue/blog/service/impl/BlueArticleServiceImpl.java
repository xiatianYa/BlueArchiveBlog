package com.blue.blog.service.impl;

import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.domain.BlueArticle;
import com.blue.blog.domain.dto.BlueArticleSearchDTO;
import com.blue.blog.domain.vo.BlueArticleSearchVo;
import com.blue.blog.mapper.BlueArticleMapper;
import com.blue.blog.service.IBlueArticleService;
import com.blue.common.core.constant.ElasticSearchConstants;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.common.core.exception.ServiceException;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.elastic.dao.BlueArticleDAO;
import com.blue.elastic.service.ElasticSearchService;
import com.blue.sort.domain.BlueArticleTag;
import com.blue.sort.domain.BlueSort;
import com.blue.sort.domain.BlueSortTag;
import com.blue.sort.mapper.BlueArticleTagMapper;
import com.blue.sort.mapper.BlueSortMapper;
import com.blue.sort.mapper.BlueSortTagMapper;
import com.blue.system.api.domain.SysUser;
import com.blue.system.api.model.LoginUser;
import com.blue.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    private SysUserMapper userMapper;
    @Resource
    private BlueArticleTagMapper blueArticleTagMapper;
    @Resource
    private BlueSortTagMapper blueSortTagMapper;
    @Resource
    private ElasticSearchService elasticSearchService;

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
        List<BlueSortTag> blueSortTags = blueSortTagMapper.selectList(new LambdaQueryWrapper<>());
        //为关联标签列表赋值
        for (BlueArticleTag blueArticleTag : blueArticleTags) {
            for (BlueSortTag blueSortTag : blueSortTags) {
                if (blueArticleTag.getTagId().equals(blueSortTag.getId())){
                    blueArticleTag.setTagName(blueSortTag.getTagName());
                    blueArticleTag.setArticleName(blueArticle.getArticleName());
                }
            }
        }
        blueArticle.setTagList(blueArticleTags);
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
        //分类列表
        List<BlueSort> blueSorts = blueSortMapper.selectList(new LambdaQueryWrapper<>());
        //用户列表
        List<SysUser> sysUsers = userMapper.selectUserList(new SysUser());
        for (BlueArticle article : blueArticles) {
            for (BlueSort blueSort : blueSorts) {
                //判断分类ID是否一致
                if (blueSort.getId().equals(article.getSortId())){
                    article.setSortName(blueSort.getSortName());
                }
            }
            for (SysUser sysUser : sysUsers) {
                //判断用户id是否一致
                if (sysUser.getUserId().equals(article.getUserId())){
                    article.setUserName(sysUser.getUserName());
                }
            }
        }
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
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            //设置创建者ID
            blueArticle.setCreateBy(userId.toString());
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
            //设置创建人ID
            blueArticleTag.setCreateBy(String.valueOf(userId));
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
    public int updateBlueArticle(BlueArticle blueArticle)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueArticle.setUpdateBy(userId.toString());
        }
        blueArticle.setUpdateTime(DateUtils.getNowDate());
        //然后向文章标签表里插入标签
        if (StringUtils.isNotNull(blueArticle.getTagList())){
            //文章的标签列表
            List<BlueArticleTag> tagList = blueArticle.getTagList();
            for (BlueArticleTag blueArticleTag : tagList) {
                //默认先删除所有和文章匹配的标签数据
                LambdaQueryWrapper<BlueArticleTag> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(BlueArticleTag::getArticleId,blueArticle.getId());
                blueArticleTagMapper.delete(wrapper);
                //设置文章ID
                blueArticleTag.setArticleId(blueArticle.getId());
                //设置创建人ID
                blueArticleTag.setCreateBy(String.valueOf(userId));
                //设置修改人ID
                blueArticleTag.setUpdateBy(String.valueOf(userId));
                //然后重新插入新的数据
                blueArticleTagMapper.insertBlueArticleTag(blueArticleTag);
            }
        }
        //修改文章文档
        elasticSearchService.createOrUpdateArticleDocument(ElasticSearchConstants.ArticleIndex,String.valueOf(blueArticle.getId()),blueArticle);
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
        //同时批量删除文字标签关联表数据
        LambdaQueryWrapper<BlueArticleTag> blueArticleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blueArticleTagLambdaQueryWrapper.in(BlueArticleTag::getArticleId,ids);
        blueArticleTagMapper.delete(blueArticleTagLambdaQueryWrapper);;
        //批量删除文档
        for (Long id : ids) {
            //删除文档
            elasticSearchService.deleteArticleDocument(ElasticSearchConstants.ArticleIndex,String.valueOf(id));
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
        //同时批量删除文字标签关联表数据
        LambdaQueryWrapper<BlueArticleTag> blueArticleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blueArticleTagLambdaQueryWrapper.eq(BlueArticleTag::getArticleId,id);
        blueArticleTagMapper.delete(blueArticleTagLambdaQueryWrapper);
        //删除文档
        elasticSearchService.deleteArticleDocument(ElasticSearchConstants.ArticleIndex,String.valueOf(id));
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
        blueArticleTagLambdaQueryWrapper.eq(BlueArticleTag::getTagId,tagId);
        //通过标签ID获取文章标签列表
        List<BlueArticleTag> blueArticleTags = blueArticleTagMapper.selectList(blueArticleTagLambdaQueryWrapper);
        //返回列表
        List<BlueArticle> blueArticleList=new ArrayList<>();
        LambdaQueryWrapper<BlueArticle> wrapper = new LambdaQueryWrapper<>();
        //默认查询已通过审核数据
        wrapper.eq(BlueArticle::getStatus,AuditingStatus.DISABLE.getCode());
        //获取全部文章列表
        List<BlueArticle> blueArticles = blueArticleMapper.selectList(wrapper);
        for (BlueArticleTag blueArticleTag : blueArticleTags) {
            //通过文章标签中文章ID获取文章
            for (BlueArticle blueArticle : blueArticles) {
                //命中
                if (blueArticle.getId().equals(blueArticleTag.getArticleId())){
                    blueArticleList.add(blueArticle);
                }
            }
        }
        return blueArticleList;
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
        //查询改文章下所包含的标签
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
        return blueArticleMapper.selectList(wrapper);
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
        //文章列表
        List<BlueArticle> blueArticles = blueArticleMapper.selectList(wrapper);
        //标签分类列表
        List<BlueSortTag> blueSortTags = blueSortTagMapper.selectList(new LambdaQueryWrapper<>());
        //用户列表
        List<SysUser> sysUsers = userMapper.selectUserList(new SysUser());
        //查询出文章列表下全部的标签
        for (BlueArticle blueArticle : blueArticles) {
            LambdaQueryWrapper<BlueArticleTag> blueArticleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
            blueArticleTagLambdaQueryWrapper.eq(BlueArticleTag::getArticleId,blueArticle.getId());
            //查询文章下包含的标签列表
            List<BlueArticleTag> blueArticleTags = blueArticleTagMapper.selectList(blueArticleTagLambdaQueryWrapper);
            //设置每个标签下 所有的标签分类列表
            for (BlueArticleTag blueArticleTag : blueArticleTags) {
                for (BlueSortTag blueSortTag : blueSortTags) {
                    if (blueArticleTag.getTagId().equals(blueSortTag.getId())){
                        blueArticleTag.setTagName(blueSortTag.getTagName());
                    }
                }
            }
            //设置每个文章的作者名称
            for (SysUser sysUser : sysUsers) {
                if (blueArticle.getUserId().equals(sysUser.getUserId())){
                    blueArticle.setUserName(sysUser.getNickName());
                }
            }
            blueArticle.setTagList(blueArticleTags);
        }
        return blueArticles;
    }

    /**
     * 文章审核
     *
     * @param blueArticle 文章对象
     * @return 结果
     */
    @Override
    public int auditing(BlueArticle blueArticle) {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueArticle.setUpdateBy(userId.toString());
        }
        //设置用户名称
        SysUser sysUser = userMapper.selectUserById(blueArticle.getUserId());
        blueArticle.setUserName(sysUser.getNickName());
        //设置修改时间
        blueArticle.setUpdateTime(DateUtils.getNowDate());
        //通过审核
        if (blueArticle.getStatus().equals(AuditingStatus.DISABLE.getCode())){
            //创建ElasticSearch文档
            elasticSearchService.createOrUpdateArticleDocument(ElasticSearchConstants.ArticleIndex, String.valueOf(blueArticle.getId()),blueArticle);
        }else{
            //未通过审核
            elasticSearchService.deleteArticleDocument(ElasticSearchConstants.ArticleIndex, String.valueOf(blueArticle.getId()));
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
        HitsMetadata<BlueArticleDAO> blueArticleDAOHitsMetadata =
                elasticSearchService.searchArticleDocument(blueArticleSearchVo, ElasticSearchConstants.ArticleIndex);
        //封装返回对象
        BlueArticleSearchDTO blueArticleSearchDTO = new BlueArticleSearchDTO();
        if (StringUtils.isNotNull(blueArticleDAOHitsMetadata)){
            blueArticleSearchDTO.setBlueArticleList(new ArrayList<>());
            blueArticleSearchDTO.setTotal(Objects.requireNonNull(blueArticleDAOHitsMetadata.total()).value());
            for (Hit<BlueArticleDAO> hit : blueArticleDAOHitsMetadata.hits()) {
                BlueArticleDAO source = hit.source();
                blueArticleSearchDTO.getBlueArticleList().add(source);
            }
            return blueArticleSearchDTO;
        }else{
            return null;
        }
    }

    public void isCheckArticle(BlueArticle blueArticle){
        if (!StringUtils.isNotEmpty(blueArticle.getArticleName())){
            throw new ServiceException("文章标题为空...");
        }
        if (!StringUtils.isNotEmpty(blueArticle.getArticleDescribe())){
            throw new ServiceException("文章描述为空...");
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
}
