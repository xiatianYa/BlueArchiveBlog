package com.blue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.blog.entry.dao.BlueErchuang;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 二创信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-02
 */
@Mapper
public interface BlueErchuangMapper extends BaseMapper<BlueErchuang>
{
    /**
     * 查询二创信息
     * 
     * @param id 二创信息主键
     * @return 二创信息
     */
    public BlueErchuang selectBlueErchuangById(Long id);

    /**
     * 查询二创信息列表
     * 
     * @param blueErchuang 二创信息
     * @return 二创信息集合
     */
    public List<BlueErchuang> selectBlueErchuangList(BlueErchuang blueErchuang);

    /**
     * 新增二创信息
     * 
     * @param blueErchuang 二创信息
     * @return 结果
     */
    public int insertBlueErchuang(BlueErchuang blueErchuang);

    /**
     * 修改二创信息
     * 
     * @param blueErchuang 二创信息
     * @return 结果
     */
    public int updateBlueErchuang(BlueErchuang blueErchuang);

    /**
     * 删除二创信息
     * 
     * @param id 二创信息主键
     * @return 结果
     */
    public int deleteBlueErchuangById(Long id);

    /**
     * 批量删除二创信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueErchuangByIds(Long[] ids);
}
