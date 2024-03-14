package com.hjong.one.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.hjong.one.enums.RoleType;
import com.hjong.one.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 * @author HJong
 * @version 1.0
 * @date 2024/3/13
 **/
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    UserMapper userMapper;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object Id, String loginType) {
        return null;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object Id, String loginType) {

        RoleType UserRole = userMapper.findUserById((Integer) Id).getRole();

        List<String> list = new ArrayList<String>();
        list.add(UserRole.toString());

        return list;
    }
}
