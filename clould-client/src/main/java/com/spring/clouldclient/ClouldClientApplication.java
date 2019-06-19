package com.spring.clouldclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class ClouldClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClouldClientApplication.class, args);
    }

    @RequestMapping(value = "hello",method = RequestMethod.POST)
    public static String sayHello(@RequestParam(required = true) String username){
        return username+"你好";
    }

}
