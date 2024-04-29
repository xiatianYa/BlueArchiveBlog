package com.blue.ws.controller;

import com.blue.common.core.web.domain.AjaxResult;
import com.blue.ws.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ws")
public class ChatServiceController {
    @Resource
    private ChatService chatService;
    @GetMapping("/getUserList")
    public AjaxResult getUserList() {
        return AjaxResult.success(chatService.getUserList());
    }
}
