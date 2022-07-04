package mqdelay.send.strategy;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

@Component
public interface PriorityStrategy {
    public void MySetPriority() throws RemotingException, InterruptedException, MQClientException, MQBrokerException;
}
