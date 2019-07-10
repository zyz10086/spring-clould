package com.spring.clouldsleuthstudy.config;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import com.github.kristofa.brave.okhttp.BraveOkHttpRequestResponseInterceptor;
import com.github.kristofa.brave.servlet.BraveServletFilter;
import lombok.Data;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.kristofa.brave.Brave.Builder;
import static com.github.kristofa.brave.http.HttpSpanCollector.Config;
import static com.github.kristofa.brave.http.HttpSpanCollector.create;

/**
 * @author wangxia
 * @date 2019/7/9 17:10
 * @Description:
 */
@Data
@Configuration
public class ZipkinProperties {
    /**
     * 服务名称
     */
    @Value("${zipkin.serviceName}")
    private String serviceName;

    /**
     * zipkin地址
     */
    @Value("${zipkin.url}")
    private String url;

    /**
     * 连接时间
     */
    @Value("${zipkin.connectTimeout}")
    private int connectTimeout;

    /**
     * 读取时间
     */
    @Value("${zipkin.readTimeout}")
    private int readTimeout;

    /**
     * 每间隔多少秒执行一次Span信息上传
     */
    @Value("${zipkin.flushInterval}")
    private int flushInterval;

    /**
     * 是否启动压缩
     */
    @Value("${zipkin.compressionEnabled}")
    private boolean compressionEnabled;

    /**
     * @Description: span（一次请求信息或者一次链路调用）信息收集器
     * @Param:
     * @return: SpanCollector 控制器
     * @Author: Mr.Yang
     * @Date: 2018/7/3 0002
     */
    @Bean
    public SpanCollector spanCollector() {
        Config config = Config.builder()
                // 默认false，span在transport之前是否会被gzipped
                .compressionEnabled(compressionEnabled)
                .connectTimeout(connectTimeout)
                .flushInterval(flushInterval)
                .readTimeout(readTimeout)
                .build();
        return create(url, config, new EmptySpanCollectorMetricsHandler());
    }

    /**
     * @Description: 作为各调用链路，只需要负责将指定格式的数据发送给zipkin
     * @Param:
     * @return:
     * @Author: Mr.Yang
     * @Date: 2018/7/3 0002
     */
    @Bean
    public Brave brave(SpanCollector spanCollector) {
        //调用服务的名称
        Builder builder = new Builder(serviceName);
        builder.spanCollector(spanCollector);
        //采集率
        builder.traceSampler(Sampler.ALWAYS_SAMPLE);
        return builder.build();
    }


    /**
     * @Description: 设置server的（服务端收到请求和服务端完成处理，并将结果发送给客户端）过滤器
     * @Param:
     * @return: 过滤器
     * @Author: Mr.Yang
     * @Date: 2018/7/3 0002
     */
    @Bean
    public BraveServletFilter braveServletFilter(Brave brave) {
        BraveServletFilter filter = new BraveServletFilter(brave.serverRequestInterceptor(),
                brave.serverResponseInterceptor(), new DefaultSpanNameProvider());
        return filter;
    }

    /**
     * @Description: 设置client的（发起请求和获取到服务端返回信息）拦截器
     * @Param:
     * @return: OkHttpClient 返回请求实例
     * @Author: Mr.Yang
     * @Date: 2018/7/3 0002
     */
    @Bean
    public OkHttpClient okHttpClient(Brave brave) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new BraveOkHttpRequestResponseInterceptor(
                        brave.clientRequestInterceptor(),
                        brave.clientResponseInterceptor(),
                        new DefaultSpanNameProvider())).build();
        return httpClient;
    }
}
