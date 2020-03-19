package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 陈睿
 * @date 2020-01-09 15:00
 */
@Service
public class Consumer {
    @Autowired
    private JmsTemplate jmsTemplate;
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext");
        Consumer consumer=(Consumer)applicationContext.getBean("Consumer");
        consumer.jmsTemplate.receive();
        System.out.println("收到消息");
    }
}