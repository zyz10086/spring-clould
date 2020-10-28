//package com.rocketmq.cn.customer;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Service;
//
//@Service
//@RocketMQMessageListener(topic = "${demo.rocketmq.jsonconsumer.topic}", consumerGroup = "${demo.rocketmq.jsonconsumer.group}")
//public class JSONAsynConsumer implements RocketMQListener<JSONObject> {
//    @Override
//    public void onMessage(JSONObject message) {
//        System.out.printf("------- UserConsumer received: %s \n", message.toJSONString());
//    }
//}
