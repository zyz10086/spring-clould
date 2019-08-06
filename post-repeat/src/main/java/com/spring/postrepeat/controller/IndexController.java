package com.spring.postrepeat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wangxia
 * @date 2019/7/30 10:50
 * @Description:
 */
@Controller
public class IndexController {

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String toIndex(HttpServletRequest request, Map<String,Object> map){
        return "index";
    }

    @PostMapping(value = "/toPay")
    @ResponseBody
    public ModelAndView toPay(String username, String goodsName){
        ModelAndView modelAndView=new ModelAndView();
        //对username+goodsName进行编码，保证唯一性
        modelAndView.addObject("token",username+goodsName);
        modelAndView.addObject("username",username);
        modelAndView.addObject("goodsName",goodsName);
        modelAndView.setViewName("/form");
        return modelAndView;
    }

}
