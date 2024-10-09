package com.blue.job.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blue.job.domain.BlueGameStatistics;
import com.blue.job.mapper.BlueGameStatisticsMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Component("StatisticsTask")
public class StatisticsTask {
    @Resource
    private BlueGameStatisticsMapper blueGameStatisticsMapper;
    public void initStatisticsTask(){
        QueryWrapper<BlueGameStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("time", LocalDateTime.now().minusHours(24));
        //清空表中所有数据
        blueGameStatisticsMapper.delete(queryWrapper);
    }
}
