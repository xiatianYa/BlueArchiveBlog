package com.blue.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.domain.BlueErchuang;
import com.blue.blog.mapper.BlueErchuangMapper;
import com.blue.blog.service.IBlueErchuangService;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 二创信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-02
 */
@Service
public class BlueErchuangServiceImpl implements IBlueErchuangService 
{
    @Autowired
    private BlueErchuangMapper blueErchuangMapper;

    /**
     * 查询二创信息
     * 
     * @param id 二创信息主键
     * @return 二创信息
     */
    @Override
    public BlueErchuang selectBlueErchuangById(Long id)
    {
        return blueErchuangMapper.selectBlueErchuangById(id);
    }

    /**
     * 查询二创信息列表
     * 
     * @param blueErchuang 二创信息
     * @return 二创信息
     */
    @Override
    public List<BlueErchuang> selectBlueErchuangList(BlueErchuang blueErchuang)
    {
        //设置默认审核为已通过
        if (!StringUtils.isNotNull(blueErchuang.getStatus())){
            blueErchuang.setStatus(AuditingStatus.DISABLE.getCode());
        }
        return blueErchuangMapper.selectBlueErchuangList(blueErchuang);
    }

    /**
     * 新增二创信息
     * 
     * @param blueErchuang 二创信息
     * @return 结果
     */
    @Override
    public int insertBlueErchuang(BlueErchuang blueErchuang)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueErchuang.setCreateBy(String.valueOf(userId));
        }
        if (StringUtils.isNull(blueErchuang.getEcPublish())){
            blueErchuang.setEcPublish(DateUtils.getNowDate());
        }
        blueErchuang.setStatus(AuditingStatus.OK.getCode());
        blueErchuang.setCreateTime(DateUtils.getNowDate());
        return blueErchuangMapper.insertBlueErchuang(blueErchuang);
    }

    /**
     * 修改二创信息
     * 
     * @param blueErchuang 二创信息
     * @return 结果
     */
    @Override
    public int updateBlueErchuang(BlueErchuang blueErchuang)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueErchuang.setUpdateBy(String.valueOf(userId));
        }
        blueErchuang.setUpdateTime(DateUtils.getNowDate());
        return blueErchuangMapper.updateBlueErchuang(blueErchuang);
    }

    /**
     * 批量删除二创信息
     * 
     * @param ids 需要删除的二创信息主键
     * @return 结果
     */
    @Override
    public int deleteBlueErchuangByIds(Long[] ids)
    {
        return blueErchuangMapper.deleteBlueErchuangByIds(ids);
    }

    /**
     * 删除二创信息信息
     * 
     * @param id 二创信息主键
     * @return 结果
     */
    @Override
    public int deleteBlueErchuangById(Long id)
    {
        return blueErchuangMapper.deleteBlueErchuangById(id);
    }

    @Override
    public List<BlueErchuang> selectBlueErchuangListByUser() {
        //添加查询体条件
        LambdaQueryWrapper<BlueErchuang> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlueErchuang::getCreateBy,SecurityUtils.getUserId());
        return blueErchuangMapper.selectList(wrapper);
    }
}
