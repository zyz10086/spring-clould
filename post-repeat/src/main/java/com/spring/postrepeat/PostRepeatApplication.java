package com.spring.postrepeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.spring.postrepeat")
public class PostRepeatApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostRepeatApplication.class, args);
        System.out.println("启动成功");
    }

}
