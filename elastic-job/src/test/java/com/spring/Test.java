package com.spring;

import com.spring.anno.UseCases;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Method;

@SpringBootApplication
public class Test {
    public static void main(String[] args) {
        //手动触发注解
//        trackUseCases(PasswordUtils.class);
        SpringApplication.run(Test.class,args);
    }

    /**
     * 可以放置在注解中
     * @param cl
     */
    public static void trackUseCases(Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) {
            //获得注解的对象
            UseCases uc = m.getAnnotation(UseCases.class);
            if (uc != null) {
                System.out.println("Found Use Case:" + uc.id() + " "
                        + uc.description());
//                useCases.remove(new Integer(uc.id()));
            }
        }
    }
}
