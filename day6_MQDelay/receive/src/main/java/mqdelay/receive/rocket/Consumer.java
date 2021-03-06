package mqdelay.receive.rocket;

import com.alibaba.fastjson.JSON;
import mqdelay.receive.model.OrderMsg;
import mqdelay.receive.util.TimeUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/*
*
* 消费者从那个位置消费，分别为：
3.1 CONSUME_FROM_LAST_OFFSET：第一次启动从队列最后位置消费，后续再启动接着上次消费的进度开始消费
3.2 CONSUME_FROM_FIRST_OFFSET：第一次启动从队列初始位置消费，后续再启动接着上次消费的进度开始消费
3.3 CONSUME_FROM_TIMESTAMP：第一次启动从指定时间点位置消费，后续再启动接着上次消费的进度开始消费
以上所说的第一次启动是指从来没有消费过的消费者，
* 如果该消费者消费过，那么会在broker端记录该消费者的消费位置，
* 如果该消费者挂了再启动，那么自动从上次消费的进度开始
*
*/

@Component
public class Consumer {

    //消费者组
    public static final String CONSUMER_GROUP = "order_consumer";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //构造函数 用来实例化对象
    public Consumer() throws MQClientException {
        //消费者实体对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
        consumer.setNamesrvAddr(RocketConstants.NAME_SERVER);
        //指定消费模式
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //指定订阅主题
        //指定订阅标签，*代表所有标签
        consumer.subscribe(RocketConstants.TOPIC, "*");
        //注册一个消费消息的Listener
        //对消息的消费在这里实现
        //两种回调 MessageListenerConcurrently 为普通监听，MessageListenerOrderly 为顺序监听
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            //遍历接收到的消息
            for (Message msg : msgs) {
                //得到消息的body
                String body = new String(msg.getBody(), StandardCharsets.UTF_8);
                //用json转成对象
                OrderMsg msgOne = JSON.parseObject(body, OrderMsg.class);
                //打印用户id
                logger.info("User_id:"+msgOne.getUserId());
                //打印订单编号
                logger.info("OrderSn:"+msgOne.getOrderSn());
                //打印消息内容
                logger.info("Time:"+ TimeUtil.getTimeNow()+"\n"+
                        //"consumer have received the massage-topic" +"\n"+
                        "{"+msg.getTopic()+"}" +"\n");
                        //"message content={"+body+"}");
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        logger.info("consumer launched success=======");
    }
}
