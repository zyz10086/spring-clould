//package com.rocketmq.cn.customer;
//
//import com.alibaba.fastjson.JSONObject;
//import com.rocketmq.cn.entity.User;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Service;
//
//@Service
//@RocketMQMessageListener(topic = "${demo.rocketmq.userconsumer.topic}", consumerGroup = "${demo.rocketmq.userconsumer.group}")
//public class UserConsumer implements RocketMQListener<User> {
//    @Override
//    public void onMessage(User message) {
//        System.out.printf("------- UserConsumer received: %s \n", JSONObject.toJSONString(message));
//    }
//}
