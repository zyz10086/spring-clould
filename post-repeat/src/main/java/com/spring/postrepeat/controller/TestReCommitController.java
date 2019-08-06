package com.spring.postrepeat.controller;

import com.spring.postrepeat.annotation.PreventRepetitionAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangxia
 * @date 2019/7/26 14:36
 * @Description:
 */
@RestController
public class TestReCommitController {

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    @PreventRepetitionAnnotation
    public String add(String username,HttpServletRequest request){
        try {
            System.out.println("添加用户:"+username);
            Thread.sleep(5000);
            System.out.println(username+"添加成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "保存成功";
    }

}
