package com.blue.ws.service;

import com.blue.system.api.model.UserVo;

import java.util.List;

public interface ChatService {

    /**
     * 获取用户列表
     */
    List<UserVo> getUserList();
}
