package com.blue.sort.service;

import com.blue.sort.domain.BluePixivType;

import java.util.List;

/**
 * 番剧类型Service接口
 * 
 * @author ruoyi
 * @date 2024-04-01
 */
public interface IBluePixivTypeService 
{
    /**
     * 查询番剧类型
     * 
     * @param id 番剧类型主键
     * @return 番剧类型
     */
    public BluePixivType selectBluePixivTypeById(Long id);

    /**
     * 查询番剧类型列表
     * 
     * @param bluePixivType 番剧类型
     * @return 番剧类型集合
     */
    public List<BluePixivType> selectBluePixivTypeList(BluePixivType bluePixivType);

    /**
     * 新增番剧类型
     * 
     * @param bluePixivType 番剧类型
     * @return 结果
     */
    public int insertBluePixivType(BluePixivType bluePixivType);

    /**
     * 修改番剧类型
     * 
     * @param bluePixivType 番剧类型
     * @return 结果
     */
    public int updateBluePixivType(BluePixivType bluePixivType);

    /**
     * 批量删除番剧类型
     * 
     * @param ids 需要删除的番剧类型主键集合
     * @return 结果
     */
    public int deleteBluePixivTypeByIds(Long[] ids);

    /**
     * 删除番剧类型信息
     * 
     * @param id 番剧类型主键
     * @return 结果
     */
    public int deleteBluePixivTypeById(Long id);
}
