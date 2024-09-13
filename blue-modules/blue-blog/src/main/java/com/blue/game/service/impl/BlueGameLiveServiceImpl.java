package com.blue.game.service.impl;

import java.util.*;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.blue.common.core.utils.BiliUtils;
import com.blue.common.core.utils.file.ImageUtils;
import com.blue.game.domain.vo.BiliUserDataVo;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<String> uids=new ArrayList<>();
        for (BlueGameLive item : blueGameLives) {
            uids.add(item.getUid());
        }
        JSONObject roomIds = JSONObject
                .parseObject(BiliUtils.getBiliLiveApi(uids))
                .getJSONObject("data")
                .getJSONObject("by_room_ids");
        for (String key : roomIds.keySet()){
            BiliUserDataVo biliUserDataVo = roomIds.getObject(key, BiliUserDataVo.class);
            //获取封面
            biliUserDataVo.setCoverByte(ImageUtils.getImage(biliUserDataVo.getCover()));
            //获取主播头像
            biliUserDataVo.setUserCoverByte(BiliUtils.getBiliLiveUserInfoApi(biliUserDataVo.getRoomId()));
            System.out.println(biliUserDataVo);
            biliUserDataVos.add(biliUserDataVo);
        }
        biliUserDataVos.sort(new Comparator<BiliUserDataVo>() {
            @Override
            public int compare(BiliUserDataVo o1, BiliUserDataVo o2) {
                // 假设你想要升序排序
                return o1.getLiveStatus().compareTo(o2.getLiveStatus());
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
        return blueGameLiveMapper.insertBlueGameLive(blueGameLive);
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
