package topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author 陈睿
 * @date 2020-01-08 20:59
 */
public class JMSConsumer_Persist {
    private static final String ACTIVEMQ_URL="tcp://192.168.122.1:61616";
    private static final String TOPIC_NAME="topic";
    public static void main(String[] args) throws JMSException, IOException {
        //1、创建连接工厂，按照给定的url地址，采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2、通过连接工厂，获得连接connection并启动访问
        Connection connection=activeMQConnectionFactory.createConnection();

        connection.setClientID("陈睿");
        //3、创建会话session,两个参数：1、事务 2、签收
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4、创建目的地topic,Destination destination=session.createTopic(TOPIC_NAME);
        Topic topic=session.createTopic(TOPIC_NAME);

        TopicSubscriber topicSubscriber=session.createDurableSubscriber(topic,"remake...");
        connection.start();
        Message message=topicSubscriber.receive();
        while (null!=message){
            TextMessage textMessage=(TextMessage)message;
            System.out.println("接收到消息："+textMessage.getText());
            message=topicSubscriber.receive(5000);
        }
        connection.close();
        session.close();
        connection.close();
    }
}

