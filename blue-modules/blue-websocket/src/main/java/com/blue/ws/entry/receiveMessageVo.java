package com.blue.ws.entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class receiveMessageVo {
    //发送者
    private Long fromUserId;
    //发送者头像
    private String fromUserAvatar;
    //发送者昵称
    private String fromUserNickName;
    //消息体
    private String data;
    //消息类型
    private Integer type;
}
