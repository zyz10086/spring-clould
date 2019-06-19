package com.example.clouldserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ClouldServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClouldServerApplication.class, args);
    }

}
