package broker;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 陈睿
 * @date 2020-01-07 13:54
 */
public class JMSProducer {

    private static final String ACTIVEMQ_URL="tcp://localhost:61616";
    private static final String QUEUE_NAME="queue";
    public static void main(String[] args) throws JMSException {
        //1、创建连接工厂，按照给定的url地址，采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2、通过连接工厂，获得连接connection并启动访问
        Connection connection=activeMQConnectionFactory.createConnection();
        connection.start();
        //3、创建会话session,两个参数：1、事务 2、签收
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4、创建目的地queue或者topic,Destination destination=session.createQueue(QUEUE_NAME);
        Queue queue=session.createQueue(QUEUE_NAME);
        //5、创建消息的生产者
        MessageProducer messageProducer=session.createProducer(queue);
        //6、通过使用messageProducer生产3条消息发送到mq队列中
        for (int i = 1; i < 3; i++) {
            //7、创建TextMessage格式消息
            TextMessage textMessage=session.createTextMessage("msg---："+i);
            //创建MapMessage格式消息
            MapMessage mapMessage=session.createMapMessage();
            mapMessage.setString("key","value");
            //8、通过messageProducer发送给mq
            messageProducer.send(textMessage);
            messageProducer.send(mapMessage);
        }
        //9、关闭资源
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("消息发布到mq");
    }
}
