package com.spring.clouldsleuthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

//@EnableZipkinServer
@SpringBootApplication
@EnableZipkinStreamServer
public class ClouldSleuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClouldSleuthServerApplication.class, args);
    }

}
