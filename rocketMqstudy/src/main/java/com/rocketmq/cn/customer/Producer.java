package com.rocketmq.cn.customer;

import com.alibaba.fastjson.JSONObject;
import com.rocketmq.cn.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class Producer {

    @Resource(name = "extRocketMQTemplate")
    private RocketMQTemplate extRocketMQTemplate;


    public void sendMsg(String topic,String msg){
        SendResult sendResult = extRocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(msg).build());
        log.info("发送结果{}",sendResult.isTraceOn());
    }

    public void sendUser(String topic, User user){
        SendResult sendResult = extRocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(user).build());
        log.info("发送结果{}",sendResult.isTraceOn());
    }

    public void sendJson(String topic, JSONObject tmp){
        extRocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload(tmp).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送结果{}",sendResult.isTraceOn());
            }
            @Override
            public void onException(Throwable e) {
                log.error("发送失败",e);
            }
        });

    }
}
