package topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author 陈睿
 * @date 2020-01-07 14:37
 */
public class JMSConsumer {
    private static final String ACTIVEMQ_URL="tcp://192.168.122.1:61616";
    private static final String TOPIC_NAME="topic";
    public static void main(String[] args) throws JMSException, IOException {
        //1、创建连接工厂，按照给定的url地址，采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2、通过连接工厂，获得连接connection并启动访问
        Connection connection=activeMQConnectionFactory.createConnection();
        connection.start();
        //3、创建会话session,两个参数：1、事务 2、签收
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4、创建目的地topic,Destination destination=session.createTopic(TOPIC_NAME);
        Topic topic=session.createTopic(TOPIC_NAME);
        //5、创建消息的消费者
        MessageConsumer messageConsumer=session.createConsumer(topic);
        /*
        //第一种方式
        while (true){
            TextMessage textMessage=(TextMessage)messageConsumer.receive();
            if (null!=textMessage){
                System.out.println("接收到消息："+textMessage.getText());
            }else {
                break;
            }
        }*/
        //第二种方式，通过监听
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (null!=message &&message instanceof TextMessage){
                    TextMessage textMessage=(TextMessage)message;
                    try {
                        System.out.println("接收到消息："+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();//保证控制台不关闭
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
