package com.spring.web;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
@Slf4j
@MapperScan("com.spring.web.mapper")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        log.info("启动成功");
    }

    @RequestMapping("/test")
    public String login(){
        return "datas";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
