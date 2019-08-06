package com.spring.postrepeat.aop;

import com.spring.postrepeat.redis.JedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wangxia
 * @date 2019/7/25 16:51
 * @Description: 防止重复提交操作aop类
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class PreventRepetitionAspect {

    //redisUtils
    @Autowired
    private JedisUtils jedisUtils;

    private static final String PARAM_TOKEN = "token";
    private static final String PARAM_TOKEN_FLAG = "tokenFlag";

    @Around(value = "@annotation(com.spring.postrepeat.annotation.PreventRepetitionAnnotation)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = null;
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null && args[i] instanceof HttpServletRequest) {
                    HttpServletRequest request = (HttpServletRequest) args[i];
                    HttpSession session = request.getSession();
                    String url=request.getRequestURI();
                    if(url.equals("toPay")){
                        //判断token已经存在
                        result=isExit(joinPoint,request);
                    }else if(url.equals("add")){
                        //存入token
                        result=isRepeat(joinPoint,request);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object isExit(ProceedingJoinPoint joinPoint, HttpServletRequest request) throws Throwable {
        String username=request.getParameter("username");
        String goodsName=request.getParameter("goodsName");
        String key=username+goodsName;
        if(jedisUtils.isExit(key)){
            return "不能重复提交";
        }
        return joinPoint.proceed();
    }

    public Object isRepeat(ProceedingJoinPoint joinPoint, HttpServletRequest request) throws Throwable {
        String token=request.getParameter("goodsName");
        String username=request.getParameter("username");
        String goodsName=request.getParameter("goodsName");
        String key=username+goodsName;
        Object tempValue=jedisUtils.getValue(key);
        if(tempValue==null || !tempValue.toString().equals(token)){
            return "不能重复提交";
        }
        jedisUtils.addKey(key,token);
        Object result=joinPoint.proceed();
        jedisUtils.deleteKey(key);
        return result;
    }
}
