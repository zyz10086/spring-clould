package com.spring.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component
public class MyFilter extends ZuulFilter {
//    返回一个字符串代表过滤器的类型，
//    pre：路由之前,routing：路由之时,post： 路由之后,error：发送错误调用
    @Override
    public String filterType() {
        return "pre";
    }

    //过滤的顺序  过滤顺序
    @Override
    public int filterOrder() {
        return 0;
    }

    //是否需要过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //过滤器的具体逻辑
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println("请求路径:"+request.getRequestURI());
        return null;
    }
}
