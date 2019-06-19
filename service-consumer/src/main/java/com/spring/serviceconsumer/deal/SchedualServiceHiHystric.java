package com.spring.serviceconsumer.deal;

import com.spring.serviceconsumer.service.FeignClientService;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements FeignClientService {

    @Override
    public String sayHello(String name) {
        return "sorry "+name;
    }
}


