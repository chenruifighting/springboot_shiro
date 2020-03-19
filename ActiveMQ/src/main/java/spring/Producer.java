package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

/**
 * @author 陈睿
 * @date 2020-01-09 14:51
 */
@Service
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext");
        Producer producer=(Producer)applicationContext.getBean("Producer");
        producer.jmsTemplate.send(session -> {
            TextMessage textMessage=session.createTextMessage("spring整合activeMQ");
            return textMessage;
        });
        System.out.println("发送成功");
    }
}
