package com.blue.blog.service.impl;

import com.blue.blog.domain.BluePixivTv;
import com.blue.blog.mapper.BluePixivTvMapper;
import com.blue.blog.service.IBluePixivTvService;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private BluePixivTvMapper bluePixivTvMapper;

    /**
     * 查询番剧信息
     * 
     * @param id 番剧信息主键
     * @return 番剧信息
     */
    @Override
    public BluePixivTv selectBluePixivTvById(Long id)
    {
        return bluePixivTvMapper.selectBluePixivTvById(id);
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
        return bluePixivTvMapper.selectBluePixivTvList(bluePixivTv);
    }

    /**
     * 新增番剧信息
     * 
     * @param bluePixivTv 番剧信息
     * @return 结果
     */
    @Override
    public int insertBluePixivTv(BluePixivTv bluePixivTv)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            bluePixivTv.setCreateBy(String.valueOf(userId));
        }
        //设置审核状态
        if (!StringUtils.isNotNull(bluePixivTv.getStatus())){
            bluePixivTv.setStatus(AuditingStatus.OK.getCode());
        }
        //设置播放数
        bluePixivTv.setPixivPlay(0L);
        bluePixivTv.setCreateTime(DateUtils.getNowDate());
        return bluePixivTvMapper.insertBluePixivTv(bluePixivTv);
    }

    /**
     * 修改番剧信息
     * 
     * @param bluePixivTv 番剧信息
     * @return 结果
     */
    @Override
    public int updateBluePixivTv(BluePixivTv bluePixivTv)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            bluePixivTv.setUpdateBy(String.valueOf(userId));
        }
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
    public int deleteBluePixivTvByIds(Long[] ids)
    {
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
