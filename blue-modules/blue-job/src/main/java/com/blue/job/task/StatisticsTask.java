package com.blue.job.task;

import com.blue.job.mapper.BlueGameStatisticsMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("StatisticsTask")
public class StatisticsTask {
    @Resource
    private BlueGameStatisticsMapper blueGameStatisticsMapper;
    public void initStatisticsTask(){
        //清空表中所有数据
        blueGameStatisticsMapper.delete(null);
    }
}
