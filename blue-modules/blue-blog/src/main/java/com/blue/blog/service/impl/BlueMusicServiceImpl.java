package com.blue.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.entry.dao.BlueMusic;
import com.blue.blog.entry.dto.BlueMusicListBySortDTO;
import com.blue.blog.mapper.BlueMusicMapper;
import com.blue.blog.service.IBlueMusicService;
import com.blue.common.core.enums.AuditingStatus;
import com.blue.common.core.utils.DateUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.sort.domain.BlueMusicSort;
import com.blue.sort.mapper.BlueMusicSortMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 音乐Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Service
public class BlueMusicServiceImpl implements IBlueMusicService 
{
    @Resource
    private BlueMusicMapper blueMusicMapper;
    @Resource
    private BlueMusicSortMapper blueMusicSortMapper;

    /**
     * 查询音乐
     * 
     * @param id 音乐主键
     * @return 音乐
     */
    @Override
    public BlueMusic selectBlueMusicById(Long id)
    {
        return blueMusicMapper.selectBlueMusicById(id);
    }

    /**
     * 查询音乐列表
     * 
     * @param blueMusic 音乐
     * @return 音乐
     */
    @Override
    public List<BlueMusic> selectBlueMusicList(BlueMusic blueMusic)
    {
        //配置审核条件
        if (StringUtils.isNull(blueMusic.getStatus())){
            blueMusic.setStatus(AuditingStatus.DISABLE.getCode());
        }
        return blueMusicMapper.selectBlueMusicList(blueMusic);
    }

    /**
     * 新增音乐
     * 
     * @param blueMusic 音乐
     * @return 结果
     */
    @Override
    public int insertBlueMusic(BlueMusic blueMusic)
    {
        Long userId = SecurityUtils.getLoginUser().getUserid();
        if(StringUtils.isNotNull(userId)){
            blueMusic.setCreateBy(userId.toString());
        }
        //设置音乐审核状态
        blueMusic.setStatus(AuditingStatus.OK.getCode());
        blueMusic.setCreateTime(DateUtils.getNowDate());
        return blueMusicMapper.insertBlueMusic(blueMusic);
    }

    /**
     * 修改音乐
     * 
     * @param blueMusic 音乐
     * @return 结果
     */
    @Override
    public int updateBlueMusic(BlueMusic blueMusic)
    {
        Long userId = SecurityUtils.getLoginUser().getUserid();
        if(StringUtils.isNotNull(userId)){
            blueMusic.setUpdateBy(userId.toString());
        }
        blueMusic.setUpdateTime(DateUtils.getNowDate());
        return blueMusicMapper.updateBlueMusic(blueMusic);
    }

    /**
     * 批量删除音乐
     * 
     * @param ids 需要删除的音乐主键
     * @return 结果
     */
    @Override
    public int deleteBlueMusicByIds(Long[] ids)
    {
        return blueMusicMapper.deleteBlueMusicByIds(ids);
    }

    /**
     * 删除音乐信息
     * 
     * @param id 音乐主键
     * @return 结果
     */
    @Override
    public int deleteBlueMusicById(Long id)
    {
        return blueMusicMapper.deleteBlueMusicById(id);
    }

    /**
     * 根据音乐分类获取音乐列表
     *
     * @param type 音乐主键
     * @return 结果
     */
    @Override
    public List<BlueMusicListBySortDTO> getMusicListBySort(Long type) {
        //获取全部音乐分类
        LambdaQueryWrapper<BlueMusicSort> blueMusicSortLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotNull(type)){
            blueMusicSortLambdaQueryWrapper.eq(BlueMusicSort::getId,type);
        }
        List<BlueMusicSort> blueMusicSorts = blueMusicSortMapper.selectList(blueMusicSortLambdaQueryWrapper);
        //获取全部音乐列表
        LambdaQueryWrapper<BlueMusic> blueMusicLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blueMusicLambdaQueryWrapper.eq(BlueMusic::getStatus,AuditingStatus.DISABLE.getCode());
        List<BlueMusic> blueMusics = blueMusicMapper.selectList(blueMusicLambdaQueryWrapper);
        //返回列表
        List<BlueMusicListBySortDTO> musicListBySorts=new ArrayList<>();
        for (BlueMusicSort blueMusicSort : blueMusicSorts) {
            BlueMusicListBySortDTO musicByType = getMusicByType(blueMusics,blueMusicSort.getSortName(),blueMusicSort.getId());
            musicListBySorts.add(musicByType);
        }
        return musicListBySorts;
    }
    /**
     * 音乐type获取音乐列表
     *
     * @param type 音乐主键
     * @return 结果
     */
    public BlueMusicListBySortDTO getMusicByType(List<BlueMusic> blueMusics, String sortName, Long type){
        BlueMusicListBySortDTO blueMusicListBySortDTO = new BlueMusicListBySortDTO();
        blueMusicListBySortDTO.setMusicList(new ArrayList<>());
        List<BlueMusic> musicList = blueMusicListBySortDTO.getMusicList();
        blueMusicListBySortDTO.setSortName(sortName);
        for (BlueMusic blueMusic : blueMusics) {
            if (StringUtils.isNotNull(type)){
                //匹配 则添加
                if (blueMusic.getSortId().equals(type)){
                    musicList.add(blueMusic);
                }
            }else {
                //type为空 则全部查询
                musicList.add(blueMusic);
            }
        }
        return blueMusicListBySortDTO;
    }
}
