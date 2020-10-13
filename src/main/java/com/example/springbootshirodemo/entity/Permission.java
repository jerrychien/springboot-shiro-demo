package com.example.springbootshirodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: qianzhiyong
 * @date: 2020/10/12 17:10
 * @description: TODO
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Permission {
    private String id;
    private String name;
}
