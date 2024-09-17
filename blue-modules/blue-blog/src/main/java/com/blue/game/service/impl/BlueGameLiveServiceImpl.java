package com.blue.game.service.impl;

import java.util.*;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.common.core.exception.ServiceException;
import com.blue.common.core.utils.BiliUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.core.utils.file.ImageUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.game.domain.vo.BiliUserDataVo;
import org.springframework.stereotype.Service;
import com.blue.game.mapper.BlueGameLiveMapper;
import com.blue.game.domain.BlueGameLive;
import com.blue.game.service.IBlueGameLiveService;

import javax.annotation.Resource;


/**
 * 游戏直播Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-11
 */
@Service
public class BlueGameLiveServiceImpl implements IBlueGameLiveService 
{
    @Resource
    private BlueGameLiveMapper blueGameLiveMapper;

    /**
     * 查询游戏直播
     * 
     * @param id 游戏直播主键
     * @return 游戏直播
     */
    @Override
    public BlueGameLive selectBlueGameLiveById(Long id)
    {
        return blueGameLiveMapper.selectBlueGameLiveById(id);
    }

    /**
     * 查询游戏直播列表
     * 
     * @param blueGameLive 游戏直播
     * @return 游戏直播
     */
    @Override
    public List<BiliUserDataVo> selectBlueGameLiveList(BlueGameLive blueGameLive)
    {
        //返回列表
        List<BiliUserDataVo> biliUserDataVos = new ArrayList<>();
        List<BlueGameLive> blueGameLives = blueGameLiveMapper.selectList(new LambdaQueryWrapper<>());
        for (BlueGameLive gameLive : blueGameLives) {
            JSONObject jsonObject = JSONObject
                    .parseObject(BiliUtils.getBiliLiveApi(gameLive.getUid()))
                    .getJSONObject("data")
                    .getJSONObject("by_room_ids")
                    .getJSONObject(gameLive.getUid());
            BiliUserDataVo biliUserDataVo = JSONObject.parseObject(jsonObject.toJSONString(), BiliUserDataVo.class);
            biliUserDataVo.setAvatarUrl(gameLive.getAvatar());
            biliUserDataVo.setBgUrl(gameLive.getBgUrl());
            gameLive.setBiliUserDataVo(biliUserDataVo);
            biliUserDataVos.add(biliUserDataVo);
        }
        biliUserDataVos.sort(new Comparator<BiliUserDataVo>() {
            @Override
            public int compare(BiliUserDataVo o1, BiliUserDataVo o2) {
                // 假设你想要升序排序
                return o2.getOnline().compareTo(o1.getOnline());
            }
        });
        return biliUserDataVos;
    }

    /**
     * 新增游戏直播
     * 
     * @param blueGameLive 游戏直播
     * @return 结果
     */
    @Override
    public int insertBlueGameLive(BlueGameLive blueGameLive)
    {
        Long userId = SecurityUtils.getLoginUser().getUserid();
        LambdaQueryWrapper<BlueGameLive> getOne = new LambdaQueryWrapper<>();
        getOne.eq(BlueGameLive::getCreateBy, userId);
        BlueGameLive one = blueGameLiveMapper.selectOne(getOne);
        if (StringUtils.isNotNull(one)){
            throw new ServiceException("你添加过直播间了,无法再次添加!");
        }
        LambdaQueryWrapper<BlueGameLive> getUid = new LambdaQueryWrapper<>();
        getUid.eq(BlueGameLive::getUid, blueGameLive.getUid());
        BlueGameLive uidOne = blueGameLiveMapper.selectOne(getUid);
        if (StringUtils.isNotNull(uidOne)){
            throw new ServiceException("大主播已入驻,请问重复添加!");
        }
        try{
            String biliLiveApi = BiliUtils.getBiliLiveApi(blueGameLive.getUid());
            String bgPath  = JSONObject
                    .parseObject(biliLiveApi)
                    .getJSONObject("data")
                    .getJSONObject("by_room_ids")
                    .getJSONObject(blueGameLive.getUid())
                    .getString("cover");
            String avatarPath = BiliUtils.getBiliLiveUserInfoApi(blueGameLive.getUid());
            //获取主播头像
            String avatarUrl = ImageUtils.downloadImageAsResource(avatarPath, "/home/blueArchive/uploadPath/live/", blueGameLive.getUid() + ".jpg");
            //获取背景
            String bgUrl = ImageUtils.downloadImageAsResource(bgPath,"/home/blueArchive/uploadPath/live/", blueGameLive.getUid()+"bg.jpg");
            blueGameLive.setCreateBy(userId);
            blueGameLive.setAvatar(avatarUrl);
            blueGameLive.setBgUrl(bgUrl);
            return blueGameLiveMapper.insertBlueGameLive(blueGameLive);
        }catch (Exception e){
            throw new ServiceException("请检查uid是否正确");
        }
    }

    /**
     * 修改游戏直播
     * 
     * @param blueGameLive 游戏直播
     * @return 结果
     */
    @Override
    public int updateBlueGameLive(BlueGameLive blueGameLive)
    {
        return blueGameLiveMapper.updateBlueGameLive(blueGameLive);
    }

    /**
     * 批量删除游戏直播
     * 
     * @param ids 需要删除的游戏直播主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameLiveByIds(Long[] ids)
    {
        return blueGameLiveMapper.deleteBlueGameLiveByIds(ids);
    }

    /**
     * 删除游戏直播信息
     * 
     * @param id 游戏直播主键
     * @return 结果
     */
    @Override
    public int deleteBlueGameLiveById(Long id)
    {
        return blueGameLiveMapper.deleteBlueGameLiveById(id);
    }
}
