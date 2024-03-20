package com.blue.cloud.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    @RabbitListener(queues = "simple.queue")
    public void listen01(String msg){
        System.out.println("收到消息:"+msg);
    }
}
