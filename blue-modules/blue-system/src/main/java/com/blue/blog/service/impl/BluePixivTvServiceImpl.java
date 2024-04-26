package com.blue.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.entry.dao.BluePixivEpisode;
import com.blue.blog.entry.dao.BluePixivLeaveMessage;
import com.blue.blog.entry.dao.BluePixivTv;
import com.blue.blog.mapper.BluePixivEpisodeMapper;
import com.blue.blog.mapper.BluePixivLeaveMessageMapper;
import com.blue.blog.mapper.BluePixivTvMapper;
import com.blue.blog.service.IBluePixivTvService;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BluePixivType;
import com.blue.sort.mapper.BluePixivTypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 番剧信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-01
 */
@Service
public class BluePixivTvServiceImpl implements IBluePixivTvService 
{
    @Resource
    private BluePixivTvMapper bluePixivTvMapper;
    @Resource
    private BluePixivTypeMapper bluePixivTypeService;
    @Resource
    private BluePixivEpisodeMapper bluePixivEpisodeMapper;
    @Resource
    private BluePixivLeaveMessageMapper bluePixivLeaveMessageMapper;

    /**
     * 查询番剧信息
     * 
     * @param id 番剧信息主键
     * @return 番剧信息
     */
    @Override
    public BluePixivTv selectBluePixivTvById(Long id)
    {
        BluePixivTv bluePixivTv = bluePixivTvMapper.selectBluePixivTvById(id);
        //番剧分类列表
        List<BluePixivType> bluePixivTypes = bluePixivTypeService.selectList(new LambdaQueryWrapper<>());
        for (BluePixivType bluePixivType : bluePixivTypes) {
            if (bluePixivTv.getPixivType().equals(bluePixivType.getId())){
                bluePixivTv.setTypeName(bluePixivType.getTypeName());
            }
        }
        return bluePixivTv;
    }

    /**
     * 查询番剧信息列表
     * 
     * @param bluePixivTv 番剧信息
     * @return 番剧信息
     */
    @Override
    public List<BluePixivTv> selectBluePixivTvList(BluePixivTv bluePixivTv)
    {
        //判断查询状态是不是为空 默认为已通过
        if (!StringUtils.isNotNull(bluePixivTv.getStatus())){
            bluePixivTv.setStatus(AuditingStatus.DISABLE.getCode());
        }
        //番剧列表
        List<BluePixivTv> bluePixivTvs = bluePixivTvMapper.selectBluePixivTvList(bluePixivTv);
        //番剧分类列表
        List<BluePixivType> bluePixivTypes = bluePixivTypeService.selectList(new LambdaQueryWrapper<>());
        for (BluePixivType bluePixivType : bluePixivTypes) {
            for (BluePixivTv pixivTv : bluePixivTvs) {
                //匹配 设置番剧分类名称
                if (pixivTv.getPixivType().equals(bluePixivType.getId())){
                    pixivTv.setTypeName(bluePixivType.getTypeName());
                }
            }
        }
        return bluePixivTvs;
    }

    /**
     * 新增番剧信息
     * 
     * @param bluePixivTv 番剧信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertBluePixivTv(BluePixivTv bluePixivTv)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            bluePixivTv.setCreateBy(String.valueOf(userId));
        }
        //设置审核状态 默认为未通过
        bluePixivTv.setStatus(AuditingStatus.OK.getCode());
        //设置播放数
        bluePixivTv.setPixivPlay(0L);
        //设置创建时间
        bluePixivTv.setCreateTime(DateUtils.getNowDate());
        //设置创建者
        bluePixivTv.setCreateBy(String.valueOf(userId));
        //如果是新增 就先插入值 获取返回ID
        return bluePixivTvMapper.insertBluePixivTv(bluePixivTv);
    }

    /**
     * 修改番剧信息
     * 
     * @param bluePixivTv 番剧信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateBluePixivTv(BluePixivTv bluePixivTv)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            //设置修改时间
            bluePixivTv.setUpdateBy(String.valueOf(userId));
        }
        //设置修改时间
        bluePixivTv.setUpdateTime(DateUtils.getNowDate());
        return bluePixivTvMapper.updateBluePixivTv(bluePixivTv);
    }

    /**
     * 批量删除番剧信息
     * 
     * @param ids 需要删除的番剧信息主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteBluePixivTvByIds(Long[] ids)
    {
        //同时删除番剧集数据
        LambdaQueryWrapper<BluePixivEpisode> episodeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        episodeLambdaQueryWrapper.in(BluePixivEpisode::getPixivId,ids);
        bluePixivEpisodeMapper.delete(episodeLambdaQueryWrapper);
        //同时删除番剧评论数据
        LambdaQueryWrapper<BluePixivLeaveMessage> leaveMessageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        leaveMessageLambdaQueryWrapper.in(BluePixivLeaveMessage::getPixivId,ids);
        bluePixivLeaveMessageMapper.delete(leaveMessageLambdaQueryWrapper);
        return bluePixivTvMapper.deleteBluePixivTvByIds(ids);
    }

    /**
     * 删除番剧信息信息
     * 
     * @param id 番剧信息主键
     * @return 结果
     */
    @Override
    public int deleteBluePixivTvById(Long id)
    {
        return bluePixivTvMapper.deleteBluePixivTvById(id);
    }
}
