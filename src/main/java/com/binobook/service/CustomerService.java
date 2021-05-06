package com.binobook.service;

import com.binobook.base.BaseService;
import com.binobook.po.Customer;
import com.binobook.po.OrderItems;
import com.binobook.po.Orders;
import com.binobook.query.CustomerQuery;
import com.binobook.query.OrderItemsQuery;
import com.binobook.query.OrdersQuery;
import com.binobook.utils.Md5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.binobook.dao.CustomerMapper;
import com.binobook.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CustomerService extends BaseService<Customer,Integer> {

    @Resource
    private CustomerMapper customerMapper;
     /**
     * Query Users List
     */
    public Map<String, Object> queryCustomerByParams(CustomerQuery customerQuery) {
        System.out.println("CustomerService.queryCustomerByParams says: customerQuery:"+customerQuery.toString());
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(customerQuery.getPage(), customerQuery.getLimit());

        PageInfo<Customer> pageInfo = new PageInfo<>(customerMapper.selectByParams(customerQuery));
        System.out.println("CustomerService.queryCustomerByParams says: customerQuery:"+customerQuery.toString());

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());

        return map;
    }


    //return throwable because of adding test it originally returns viod
    @Transactional(propagation = Propagation.REQUIRED)
    public Throwable addUser(Customer customer) {
        System.out.println("Service.addUser says01: "+ customer.toString());

        checkUserParams(customer.getName(), customer.getEmail(), customer.getPhone(), null);

        customer.setCreateDate(new Date());
        customer.setUpdateDate(new Date());


        customer.setUserPassword(Md5Util.encode("123456"));
        System.out.println("Service.addUser says02: "+ customer.toString());


        AssertUtil.isTrue(customerMapper.insertSelective(customer) < 1, "Fail to Add！");
        System.out.println("Service.addUser says03: "+ customer.toString());


        System.out.println("Service.addUser says04: "+ customer.toString());

        return null;
    }



    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomer(Customer customer) {
        System.out.println("line134:CustomerService.updateCustomer says: customer="+customer.toString());

        AssertUtil.isTrue(null == customer.getId(), "Empty Input！");

        Customer temp = customerMapper.selectByPrimaryKey(customer.getId());
        System.out.println("line139:CustomerService.updateCustomer says: temp="+temp.toString());

        AssertUtil.isTrue(null == temp, "No such Record！");

        checkUserParams(customer.getName(), customer.getEmail(), customer.getPhone(),customer.getId());

        customer.setUpdateDate(new Date());
         System.out.println("line153: CustomerService.updateCustomer says: temp="+customer.toString());

        AssertUtil.isTrue(customerMapper.updateByPrimaryKeySelective(customer) != 1, "Fail to update！");
        System.out.println("line156: CustomerService.updateCustomer says: updateFinished");
    }


    private void checkUserParams(String name, String email, String phone, Integer userId) {

        AssertUtil.isTrue(StringUtils.isBlank(name), "User Name Required！");

        Customer temp = customerMapper.queryCustomerByName(name);

        AssertUtil.isTrue(null != temp && !(temp.getId().equals(userId)), "User Name ALready Exist！Please reenter!");

        AssertUtil.isTrue(StringUtils.isBlank(email), "Email Required！");

        AssertUtil.isTrue(StringUtils.isBlank(phone), "Phone Required！");

    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomer(Integer id) {

        AssertUtil.isTrue(null == id, "Empty Input！");

        Customer customer = customerMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null == customer, "No Such Record！");

        customerMapper.deleteByPrimaryKey(id);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByIds(Integer[] ids) {
        // Non-Empty Verification
        AssertUtil.isTrue(ids == null || ids.length == 0, "No Such Records！");
        System.out.println("CustomerService.deleteByIds says: ids="+ids.toString());
        AssertUtil.isTrue(customerMapper.deleteBatch(ids) != ids.length, "Deletion Failed！");

    }

    public Map<String, Object> listAllCustomerOrders() {
        Map<String,Object> result = new HashMap<String,Object>();
        System.out.println("I am CustomerService.listAllCustomerOrders!");
        List<Orders> orderLists = (List<Orders>) customerMapper.listAllCustomerOrders();
        System.out.println("CustomerService.queryMallUserOrderlistsByID says: results = "+orderLists);
        OrdersQuery ordersQuery = new OrdersQuery();
        PageHelper.startPage(ordersQuery.getPage(), ordersQuery.getLimit());
        PageInfo<Orders> pageInfo =new PageInfo<Orders>(orderLists);
        result.put("count",10);
        result.put("data",pageInfo.getList());
        result.put("code",0);
        result.put("msg","");
        return result;
    }

    public Map<String, Object> queryOrderDetailsByParams(Integer id) {
        Map<String, Object> map = new HashMap<>();

        System.out.println("CustomerService.queryOrderDetailsByParams says：I am here！");

        OrderItemsQuery orderItemsQuery = new OrderItemsQuery();
        PageHelper.startPage(orderItemsQuery.getPage(), orderItemsQuery.getLimit());

        PageInfo<OrderItems> pageInfo = new PageInfo<OrderItems>(customerMapper.queryOrderDetailsByParams(id));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        System.out.println("CustomerService.queryOrderDetailsByParams says：the map is "+map.toString());
        return map;
    }
}


