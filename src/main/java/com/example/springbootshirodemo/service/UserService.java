package com.example.springbootshirodemo.service;

import com.example.springbootshirodemo.entity.Permission;
import com.example.springbootshirodemo.entity.Role;
import com.example.springbootshirodemo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: qianzhiyong
 * @date: 2020/10/12 17:16
 * @description: TODO
 */
@Slf4j
@Component
public class UserService {

    private static Map<String, User> map = new HashMap<>();

    static {
        Permission Permission1 = new Permission("1", "query");
        Permission Permission2 = new Permission("2", "add");
        Permission Permission3 = new Permission("3", "add:query");
        Permission Permission4 = new Permission("4", "add:update");
        Set<Permission> PermissionSet = new HashSet<>();
        PermissionSet.add(Permission1);
        PermissionSet.add(Permission3);
        Role role = new Role("1", "admin", PermissionSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = new User("1", "qianzhiyong", "123456", roleSet);
        map.put(user.getUsername(), user);

        Set<Permission> PermissionSet1 = new HashSet<>();
        PermissionSet1.add(Permission2);
        Role role1 = new Role("2", "user", PermissionSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User("2", "songkaiqin", "123456", roleSet1);
        map.put(user1.getUsername(), user1);
    }

    public User getUserByName(String username) {
        return getMapByName(username);
    }

    private User getMapByName(String userName) {
        return map.get(userName);
    }
}
