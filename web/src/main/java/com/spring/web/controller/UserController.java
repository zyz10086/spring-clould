package com.spring.web.controller;

import com.spring.web.entity.UserEntity;
import com.spring.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author wangxia
 * @date 2019/8/30 16:06
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/search")
    public List<UserEntity> search(){
        return userMapper.selectByMap(new HashMap<>());
    }

    @GetMapping("/getById")
    public UserEntity getById(Long id){
        return userMapper.selectById(id);
    }

    @GetMapping("/getByUsername")
    public List<UserEntity> getByUsername(String username){
//        QueryWrapper<UserEntity> query=new QueryWrapper<UserEntity>();
//        query.eq("username",username);
//        return userMapper.selectList(query);
        return userMapper.getByUsername(username);
    }

    @PutMapping("/save")
    public String save(UserEntity userEntity){
        userMapper.insert(userEntity);
        return "添加成功";
    }

}
