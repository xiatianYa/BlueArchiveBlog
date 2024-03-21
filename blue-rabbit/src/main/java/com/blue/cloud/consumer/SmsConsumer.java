package com.blue.cloud.consumer;


import com.aliyuncs.exceptions.ClientException;
import com.blue.cloud.aliyun.AliYunSmsService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SmsConsumer {
    @Autowired
    private AliYunSmsService aliYunSmsService;
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "smsTopic"),
            exchange = @Exchange(name = "blueArchive.smsTopic", type = ExchangeTypes.TOPIC),
            key = {"sms.send"}
    ))
    public void SmsTopicQueue(Map<String,Object> map) throws ClientException {
        //获取手机号
        String phone = (String) map.get("phone");
        //获取验证码
        String phoneCode = (String) map.get("phoneCode");
        aliYunSmsService.sendSms(phone,phoneCode);
    }
}
