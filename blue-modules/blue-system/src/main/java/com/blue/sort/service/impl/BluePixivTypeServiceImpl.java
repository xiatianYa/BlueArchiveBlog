package com.blue.sort.service.impl;

import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BluePixivType;
import com.blue.sort.mapper.BluePixivTypeMapper;
import com.blue.sort.service.IBluePixivTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 番剧类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-01
 */
@Service
public class BluePixivTypeServiceImpl implements IBluePixivTypeService 
{
    @Autowired
    private BluePixivTypeMapper bluePixivTypeMapper;

    /**
     * 查询番剧类型
     * 
     * @param id 番剧类型主键
     * @return 番剧类型
     */
    @Override
    public BluePixivType selectBluePixivTypeById(Long id)
    {
        return bluePixivTypeMapper.selectBluePixivTypeById(id);
    }

    /**
     * 查询番剧类型列表
     * 
     * @param bluePixivType 番剧类型
     * @return 番剧类型
     */
    @Override
    public List<BluePixivType> selectBluePixivTypeList(BluePixivType bluePixivType)
    {
        return bluePixivTypeMapper.selectBluePixivTypeList(bluePixivType);
    }

    /**
     * 新增番剧类型
     * 
     * @param bluePixivType 番剧类型
     * @return 结果
     */
    @Override
    public int insertBluePixivType(BluePixivType bluePixivType)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            bluePixivType.setCreateBy(String.valueOf(userId));
        }
        bluePixivType.setCreateTime(DateUtils.getNowDate());
        return bluePixivTypeMapper.insertBluePixivType(bluePixivType);
    }

    /**
     * 修改番剧类型
     * 
     * @param bluePixivType 番剧类型
     * @return 结果
     */
    @Override
    public int updateBluePixivType(BluePixivType bluePixivType)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            bluePixivType.setUpdateBy(String.valueOf(userId));
        }
        bluePixivType.setUpdateTime(DateUtils.getNowDate());
        return bluePixivTypeMapper.updateBluePixivType(bluePixivType);
    }

    /**
     * 批量删除番剧类型
     * 
     * @param ids 需要删除的番剧类型主键
     * @return 结果
     */
    @Override
    public int deleteBluePixivTypeByIds(Long[] ids)
    {
        return bluePixivTypeMapper.deleteBluePixivTypeByIds(ids);
    }

    /**
     * 删除番剧类型信息
     * 
     * @param id 番剧类型主键
     * @return 结果
     */
    @Override
    public int deleteBluePixivTypeById(Long id)
    {
        return bluePixivTypeMapper.deleteBluePixivTypeById(id);
    }
}
