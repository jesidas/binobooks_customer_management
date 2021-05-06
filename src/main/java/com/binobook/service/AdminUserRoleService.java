package com.binobook.service;

import com.binobook.base.BaseService;
import com.binobook.po.AdminUserRole;
import com.binobook.dao.UserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserRoleService extends BaseService<AdminUserRole, Integer> {

    @Resource
    private UserRoleMapper userRoleMapper;
}
