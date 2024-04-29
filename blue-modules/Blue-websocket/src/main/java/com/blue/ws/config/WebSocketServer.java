package com.blue.ws.config;


import com.alibaba.fastjson2.JSONObject;
import com.blue.common.core.constant.ChatConstants;
import com.blue.common.core.exception.ServiceException;
import com.blue.common.core.utils.StringUtils;
import com.blue.ws.entry.SendMessageVo;
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
@ServerEndpoint("/server/{userId}")
@Component
public class WebSocketServer {

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<Long,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private Long userId=null;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") Long userId) {
        this.session = session;
        this.userId=userId;
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
            //加入set中
        }else{
            webSocketMap.put(userId,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }
        sendOnLineMessage(userId);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        sendOffLineMessage(userId);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        //Json实例化对象
        SendMessageVo sendMessageVo = JSONObject.parseObject(message, SendMessageVo.class);
        if(StringUtils.isNull(sendMessageVo)){
            throw new ServiceException("消息体为空");
        }
        sendMessageVo.setType(ChatConstants.ChatType);
        if(!webSocketMap.containsKey(sendMessageVo.getToUserId())){
            throw new ServiceException("用户已下线");
        }
        //获取接受对象webSocketServer
        WebSocketServer webSocketServer = webSocketMap.get(sendMessageVo.getToUserId());
        //发送消息
        webSocketServer.sendMessage(message);
    }

    /**
     *
     * @param session 连接对象
     * @param error 错误对象
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户错误:"+this.userId+",原因:"+error.getMessage());
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message){
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            throw new ServiceException("消息发送失败"+e);
        }
    }

    /**
     * 获取在线人数数量
     * */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    /**
     * 增加在线人数数量
     * */
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }
    /**
     * 减少在线人数数量
     * */
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
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
    public static synchronized void sendOnLineMessage(Long UserId){
        SendMessageVo sendMessageVo = new SendMessageVo();
        sendMessageVo.setType(ChatConstants.OnLineType);
        sendMessageVo.setMessage(String.valueOf(UserId));
        webSocketMap.forEach((k,v)->{
            try {
                v.session.getBasicRemote().sendText(JSONObject.toJSONString(sendMessageVo));
            } catch (IOException e) {
                throw new ServiceException("消息发送失败");
            }
        });
    }
}

