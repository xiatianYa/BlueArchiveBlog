package com.blue.ws.entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendMessageVo {
    //发送者
    private Long fromUserId;
    //发送者头像
    private String fromUserAvatar;
    //接收者
    private Long toUserId;
    //发送者昵称
    private String fromUserNickName;
    //消息体
    private String message;
    //消息类型
    private Integer type;
}
