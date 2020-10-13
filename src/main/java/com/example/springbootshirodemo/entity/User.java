package com.example.springbootshirodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @author: qianzhiyong
 * @date: 2020/10/12 17:12
 * @description: TODO
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private String id;
    private String username;
    private String password;
    private Set<Role> roleSet;
}
