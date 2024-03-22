package com.blue.blog.service.impl;

import com.blue.blog.domain.BlueNotice;
import com.blue.blog.mapper.BlueNoticeMapper;
import com.blue.blog.service.IBlueNoticeService;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@Service
public class BlueNoticeServiceImpl implements IBlueNoticeService 
{
    @Autowired
    private BlueNoticeMapper blueNoticeMapper;

    /**
     * 查询公告信息
     * 
     * @param id 公告信息主键
     * @return 公告信息
     */
    @Override
    public BlueNotice selectBlueNoticeById(Long id)
    {
        return blueNoticeMapper.selectBlueNoticeById(id);
    }

    /**
     * 查询公告信息列表
     * 
     * @param blueNotice 公告信息
     * @return 公告信息
     */
    @Override
    public List<BlueNotice> selectBlueNoticeList(BlueNotice blueNotice)
    {
        return blueNoticeMapper.selectBlueNoticeList(blueNotice);
    }

    /**
     * 新增公告信息
     * 
     * @param blueNotice 公告信息
     * @return 结果
     */
    @Override
    public int insertBlueNotice(BlueNotice blueNotice)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueNotice.setCreateBy(String.valueOf(userId));
        }
        blueNotice.setCreateTime(DateUtils.getNowDate());
        return blueNoticeMapper.insertBlueNotice(blueNotice);
    }

    /**
     * 修改公告信息
     * 
     * @param blueNotice 公告信息
     * @return 结果
     */
    @Override
    public int updateBlueNotice(BlueNotice blueNotice)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueNotice.setUpdateBy(String.valueOf(userId));
        }
        blueNotice.setUpdateTime(DateUtils.getNowDate());
        return blueNoticeMapper.updateBlueNotice(blueNotice);
    }

    /**
     * 批量删除公告信息
     * 
     * @param ids 需要删除的公告信息主键
     * @return 结果
     */
    @Override
    public int deleteBlueNoticeByIds(Long[] ids)
    {
        return blueNoticeMapper.deleteBlueNoticeByIds(ids);
    }

    /**
     * 删除公告信息信息
     * 
     * @param id 公告信息主键
     * @return 结果
     */
    @Override
    public int deleteBlueNoticeById(Long id)
    {
        return blueNoticeMapper.deleteBlueNoticeById(id);
    }
}
