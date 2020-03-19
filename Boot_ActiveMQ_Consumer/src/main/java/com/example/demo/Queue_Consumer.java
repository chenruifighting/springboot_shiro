package com.example.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author 陈睿
 * @date 2020-01-13 11:27
 */
@Component
public class Queue_Consumer {
    @JmsListener(destination = "${myqueue}")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("消费者收到："+textMessage.getText());
    }
}
