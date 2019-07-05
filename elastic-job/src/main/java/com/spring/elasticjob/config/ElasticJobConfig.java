package com.spring.elasticjob.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.spring.elasticjob.job.MyElasticJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticJobConfig {


    @Autowired
    ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Bean
    public MyElasticJob myElasticJob(){
        return new MyElasticJob();
    }

    //参数加final的作用
    //第一种情况，修饰基本类型（非引用类型）。这时参数的值在方法体内是不能被修改的，即不能被重新赋值。否则编译就通不过。
    //修饰引用类型。这时参数变量所引用的对象是不能被改变的。作为引用的拷贝，参数在方法体里面不能再引用新的对象。
    @Bean(initMethod = "init")
    public JobScheduler workFlowJobScheduler(final MyElasticJob myElasticJob,
                                             @Value("${myElasticJob.cron}") final String cron,
                                             @Value("${myElasticJob.shardingTotalCount}") final int shardingTotalCount,
                                             @Value("${myElasticJob.shardingItemParameters}") final String shardingItemParameters) {
        return new SpringJobScheduler(myElasticJob, zookeeperRegistryCenter, getLiteJobConfiguration(myElasticJob.getClass(), cron, shardingTotalCount, shardingItemParameters));
    }


    public LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                        final String cron,
                                                        final int shardingTotalCount,
                                                        final String shardingItemParameters) {
        return LiteJobConfiguration.
                newBuilder(
                    new SimpleJobConfiguration(
                            JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount).
                                    shardingItemParameters(shardingItemParameters).build(),
                            jobClass.getCanonicalName())
                ).
                overwrite(true).//本地配置是否可覆盖注册中心配置如果可覆盖，每次启动作业都以本地配置为准
                build();
    }

}
