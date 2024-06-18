package com.blue.ws.service.impl;

import com.blue.common.core.constant.SecurityConstants;
import com.blue.common.core.exception.ServiceException;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.system.api.RemoteUserService;
import com.blue.system.api.model.UserVo;
import com.blue.ws.config.WebSocketServer;
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
        List<Long> userIdList = WebSocketServer.getUserIdList();
        Long userid = SecurityUtils.getLoginUser().getUserid();
        if (StringUtils.isNull(userid)){
            throw new ServiceException("用户信息失效,请重新登录!");
        }
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
