package com.binobook.dao;

import com.binobook.base.BaseMapper;
import com.binobook.po.OrderItems;
import com.binobook.po.Orders;
import com.binobook.query.CustomerQuery;
import com.binobook.po.Customer;
import com.binobook.query.OrderItemsQuery;

import java.util.List;
import java.util.Map;

public interface CustomerMapper extends BaseMapper<Customer, Integer> {

    // 通过客户名称查询客户对象
    public Customer queryCustomerByName(String name);


    // 查询客户贡献数据
    List<Map<String,Object>> queryCustomerContributionByParams(CustomerQuery customerQuery);

    // 查询客户构成
    List<Map<String,Object>> countCustomerMake();

    List<Orders> listAllCustomerOrders();

    List<OrderItems> queryOrderDetailsByParams(Integer id);
}