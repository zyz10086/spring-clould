package com.spring.postrepeat.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author wangxia
 * @date 2019/7/26 14:45
 * @Description:
 */
@Component
public class JedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";//PX表示超时时间是毫秒设置，EX表示超时时间是分钟设置
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 判断是否存在
     * @param key
     * @return
     */
    public boolean isExit(String key){
        Object tempValue=redisTemplate.opsForValue().get(key);
        return tempValue==null;
    }

    /**
     * 从redis中 根据key取出对应的value
     * @param key
     * @return
     */
    public Object getValue(String key){
        if(!StringUtils.isEmpty(key)){
            return redisTemplate.opsForValue().get(key);
        }
        return null;
    }

    public boolean deleteKey(String key){
        return redisTemplate.delete(key);
    }

    public void addKey(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

}
