package com.blue.blog.service.impl;

import com.blue.blog.domain.BlueBg;
import com.blue.blog.mapper.BlueBgMapper;
import com.blue.blog.service.IBlueBgService;
import com.blue.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 网站背景资源信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-17
 */
@Service
public class BlueBgServiceImpl implements IBlueBgService {
    @Autowired
    private BlueBgMapper blueBgMapper;

    /**
     * 查询网站背景资源信息
     * 
     * @param id 网站背景资源信息主键
     * @return 网站背景资源信息
     */
    @Override
    public BlueBg selectBlueBgById(Long id) {
        return blueBgMapper.selectBlueBgById(id);
    }

    /**
     * 查询网站背景资源信息列表
     * 
     * @param blueBg 网站背景资源信息
     * @return 网站背景资源信息
     */
    @Override
    public List<BlueBg> selectBlueBgList(BlueBg blueBg) {
        return blueBgMapper.selectBlueBgList(blueBg);
    }

    /**
     * 新增网站背景资源信息
     * 
     * @param blueBg 网站背景资源信息
     * @return 结果
     */
    @Override
    public int insertBlueBg(BlueBg blueBg) {
        blueBg.setCreateTime(DateUtils.getNowDate());
        blueBg.setUpdateTime(DateUtils.getNowDate());
        return blueBgMapper.insertBlueBg(blueBg);
    }

    /**
     * 修改网站背景资源信息
     * 
     * @param blueBg 网站背景资源信息
     * @return 结果
     */
    @Override
    public int updateBlueBg(BlueBg blueBg) {
        blueBg.setUpdateTime(DateUtils.getNowDate());
        return blueBgMapper.updateBlueBg(blueBg);
    }

    /**
     * 批量删除网站背景资源信息
     * 
     * @param ids 需要删除的网站背景资源信息主键
     * @return 结果
     */
    @Override
    public int deleteBlueBgByIds(Long[] ids) {
        return blueBgMapper.deleteBlueBgByIds(ids);
    }

    /**
     * 删除网站背景资源信息信息
     * 
     * @param id 网站背景资源信息主键
     * @return 结果
     */
    @Override
    public int deleteBlueBgById(Long id) {
        return blueBgMapper.deleteBlueBgById(id);
    }
}
