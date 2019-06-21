package com.spring.servicegateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.spring.servicegateway.handle.JsonSentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class GatewayConfiguration {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }


    /**
     * 配置SentinelGatewayBlockExceptionHandler，限流后异常处理
     * @return
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public JsonSentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new JsonSentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    /**
     * 配置SentinelGatewayFilter
     * @return
     */
    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    @PostConstruct
    public void doInit() {
        String resoutce="customized_api";
        initCustomizedApis(resoutce);
        initGatewayRules(resoutce);

        //方法2
        // 直接指定为某个route的id
//        initGatewayRules("path_route");

    }

    /**
     * 配置限流规则
     */
    private void initGatewayRules(String resource) {
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule(resource)
                .setCount(1) // 限流阈值
                .setIntervalSec(1) // 统计时间窗口，单位是秒，默认是 1 秒
        );
        GatewayRuleManager.loadRules(rules);
    }

    private void  initCustomizedApis(String resource){
        Set<ApiDefinition> definitions=new HashSet<>();
        ApiDefinition apiDefinition=new ApiDefinition(resource).
                setPredicateItems(new HashSet<ApiPredicateItem>(){{
                    // course完全匹配
                    add(new ApiPathPredicateItem().setPattern("/course"));
                    // blog/开头的
                    add(new ApiPathPredicateItem().setPattern("/blog/**")
                            .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_PREFIX));
                }});
        definitions.add(apiDefinition);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }

}
