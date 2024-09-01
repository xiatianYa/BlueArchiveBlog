package com.blue.job.task;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blue.common.core.utils.SteamUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.redis.service.RedisService;
import com.blue.job.domain.BlueGameCommunity;
import com.blue.job.domain.BlueGameServer;
import com.blue.job.domain.BlueGameStatistics;
import com.blue.job.mapper.BlueGameCommunityMapper;
import com.blue.job.mapper.BlueGameServerMapper;
import com.blue.job.mapper.BlueGameStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;


@Component("PollServerMessagesTask")
public class PollServerMessagesTask {
    @Resource
    private BlueGameStatisticsMapper blueGameStatisticsMapper;
    @Resource
    private BlueGameCommunityMapper blueGameCommunityMapper;
    @Resource
    private BlueGameServerMapper blueGameServerMapper;
    @Resource
    private RedisService redisService;
    public void serverMessageQuery() throws ExecutionException, InterruptedException {
        //数据库时间
        Date time = new Date();
        // 获取当前时间（开始时间）
        long startTime = System.currentTimeMillis();
        //查询所有社区
        LambdaQueryWrapper<BlueGameCommunity> blueGameCommunityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<BlueGameCommunity> blueGameCommunities = blueGameCommunityMapper.selectList(blueGameCommunityLambdaQueryWrapper);
        //查询所有的服务器
        LambdaQueryWrapper<BlueGameServer> blueGameServerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<BlueGameServer> blueGameServers = blueGameServerMapper.selectList(blueGameServerLambdaQueryWrapper);
        //存储至Redis的Map
        HashMap<String, String> serverMap = new HashMap<>();
        // 创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(blueGameCommunities.size());
        // 存储Future对象的列表，以便稍后获取结果
        List<Future<String>> futures = new ArrayList<>();
        //遍历社区列表 获取服务器数据
        for (BlueGameCommunity blueGameCommunity : blueGameCommunities) {
            //获取服务器列表
            List<BlueGameServer> serverList = blueGameServers.stream().filter(item -> item.getCommunityId().equals(blueGameCommunity.getId())).collect(Collectors.toList());
            ArrayList<String> paths = new ArrayList<>();
            for (BlueGameServer blueGameServer : serverList) {
                paths.add(blueGameServer.getIp()+":"+blueGameServer.getPort());
            }
            Callable<String> task = () -> SteamUtils.getSteamApiServer(paths);
            Future<String> future = executor.submit(task);
            futures.add(future);
        }
        // 等待所有任务完成并收集结果
        List<String> results = new ArrayList<>();
        for (Future<String> future : futures) {
            results.add(future.get()); // 注意：这里会阻塞直到结果可用
        }
        // 处理数据
        for (int i = 0; i < blueGameCommunities.size(); i++) {
            BlueGameCommunity blueGameCommunity = blueGameCommunities.get(i);
            //获取异步返回数据
            String serverJson = results.get(i);
            JSONArray jsonArray = JSON.parseArray(serverJson);
            int totalPlayers = getTotalPlayers(jsonArray);
            //存储实体类
            BlueGameStatistics blueGameStatistics = new BlueGameStatistics();
            blueGameStatistics.setCommunityPlay(0);
            blueGameStatistics.setCommunityId(blueGameCommunity.getId());
            blueGameStatistics.setCommunityPlay(totalPlayers);
            blueGameStatistics.setTime(time);
            //保存到数据库中
            blueGameStatisticsMapper.insert(blueGameStatistics);
            //数据存储进Redis中
            serverMap.put(blueGameCommunity.getId().toString(), serverJson);
        }
        redisService.deleteObject("server_json");
        redisService.setCacheMap("server_json",serverMap);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        // 输出方法执行时间
        System.out.println("获取服务器信息耗时 : " + elapsedTime + "毫秒");
    }

    /**
     * 获取总人数
     * @param jsonArray
     * @return
     */
    private static int getTotalPlayers(JSONArray jsonArray) {
        int totalPlayers = 0;
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject response = jsonObject.getJSONObject("response");
            JSONArray servers = response.getJSONArray("servers");
            if (StringUtils.isNull(servers)){
                continue;
            }
            for (Object serverObj : servers) {
                JSONObject server = (JSONObject) serverObj;
                int players = server.getIntValue("players");
                totalPlayers += players;
            }
        }
        return totalPlayers;
    }
}
