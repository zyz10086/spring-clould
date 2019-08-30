package com.spring.clouldconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ClouldConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClouldConfigServerApplication.class, args);
        System.out.println("启动成功");
    }
}
