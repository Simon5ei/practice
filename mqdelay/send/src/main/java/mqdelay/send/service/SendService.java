package mqdelay.send.service;

import com.alibaba.fastjson.JSON;
import mqdelay.send.model.OrderMsg;
import mqdelay.send.rocket.Producer;
import mqdelay.send.rocket.RocketConstants;
import mqdelay.send.strategy.PriorityStrategy;
import mqdelay.send.strategy.normalStrategy;
import mqdelay.send.strategy.vipStrategy;
import mqdelay.send.util.TimeUtil;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Service
public class SendService{
    @Autowired
    private Producer producer;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    public void SendMs(int userId) throws RemotingException, InterruptedException, MQClientException, MQBrokerException {

        PriorityStrategy priorityStrategy;
        //策略模式生产方法
        if(userId>10) priorityStrategy =new vipStrategy();
        else priorityStrategy =new normalStrategy();
        //策略模式使用方法
        priorityStrategy.MySetPriority();


        logger.info(Thread.currentThread().getName());
        DateTimeFormatter df_year = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        LocalDateTime date = LocalDateTime.now();
        String dateStr = date.format(df_year);

        //消息,指定用户id和订单编号
        OrderMsg msg = new OrderMsg();
        msg.setUserId(userId);
        msg.setOrderSn(userId+"_"+dateStr);

        String msgJson = JSON.toJSONString(msg);
        //生成一个信息，标签在这里手动指定
        Message message = new Message(RocketConstants.TOPIC, "auto_cancel", msgJson.getBytes());
        //delayTime的值:
        //messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
        message.setDelayTimeLevel(2);
        //发送信息
        SendResult sendResult = producer.getProducer().send(message);
        logger.info("Time:"+ TimeUtil.getTimeNow()+"\n"+
                //"producer send a piece of massage:" +"\n"+
                " content:{"+msgJson+"},"+
                "result:{"+sendResult+"}");
    }
}
