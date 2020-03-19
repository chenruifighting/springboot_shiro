package broker;

import org.apache.activemq.broker.BrokerService;

/**
 * @author 陈睿
 * @date 2020-01-09 10:48
 * 不使用linux命令启动activeMQ，用java程序启动
 */
public class ActiveMQ_Start {
    public static void main(String[] args) throws Exception {
        BrokerService brokerService=new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
