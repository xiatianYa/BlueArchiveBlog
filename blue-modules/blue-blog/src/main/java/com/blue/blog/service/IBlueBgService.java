package com.blue.blog.service;

import com.blue.blog.entry.dao.BlueBg;

import java.util.List;

/**
 * 网站背景资源信息Service接口
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public interface IBlueBgService 
{
    /**
     * 查询网站背景资源信息
     * 
     * @param id 网站背景资源信息主键
     * @return 网站背景资源信息
     */
    public BlueBg selectBlueBgById(Long id);

    /**
     * 查询网站背景资源信息列表
     * 
     * @param blueBg 网站背景资源信息
     * @return 网站背景资源信息集合
     */
    public List<BlueBg> selectBlueBgList(BlueBg blueBg);

    /**
     * 新增网站背景资源信息
     * 
     * @param blueBg 网站背景资源信息
     * @return 结果
     */
    public int insertBlueBg(BlueBg blueBg);

    /**
     * 修改网站背景资源信息
     * 
     * @param blueBg 网站背景资源信息
     * @return 结果
     */
    public int updateBlueBg(BlueBg blueBg);

    /**
     * 批量删除网站背景资源信息
     * 
     * @param ids 需要删除的网站背景资源信息主键集合
     * @return 结果
     */
    public int deleteBlueBgByIds(Long[] ids);

    /**
     * 删除网站背景资源信息信息
     * 
     * @param id 网站背景资源信息主键
     * @return 结果
     */
    public int deleteBlueBgById(Long id);
}
