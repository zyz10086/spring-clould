package com.spring.clouldsentinel.resource;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.spring.clouldsentinel.util.ExceptionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    @GetMapping("/test")
    /**
     * value  对应sentinel资源名
     * blockHandler 处理异常的方法名
     * 函数访问范围需要是 public
     * 返回类型需要与原方法相匹配
     * 参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException
     * blockHandlerClass  异常处理类名
     */
    @SentinelResource(value = "hello", blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class)
    public String test() {
        return "测试限流成功";
    }

    @RequestMapping("/sentinel/hello")
    public String hello() {
        //使用原始方式进行频率限制
        Entry entry = null;
        try {
            entry = SphU.entry("resource2");
            return "测试默认限流";
        } catch (BlockException e1) {
           return "超过频率控制啦.....";
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }
    //通过注解限流并自定义限流逻辑
    @SentinelResource(value = "resource2", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    @RequestMapping("/sentinel/test2")
    public String test2() {
        return  "测试自定义限流";
    }


}
