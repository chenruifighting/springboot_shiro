package com.example.springboot_shiro.Mapper;

import com.example.springboot_shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 陈睿
 * @date 2020-06-06 9:47
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where name=#{name}")
    User findPwdByName(String name);
}
