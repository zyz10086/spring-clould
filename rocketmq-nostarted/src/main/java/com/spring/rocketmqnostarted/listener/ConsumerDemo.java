package com.spring.rocketmqnostarted.listener;

import com.spring.rocketmqnostarted.entity.RocketmqEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerDemo {
    @EventListener(condition = "#event.msgs[0].topic=='old_test' && #event.msgs[0].tags=='test'")
    public void rocketmqMsgListen(RocketmqEvent event) {
        try {
            for(MessageExt tmp:event.getMsgs()){
                System.out.println("监听到一个消息达到：" + new String(tmp.getBody()));
            }
        } catch (Exception e) {
           log.error("获取信息失败",e);
        }
    }
}
