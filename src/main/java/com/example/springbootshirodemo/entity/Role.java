package com.example.springbootshirodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @author: qianzhiyong
 * @date: 2020/10/12 17:11
 * @description: TODO
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Role {
    private String id;
    private String name;
    private Set<Permission> permissionSet;
}
