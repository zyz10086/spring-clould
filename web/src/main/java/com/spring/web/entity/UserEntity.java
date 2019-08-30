package com.spring.web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangxia
 * @date 2019/8/30 16:09
 * @Description:
 */
@Data
@TableName("user")
public class UserEntity extends Model {

    @TableId("userId")
    private Long userId;
    @TableField("username")
    private String username;
    @TableField("userage")
    private Integer userage;
    @TableField("birthday")
    private String birthday;
    @TableField("phoneNum")
    private String phoneNum;
    @TableField("address")
    private String address;
    @TableField("remark")
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }
}
