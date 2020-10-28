package com.spring.rocketmqnostarted.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class ProducerDemo {
    @Autowired
    private DefaultMQProducer defaultProducer;

//    @Autowired
//    private TransactionMQProducer transactionProducer;

    @Value("${rocket.topic}")
    private String topic;

    @Value("${rocket.tag}")
    private String tag;

    @RequestMapping(value = "/sendMsg", method = RequestMethod.GET)
    public void sendMsg(String body) {
        Message msg = new Message(topic, // topic
                tag, // tag
                UUID.randomUUID().toString(), // key
                body.getBytes());// body
        try {
            defaultProducer.send(msg, new SendCallback() {

                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("发送成功");
                }

                @Override
                public void onException(Throwable e) {
                    log.error("发生异常");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @RequestMapping(value = "/sendTransactionMsg", method = RequestMethod.GET)
//    public String sendTransactionMsg() {
//        SendResult sendResult = null;
//        try {
//            // 构造消息
//            Message msg = new Message("TopicTest1", // topic
//                    "TagA", // tag
//                    "OrderID001", // key
//                    ("Hello zebra mq").getBytes());// body
//
//            // 发送事务消息，LocalTransactionExecute的executeLocalTransactionBranch方法中执行本地逻辑
//            sendResult = transactionProducer.sendMessageInTransaction(msg, (Message msg1, Object arg) -> {
//                int value = 1;
//
//                // TODO 执行本地事务，改变value的值
//                // ===================================================
//                System.out.println("执行本地事务。。。完成");
//                if (arg instanceof Integer) {
//                    value = (Integer) arg;
//                }
//                // ===================================================
//
//                if (value == 0) {
//                    throw new RuntimeException("Could not find db");
//                } else if ((value % 5) == 0) {
//                    return LocalTransactionState.ROLLBACK_MESSAGE;
//                } else if ((value % 4) == 0) {
//                    return LocalTransactionState.COMMIT_MESSAGE;
//                }
//                return LocalTransactionState.ROLLBACK_MESSAGE;
//            }, 4);
//            System.out.println(sendResult);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sendResult.toString();
//    }
//
//    @RequestMapping(value = "/sendMsgOrder", method = RequestMethod.GET)
//    public void sendMsgOrder(String body) {
//        Message msg = new Message(topic, // topic
//                tag, // tag
//                UUID.randomUUID().toString(), // key
//                body.getBytes());// body
//        int i=0;
//        try {
//            defaultProducer.send(msg, new MessageQueueSelector() {
//                @Override
//                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
//                    System.out.println("MessageQueue" + arg);
//                    int index = ((Integer) arg) % mqs.size();
//                    return mqs.get(index);
//                }
//            }, i);// i==arg
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
