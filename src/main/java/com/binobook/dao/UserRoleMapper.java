package com.binobook.dao;

import com.binobook.base.BaseMapper;
import com.binobook.po.AdminUserRole;

public interface UserRoleMapper extends BaseMapper<AdminUserRole,Integer> {

    // 根据用户ID查询用户角色记录
    Integer countUserRoleByUserId(Integer userId);

    // 根据用户ID删除用户角色记录
    Integer deleteUserRoleByUserId(Integer userId);
}