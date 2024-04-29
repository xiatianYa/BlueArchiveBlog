package com.blue.ws.service.impl;

import com.blue.system.api.RemoteUserService;
import com.blue.ws.entry.User;
import com.blue.ws.service.ChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Resource
    private RemoteUserService remoteUserService;

    @Override
    public List<User> getUserList() {
        return null;
    }
}
