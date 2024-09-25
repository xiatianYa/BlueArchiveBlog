package com.blue.ws.server;


import com.alibaba.fastjson2.JSONObject;
import com.blue.common.core.constant.ChatConstants;
import com.blue.common.core.utils.RandomUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.core.utils.udp.GameServerUtil;
import com.blue.common.redis.service.RedisService;
import com.blue.ws.entry.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author summer
 */
@ServerEndpoint("/gameServer")
@Component
public class GameWebSocketServer {
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static final ConcurrentHashMap<Long, GameWebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**登录用户信息*/
    private Long userId;
    /**RedisService*/
    private static RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        GameWebSocketServer.redisService = redisService;
    }

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        try {
            //构建登录成功的消息
            gameServerMessageVo messageVo = gameServerMessageVo
                    .builder()
                    .message("登录成功")
                    .type(ChatConstants.LoginSuccessType)
                    .build();
            String random = RandomUtils.getRandom(16);
            this.userId=Long.valueOf(random);
            webSocketMap.put(this.userId,this);
            System.out.println("用户"+this.userId+"连接成功");
            System.out.println("当前在线用户"+webSocketMap.size());
            //发送消息给用户
            sendMessage(JSONObject.toJSONString(messageVo));
        }catch (Exception e){
            errorMessageVo error = errorMessageVo.builder()
                    .data("消息发送失败")
                    .type(ChatConstants.MessageFailType)
                    .build();
            onError(JSONObject.toJSONString(error),this.session);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(this.userId)){
            //删除当前连接
            webSocketMap.remove(this.userId);
            //用户断开
            System.out.println("用户"+this.userId+"连接断开");
        }
    }

    /**
     * 消息发送失败(推送给发送者)
     */
    @SneakyThrows
    public static void onError(String jsonObject, Session session) {
        session.getBasicRemote().sendText(JSONObject.toJSONString(jsonObject));
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            //Json实例化对象
            gameServerMessageVo requestParam = JSONObject.parseObject(message, gameServerMessageVo.class);
            byte[] responseData = GameServerUtil.sendAndReceiveUDP(requestParam.getIp(), requestParam.getPort());
            String responseJson;
            //返回数据为null 获取失败
            if (StringUtils.isNull(responseData)) {
                errorMessageVo error = errorMessageVo.builder()
                        .data("服务器信息获取失败!")
                        .type(ChatConstants.MessageFailType)
                        .build();
                responseJson = JSONObject.toJSONString(error);
                session.getBasicRemote().sendText(responseJson);
                return;
            }
            sendMessageVo response = sendMessageVo.builder()
                    .data(
                    JSONObject.toJSONString(gamerServerVo.builder()
                    .ip(requestParam.getIp())
                    .port(requestParam.getPort())
                    .players((int) responseData[0])
                    .maxPlayers((int) responseData[1])
                    .status((int) responseData[0] <= requestParam.getMinPlayers())
                    .build()))
                    .type(ChatConstants.ServerMessageSuccessType)
                    .build();
            synchronized (session) {
                session.getBasicRemote().sendText(JSONObject.toJSONString(response));
            }
        }catch (Exception e) {
            String responseJson;
            //返回数据为null 获取失败
            errorMessageVo error = errorMessageVo.builder()
                    .data("服务器信息获取失败!")
                    .type(ChatConstants.MessageFailType)
                    .build();
            responseJson = JSONObject.toJSONString(error);
            try {
                session.getBasicRemote().sendText(responseJson);
            } catch (IOException ex) {
                System.out.println("服务器推送消息失败!");
            }
        }
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 服务器推送地图数据给服务器端
     */
    @Scheduled(fixedRate = 3000)
    public void sendServerMessage(){
        Map<String, String> serverJson = redisService.getCacheMap("server_json");
        webSocketMap.forEach((k,v)->{
            try {
                sendMessageVo build = sendMessageVo.builder()
                        .data(JSONObject.toJSONString(serverJson))
                        .type(ChatConstants.ServerPushServerDataType)
                        .build();
                synchronized (v.session) {
                    v.session.getBasicRemote().sendText(JSONObject.toJSONString(build));
                }
            } catch (IOException e) {
                String responseJson;
                //返回数据为null 获取失败
                errorMessageVo error = errorMessageVo.builder()
                        .data("服务器信息获取失败!")
                        .type(ChatConstants.MessageFailType)
                        .build();
                responseJson = JSONObject.toJSONString(error);
                try {
                    v.session.getBasicRemote().sendText(responseJson);
                } catch (IOException ex) {
                    System.out.println("服务器推送消息失败!");
                }
            }
        });
    }
}

