package com.spring.clouldselueth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class ClouldSelueth2Application {
    Logger logger= LoggerFactory.getLogger(ClouldSelueth2Application.class);
    public static void main(String[] args) {
        SpringApplication.run(ClouldSelueth2Application.class, args);
    }

    @RequestMapping("/miya")
    public String info(){
        logger.info("测试请求miya");
        return restTemplate.getForObject("http://localhost:9000/hiEnd",String.class);
    }

    @Autowired
    private RestTemplate restTemplate;
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
