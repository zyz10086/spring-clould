package com.spring.clouldsleuthstudy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxia
 * @date 2019/7/9 17:17
 * @Description:
 */
@RestController
@RequestMapping("server3")
public class ZipkinBraveController3 {

    /**
     * @Description: 第三步调用
     * @Param:
     * @return:  字符串
     * @Author: Mr.Yang
     * @Date: 2018/7/3
     */
    @RequestMapping("/zipkin")
    public String service1() throws Exception {
        Thread.sleep(200);

        return "你好,欢迎进入Zipkin的世界";
    }

}
