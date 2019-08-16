package com.spring.clouldsleuth;

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
public class ClouldSleuthApplication {

    Logger logger= LoggerFactory.getLogger(ClouldSleuthApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClouldSleuthApplication.class, args);
    }

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/hi")
    public String callHome(){
        logger.info("测试请求hi");
        return restTemplate.getForObject("http://localhost:9001/miya", String.class);
    }
    @RequestMapping("/hiEnd")
    public String info(){
        logger.info("测试请求hiEnd");
        return "i'm service-hi hiEnd()";
    }

}
