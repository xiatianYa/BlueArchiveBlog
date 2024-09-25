package com.blue.ws.server;


import com.alibaba.fastjson2.JSONObject;
import com.blue.common.core.constant.ChatConstants;
import com.blue.common.core.constant.TokenConstants;
import com.blue.common.core.exception.ServiceException;
import com.blue.common.core.utils.JwtUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.redis.service.RedisService;
import com.blue.system.api.model.LoginUser;
import com.blue.ws.entry.errorMessageVo;
import com.blue.ws.entry.receiveMessageVo;
import com.blue.ws.entry.sendMessageVo;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author summer
 */
@ServerEndpoint("/server/{token}")
@Component
public class ChatWebSocketServer {
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static final ConcurrentHashMap<Long, ChatWebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    private static final Logger log = LoggerFactory.getLogger(ChatWebSocketServer.class);
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**登录用户信息*/
    private LoginUser loginUser;
    /**RedisService*/
    private static RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        ChatWebSocketServer.redisService = redisService;
    }
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("token") String token) {
        this.session = session;
        //根据token获取登录用户信息
        String userKey =JwtUtils.getUserKey(token);
        this.loginUser = redisService.getCacheObject(getTokenKey(userKey));
        if(StringUtils.isNull(loginUser)){
            throw new ServiceException("用户未登录");
        }
        webSocketMap.remove(loginUser.getUserid());
        webSocketMap.put(loginUser.getUserid(),this);
        //用户登录
        System.out.println("用户"+loginUser.getUsername()+"连接成功");
        //给自己发登录成功的消息
        sendMessageVo sendMessageVo = com.blue.ws.entry.sendMessageVo.builder().
                fromUserId(loginUser.getUserid()).
                toUserId(loginUser.getUserid()).
                fromUserAvatar(loginUser.getSysUser().
                        getAvatar()).
                fromUserNickName(loginUser.getUsername()).
                data("登录成功").type(ChatConstants.LoginSuccessType)
                .build();
        sendMessageByUser(sendMessageVo);
        //向全体用户发送上线消息
        sendOnLineMessage(loginUser);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(StringUtils.isNotNull(loginUser) && webSocketMap.containsKey(loginUser.getUserid())){
            //删除当前连接
            webSocketMap.remove(loginUser.getUserid());
            //用户断开
            System.out.println("用户"+loginUser.getUsername()+"连接断开");
            //发送离线消息
            sendOffLineMessage(loginUser.getUserid());
        }
    }

    /**
     * 客户端发送消息
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        //Json实例化对象
        receiveMessageVo receiveMessageVo = JSONObject.parseObject(message, com.blue.ws.entry.receiveMessageVo.class);
        if(StringUtils.isNull(receiveMessageVo)){
            errorMessageVo error = errorMessageVo.builder()
                    .data("消息不能为空")
                    .type(ChatConstants.MessageFailType)
                    .build();
            onError(JSONObject.toJSONString(error),session);
            return;
        }
        //获取发送者用户信息
        LoginUser formUser = webSocketMap.get(receiveMessageVo.getFromUserId()).loginUser;
        receiveMessageVo.setFromUserAvatar(formUser.getSysUser().getAvatar());
        receiveMessageVo.setFromUserNickName(formUser.getSysUser().getNickName());
        //判断消息类型
        switch (receiveMessageVo.getType()){
            case 201:
                //群聊
                sendMessageAll(receiveMessageVo);
                break;
            }
    }

    /**
     * 实现服务器主动推送消息(所有人)
     */
    public void sendMessageAll(Object object){
        //Json实例化对象
        String jsonString = JSONObject.toJSONString(object);
        webSocketMap.forEach((k,v)->{
            try {
                v.session.getBasicRemote().sendText(jsonString);
            } catch (IOException e) {
                errorMessageVo error = errorMessageVo.builder()
                        .data("消息发送失败")
                        .type(ChatConstants.MessageFailType)
                        .build();
                onError(JSONObject.toJSONString(error), v.session);
            }
        });
    }
    /**
     * 实现服务器主动推送消息(指定用户)
     */
    public void sendMessageByUser(sendMessageVo sendMessageVo){
        try {
            //Json实例化对象
            String jsonString = JSONObject.toJSONString(sendMessageVo);
            //获取该用户连接实例
            ChatWebSocketServer webSocketServer = webSocketMap.get(sendMessageVo.getToUserId());
            if(StringUtils.isNull(webSocketServer)){
                errorMessageVo error = errorMessageVo.builder()
                        .data("此用户未登录")
                        .type(ChatConstants.MessageFailType)
                        .build();
                onError(JSONObject.toJSONString(error),this.session);
            }
            //发送信息
            webSocketServer.session.getBasicRemote().sendText(jsonString);
        } catch (IOException e) {
            errorMessageVo error = errorMessageVo.builder()
                    .data("消息发送失败")
                    .type(ChatConstants.MessageFailType)
                    .build();
            onError(JSONObject.toJSONString(error),this.session);
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
     * 获取所有用户Id
     * */
    public static synchronized List<Long> getUserIdList(){
        List<Long> userIdList=new ArrayList<>();
        webSocketMap.forEach((k,v)->{
            userIdList.add(k);
        });
        return userIdList;
    }

    /**
     * 给所有在线用户发送有人离线消息
     */
    public static synchronized void sendOffLineMessage(Long UserId){
        sendMessageVo sendMessageVo = new sendMessageVo();
        sendMessageVo.setType(ChatConstants.OffLineType);
        sendMessageVo.setData(String.valueOf(UserId));
        webSocketMap.forEach((k,v)->{
            try {
                v.session.getBasicRemote().sendText(JSONObject.toJSONString(sendMessageVo));
            } catch (IOException e) {
                errorMessageVo error = errorMessageVo.builder()
                        .data("消息发送失败")
                        .type(ChatConstants.MessageFailType)
                        .build();
                onError(JSONObject.toJSONString(error),v.session);
            }
        });
    }
    /**
     * 给所有在线用户发送有人上线消息
     */
    public static synchronized void sendOnLineMessage(LoginUser loginUser){
        sendMessageVo sendMessageVo = new sendMessageVo();
        sendMessageVo.setType(ChatConstants.OnLineType);
        sendMessageVo.setFromUserId(loginUser.getUserid());
        sendMessageVo.setFromUserAvatar(loginUser.getSysUser().getAvatar());
        sendMessageVo.setFromUserNickName(loginUser.getSysUser().getNickName());
        sendMessageVo.setData(loginUser.getUsername()+"上线了");
        webSocketMap.forEach((k,v)->{
            try {
                v.session.getBasicRemote().sendText(JSONObject.toJSONString(sendMessageVo));
            } catch (IOException e) {
                errorMessageVo error = errorMessageVo.builder()
                        .data("消息发送失败")
                        .type(ChatConstants.MessageFailType)
                        .build();
                onError(JSONObject.toJSONString(error),v.session);
            }
        });
    }
    /**
     * 服务器推送在线用户给玩家
     */
    @Scheduled(fixedRate = 10000)
    public void sendAllUserMessage(){
        sendMessageVo sendMessageVo = new sendMessageVo();
        sendMessageVo.setType(ChatConstants.OnLineType);
        sendMessageVo.setData("系统定时推送消息");
        webSocketMap.forEach((k,v)->{
            try {
                v.session.getBasicRemote().sendText(JSONObject.toJSONString(sendMessageVo));
            } catch (IOException e) {
                errorMessageVo error = errorMessageVo.builder()
                        .data("消息发送失败")
                        .type(ChatConstants.MessageFailType)
                        .build();
                onError(JSONObject.toJSONString(error),v.session);
            }
        });
    }
    private String getTokenKey(String token) {
        return TokenConstants.REDIS_USER_KEY_PREFIX+ ":" + token;
    }
}

