package com.binobook.service;

import com.binobook.dao.CustomerMapper;
import com.binobook.po.Customer;
import com.binobook.query.CustomerQuery;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerServiceTest {
    @Resource
    private CustomerService customerService;

    @Resource
    private CustomerMapper customerMapper;
    @Test
    public void queryCustomerByParams() {
        CustomerQuery customerQuery = new CustomerQuery("scott","333@123.com","13456789010");
        this.customerService.queryCustomerByParams(customerQuery);

    }

    @Test
    @Transactional
    @Rollback(true)
    public void addUser() {
        Customer customer = new Customer();
        customer.setId(102);
        customer.setName("zhaoliu000");
        customer.setEmail("126@126.com");
        customer.setPhone("13322456789");
        this.customerService.addUser(customer);
//        System.out.println(this.adminUserService.selectByParams(myu));
        assertEquals("zhaoliu000",customer.getName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void updateCustomer() {
        Customer customer = new Customer();
        customer.setName("yueyang");
        customer.setId(1);
        customer.setEmail("124@125.com");
        customer.setPhone("13324567890");
//        Integer[] roles = new Integer[]{1,2,4};
        this.customerService.updateCustomer(customer);
        Customer temp = this.customerService.selectByPrimaryKey(customer.getId());
        assertEquals(temp.getId(), customer.getId());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteCustomer() {
        Integer[] ids = new Integer[]{7,8,9};
        for (Integer id:ids){
            this.customerService.deleteByPrimaryKey(id);

        }
        Integer[] non_exist_ids = new Integer[]{104,78};
        try {
            for (Integer non_exist_id: non_exist_ids){
                this.customerService.deleteByPrimaryKey(non_exist_id);

            }
        }catch (Throwable exception) {
            assertEquals( "Deletion Failed！" , exception.getMessage() ) ;
        }
    }
}