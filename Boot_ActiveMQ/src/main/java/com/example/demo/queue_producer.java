package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * @author 陈睿
 * @date 2020-01-09 19:26
 */
@Component
public class queue_producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    public void producerMsg() {
        jmsMessagingTemplate.convertAndSend(queue, "**" + UUID.randomUUID().toString().substring(0, 6));
    }

    //设置定时3秒投递消息
   /* @Scheduled(fixedDelay = 3000)
    public void producerMsgScheduled() {
        jmsMessagingTemplate.convertAndSend(queue, "**" + UUID.randomUUID().toString().substring(0, 6));
    }*/
}
