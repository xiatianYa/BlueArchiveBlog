package com.blue.ws.service.impl;

import com.blue.common.core.constant.SecurityConstants;
import com.blue.system.api.RemoteUserService;
import com.blue.system.api.model.UserVo;
import com.blue.ws.server.ChatWebSocketServer;
import com.blue.ws.service.ChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Resource
    private RemoteUserService remoteUserService;

    @Override
    public List<UserVo> getUserList() {
        //在线用户ID列表
        List<Long> userIdList = ChatWebSocketServer.getUserIdList();
        //返回对象列表
        List<UserVo> userVoList=new ArrayList<>();
        //获取用户信息
        for (Long id : userIdList) {
            userVoList.add(remoteUserService.getUserInfoById(id, SecurityConstants.INNER).getData());
        }
        return userVoList;
    }

    @Override
    public UserVo getUserVo(Long userId) {
        return remoteUserService.getUserInfoById(userId, SecurityConstants.INNER).getData();
    }

}
