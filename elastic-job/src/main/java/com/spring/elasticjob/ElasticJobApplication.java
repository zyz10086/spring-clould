package com.spring.elasticjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElasticJobApplication {

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:");
//        context.start();
        SpringApplication.run(ElasticJobApplication.class, args);
        System.out.println("启动成功");
    }

}
