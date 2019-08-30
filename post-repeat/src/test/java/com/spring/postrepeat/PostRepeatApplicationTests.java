package com.spring.postrepeat;

import com.spring.postrepeat.redis.JedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepeatApplicationTests {
    @Autowired
    private JedisUtils jedisUtils;
    @Test
    public void contextLoads() {

        jedisUtils.setAdd("name","zhangsan");
        jedisUtils.setAdd("name","lisi");
        jedisUtils.setAdd("name","wangwu");

        jedisUtils.setAdd("name2","zhangsan2");
        jedisUtils.setAdd("name2","lisi");
        jedisUtils.setAdd("name2","wangwu2");

        for(Object name:jedisUtils.findDifference("name2","name")){
            System.out.println(name.toString());
        }

    }

}
