package com.spring.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.web.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangxia
 * @date 2019/8/30 16:03
 * @Description:
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    @Select("select * from user where userId=#{id}")
    public UserEntity getByIdTest(@Param("id") Long id);

    public List<UserEntity> getByUsername(@Param("username") String username);

}
