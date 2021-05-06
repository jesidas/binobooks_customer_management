package com.binobook.dao;

import com.binobook.base.BaseMapper;
import com.binobook.po.AdminUser;
import com.binobook.po.MallUser;
import com.binobook.po.Orders;

import java.util.List;
import java.util.Map;

/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
public interface MallUserMapper extends BaseMapper<MallUser, Integer> {

    // 通过用户名查询用户记录，返回用户对象
    public MallUser queryUserByName(String userName);

    // 查询所有的客户经理
    List<Orders> queryMallUserOrderlistsByID(Integer id);
    List<Orders> queryOrderDetailByID(Integer id);

    public int addUser(MallUser mallUser);
}