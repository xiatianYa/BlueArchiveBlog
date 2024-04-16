package com.blue.echarts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.blog.domain.BlueArticle;
import com.blue.blog.domain.BlueErchuang;
import com.blue.blog.domain.BluePhoto;
import com.blue.blog.mapper.BlueArticleMapper;
import com.blue.blog.mapper.BlueErchuangMapper;
import com.blue.blog.mapper.BluePhotoMapper;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.echarts.domain.UserHeatMapDataVo;
import com.blue.echarts.service.BlueEchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlueEchartsServiceImpl implements BlueEchartsService {
    @Autowired
    private BlueArticleMapper blueArticleMapper;
    @Autowired
    private BluePhotoMapper bluePhotoMapper;
    @Autowired
    private BlueErchuangMapper blueErchuangMapper;
    @Override
    public List<UserHeatMapDataVo> selectUserHeatMapData() {
        //时间格式化对象
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd");
        //获取用户ID
        Long userId = SecurityUtils.getUserId();
        //返回数据列表
        List<UserHeatMapDataVo> userHeatMapDataVos = new ArrayList<>();
        //查询用户文章提交
        LambdaQueryWrapper<BlueArticle> articleLambdaQueryWrapper=new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(BlueArticle::getUserId,userId);
        List<BlueArticle> blueArticles = blueArticleMapper.selectList(articleLambdaQueryWrapper);
        for (BlueArticle blueArticle : blueArticles) {
            boolean exists=userHeatMapDataVos.stream().anyMatch(vo->
                    vo.getDate().equals(simpleDateFormat.format(blueArticle.getCreateTime())));
            //如果不是第一次提交 则进入
            if (exists){
                //查询userHeatMapDataVos里面CommitTime等于blueArticle.getCreateTime()的数据
                UserHeatMapDataVo userHeatMapDataVo = userHeatMapDataVos.stream().filter(vo->
                        vo.getDate().equals(simpleDateFormat.format(blueArticle.getCreateTime()))).findFirst().get();

                if (userHeatMapDataVo.getCount()<10){
                    userHeatMapDataVo.setCount(userHeatMapDataVo.getCount()+1);
                }
            }else{
                //如果是第一次提交，则新增一条数据
                UserHeatMapDataVo userHeatMapDataVo = new UserHeatMapDataVo();
                userHeatMapDataVo.setDate(simpleDateFormat.format(blueArticle.getCreateTime()));
                userHeatMapDataVo.setCount(1);
                userHeatMapDataVos.add(userHeatMapDataVo);
            }
        }
        //查询用户相册提交
        LambdaQueryWrapper<BluePhoto> photoLambdaQueryWrapper=new LambdaQueryWrapper<>();
        photoLambdaQueryWrapper.eq(BluePhoto::getUserId,userId);
        List<BluePhoto> bluePhotos = bluePhotoMapper.selectList(photoLambdaQueryWrapper);
        for (BluePhoto bluePhoto : bluePhotos) {
            boolean exists=userHeatMapDataVos.stream().anyMatch(vo->
                    vo.getDate().equals(simpleDateFormat.format(bluePhoto.getCreateTime())));
            //如果不是第一次提交 则进入
            if (exists){
                //查询userHeatMapDataVos里面CommitTime等于bluePhoto.getCreateTime()的数据
                UserHeatMapDataVo userHeatMapDataVo = userHeatMapDataVos.stream().filter(vo->
                        vo.getDate().equals(simpleDateFormat.format(bluePhoto.getCreateTime()))).findFirst().get();

                if (userHeatMapDataVo.getCount()<10){
                    userHeatMapDataVo.setCount(userHeatMapDataVo.getCount()+1);
                }
            }else{
                //查询userHeatMapDataVos里面CommitTime等于blueArticle.getCreateTime()的数据
                UserHeatMapDataVo userHeatMapDataVo = new UserHeatMapDataVo();
                userHeatMapDataVo.setDate(simpleDateFormat.format(bluePhoto.getCreateTime()));
                userHeatMapDataVo.setCount(1);
                userHeatMapDataVos.add(userHeatMapDataVo);
            }
        }
        //查询用户二创提交
        LambdaQueryWrapper<BlueErchuang> erchuangLambdaQueryWrapper=new LambdaQueryWrapper<>();
        erchuangLambdaQueryWrapper.eq(BlueErchuang::getCreateBy,userId);
        List<BlueErchuang> blueErchuangs = blueErchuangMapper.selectList(erchuangLambdaQueryWrapper);
        for (BlueErchuang blueErchuang : blueErchuangs) {
            boolean exists=userHeatMapDataVos.stream().anyMatch(vo->
                    vo.getDate().equals(simpleDateFormat.format(blueErchuang.getCreateTime())));
            //如果不是第一次提交 则进入
            if (exists){
                //查询userHeatMapDataVos里面CommitTime等于blueErchuang.getCreateTime()的数据
                UserHeatMapDataVo userHeatMapDataVo = userHeatMapDataVos.stream().filter(vo->
                        vo.getDate().equals(simpleDateFormat.format(blueErchuang.getCreateTime()))).findFirst().get();

                if (userHeatMapDataVo.getCount()<10){
                    userHeatMapDataVo.setCount(userHeatMapDataVo.getCount()+1);
                }
            }else{
                //查询userHeatMapDataVos里面CommitTime等于blueArticle.getCreateTime()的数据
                UserHeatMapDataVo userHeatMapDataVo = new UserHeatMapDataVo();
                userHeatMapDataVo.setDate(simpleDateFormat.format(blueErchuang.getCreateTime()));
                userHeatMapDataVo.setCount(1);
                userHeatMapDataVos.add(userHeatMapDataVo);
            }
        }
        return userHeatMapDataVos;
    }
}
