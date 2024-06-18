package com.blue.ws.config;


import com.alibaba.fastjson2.JSONObject;
import com.blue.common.core.constant.ChatConstants;
import com.blue.common.core.constant.Constants;
import com.blue.common.core.constant.TokenConstants;
import com.blue.common.core.exception.ServiceException;
import com.blue.common.core.utils.JwtUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.redis.service.RedisService;
import com.blue.common.security.service.TokenService;
import com.blue.system.api.model.LoginUser;
import com.blue.ws.entry.SendMessageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.alibaba.nacos.api.common.Constants.ACCESS_TOKEN;


/**
 * @author summer
 */
@ServerEndpoint("/server/{token}")
@Component
public class WebSocketServer {
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static final ConcurrentHashMap<Long,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**登录用户信息*/
    private LoginUser loginUser;
    /**RedisService*/
    private static RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        WebSocketServer.redisService = redisService;
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
        //向全体用户发送上线消息
        sendOnLineMessage(loginUser);
        //给自己发登录成功的消息
        SendMessageVo sendMessageVo = SendMessageVo.builder().
                fromUserId(loginUser.getUserid()).
                toUserId(loginUser.getUserid()).
                fromUserAvatar(loginUser.getSysUser().
                        getAvatar()).
                fromUserNickName(loginUser.getUsername()).
                message("登录成功").type(ChatConstants.LoginSuccessType)
                .build();
        sendMessageByUser(sendMessageVo);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(loginUser.getUserid())){
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
        SendMessageVo sendMessageVo = JSONObject.parseObject(message, SendMessageVo.class);
        if(StringUtils.isNull(sendMessageVo)){
            throw new ServiceException("消息不能为空");
        }
        //设置消息类型
        sendMessageVo.setType(ChatConstants.ChatGroupType);
        //设置用户ID
        //向所有在线用户发送消息
        for (WebSocketServer item : webSocketMap.values()) {
            item.sendMessageAll(JSONObject.toJSONString(sendMessageVo));
        }
    }

    /**
     * 实现服务器主动推送消息(所有人)
     */
    public void sendMessageAll(Object object){
        try {
            //Json实例化对象
            String jsonString = JSONObject.toJSONString(object);
            this.session.getBasicRemote().sendText(jsonString);
        } catch (IOException e) {
            throw new ServiceException("消息发送失败"+e);
        }
    }
    /**
     * 实现服务器主动推送消息(指定用户)
     */
    public void sendMessageByUser(SendMessageVo sendMessageVo){
        try {
            //Json实例化对象
            String jsonString = JSONObject.toJSONString(sendMessageVo);
            //获取该用户连接实例
            WebSocketServer webSocketServer = webSocketMap.get(sendMessageVo.getToUserId());
            if(StringUtils.isNull(webSocketServer)){
                throw new ServiceException("用户未登录");
            }
            //发送信息
            webSocketServer.session.getBasicRemote().sendText(jsonString);
        } catch (IOException e) {
            throw new ServiceException("消息发送失败"+e);
        }
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
        SendMessageVo sendMessageVo = new SendMessageVo();
        sendMessageVo.setType(ChatConstants.OffLineType);
        sendMessageVo.setMessage(String.valueOf(UserId));
        webSocketMap.forEach((k,v)->{
            try {
                v.session.getBasicRemote().sendText(JSONObject.toJSONString(sendMessageVo));
            } catch (IOException e) {
                throw new ServiceException("消息发送失败");
            }
        });
    }
    /**
     * 给所有在线用户发送有人上线消息
     */
    public static synchronized void sendOnLineMessage(LoginUser loginUser){
        SendMessageVo sendMessageVo = new SendMessageVo();
        sendMessageVo.setType(ChatConstants.OnLineType);
        sendMessageVo.setFromUserId(loginUser.getUserid());
        sendMessageVo.setFromUserAvatar(loginUser.getSysUser().getAvatar());
        sendMessageVo.setFromUserNickName(loginUser.getSysUser().getNickName());
        sendMessageVo.setMessage(loginUser.getUsername()+"上线了");
        webSocketMap.forEach((k,v)->{
            try {
                v.session.getBasicRemote().sendText(JSONObject.toJSONString(sendMessageVo));
            } catch (IOException e) {
                throw new ServiceException("消息发送失败");
            }
        });
    }
    private String getTokenKey(String token) {
        return TokenConstants.REDIS_USER_KEY_PREFIX+ ":" + token;
    }
}

