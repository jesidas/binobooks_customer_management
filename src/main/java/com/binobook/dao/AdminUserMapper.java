package com.binobook.dao;

import com.binobook.base.BaseMapper;
import com.binobook.po.AdminUser;

import java.util.List;
import java.util.Map;


public interface AdminUserMapper extends BaseMapper<AdminUser, Integer> {


    public AdminUser queryUserByName(String userName);


}