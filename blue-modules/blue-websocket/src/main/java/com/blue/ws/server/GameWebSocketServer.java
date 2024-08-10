package com.blue.ws.server;


import com.alibaba.fastjson2.JSONObject;
import com.blue.common.core.constant.ChatConstants;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.core.utils.udp.GameServerUtil;
import com.blue.ws.entry.*;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


/**
 * @author summer
 */
@ServerEndpoint("/gameServer")
@Component
public class GameWebSocketServer {
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;

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
        System.out.println("连接关闭");
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
            // 获取当前时间（开始时间）
            long startTime = System.currentTimeMillis();
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
            session.getBasicRemote().sendText(JSONObject.toJSONString(response));
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            // 输出方法执行时间
            System.out.println("获取服务器信息耗时 : " + elapsedTime + "毫秒");
        }catch (Exception e){
            System.out.println("服务器消息发送失败!");
        }
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}

