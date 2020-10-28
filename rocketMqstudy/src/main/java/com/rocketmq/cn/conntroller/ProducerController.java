package com.rocketmq.cn.conntroller;

import com.alibaba.fastjson.JSONObject;
import com.rocketmq.cn.customer.Producer;
import com.rocketmq.cn.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProducerController {

    @Resource
    private Producer producer;

    @GetMapping("/send")
    public String send(String msg){
        producer.sendMsg("wtest",msg);
        return "发送结束" ;
    }

    @GetMapping("/sendUser")
    public String sendUser(String username,String sex,int age){
        User user=new User();
        user.setUsername(username);
        user.setSex(sex);
        user.setAge(age);
        producer.sendUser("w_user_test",user);
        return "发送结束" ;
    }

    @GetMapping("/sendJSON")
    public String sendJson(String msg){
        JSONObject json=new JSONObject();
        json.put("say",msg);
        producer.sendJson("w_json_test",json);
        return "发送结束" ;
    }

}
