package com.spring.clustersentinelserver.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    @SentinelResource("resource")
    public String sayHello(){
        return  "你好啊";
    }
}
