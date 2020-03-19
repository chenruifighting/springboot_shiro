package topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 陈睿
 * @date 2020-01-08 20:56
 */
public class JMSProducer_Persist {
    private static final String ACTIVEMQ_URL="tcp://192.168.122.1:61616";
    private static final String TOPIC_NAME="topic";
    public static void main(String[] args) throws JMSException {
        //1、创建连接工厂，按照给定的url地址，采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2、通过连接工厂，获得连接connection
        Connection connection=activeMQConnectionFactory.createConnection();
        //3、创建会话session,两个参数：1、事务 2、签收
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4、创建目的地topic,Destination destination=session.createTopic(TOPIC_NAME);
        Topic topic=session.createTopic(TOPIC_NAME);
        //5、创建消息的生产者
        MessageProducer messageProducer=session.createProducer(topic);

        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();//启动访问
        //6、通过使用messageProducer生产3条消息发送到mq队列中
        for (int i = 1; i < 3; i++) {
            //7、创建消息
            TextMessage textMessage=session.createTextMessage("msg---："+i);
            //8、通过messageProducer发送给mq
            messageProducer.send(textMessage);
        }
        //9、关闭资源
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("消息发布到mq");
    }
}
