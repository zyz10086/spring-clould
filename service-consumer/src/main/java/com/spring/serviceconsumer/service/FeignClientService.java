package com.spring.serviceconsumer.service;

import com.spring.serviceconsumer.deal.SchedualServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "client-hi",fallback = SchedualServiceHiHystric.class)
public interface FeignClientService {

    @RequestMapping(value = "hello",method = RequestMethod.POST)
    String sayHello(@RequestParam(value = "username") String name);

}
