package com.blue.job.task;

import com.blue.common.redis.service.RedisService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时服务器查询调度
 *
 * @author Summer
 */
@Component("GameServeTask")
public class GameServerTask {
    @Resource
    private RedisService redisService;
    public void selectGameServer(){

    }
}
