package com.example.demo.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @author 陈睿
 * @date 2020-01-09 19:18
 */
@Component
@EnableJms
public class ConfigBean {
    @Value("${myqueue}")
    private String myqueue;
    @Bean
    public Queue queue(){
        return new ActiveMQQueue(myqueue);

    }
}
