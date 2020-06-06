package com.example.springboot_shiro.entity;

import lombok.Data;

/**
 * @author 陈睿
 * @date 2020-06-06 9:44
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer perms;
}
