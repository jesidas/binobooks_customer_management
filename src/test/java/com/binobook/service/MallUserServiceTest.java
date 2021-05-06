package com.binobook.service;

import com.binobook.dao.MallUserMapper;
import com.binobook.model.MallUserModel;
import com.binobook.po.MallUser;
import com.binobook.query.MallUserQuery;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MallUserServiceTest {

    @Resource
    private MallUserService mallUserService;

    @Resource
    private MallUserMapper mallUserMapper;

    @Test
    public void userLogin() {
        MallUserModel myc = new MallUserModel();
        myc=this.mallUserService.userLogin("admin","12");
        System.out.println(myc.toString());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void updatePassWord() {
        MallUser mallUser = new MallUser();
        mallUser.setId(10);
        this.mallUserService.updatePassWord(mallUser.getId(),"12","12345","12345");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void addUser() {
        MallUser mallUser = new MallUser();
        mallUser.setName("lisi000");
        mallUser.setEmail("126@126.com");
        mallUser.setPhone("13322456789");
        this.mallUserService.addUser(mallUser);
//        System.out.println(this.adminUserService.selectByParams(myu));
        assertEquals("lisi000",mallUser.getName());

    }

    @Test
    @Transactional
    @Rollback(true)
    public void updateUser() {
        MallUser mallUser = new MallUser();
        mallUser.setId(1);
        mallUser.setName("yueyang");
        mallUser.setEmail("124@125.com");
        mallUser.setPhone("13324567890");
//        Integer[] roles = new Integer[]{1,2,4};
        this.mallUserService.updateUser(mallUser);
        MallUser temp = this.mallUserMapper.queryUserByName(mallUser.getName());
        assertEquals(temp.getName(), mallUser.getName());
//        this.mallUserService.relationUserRole(mallUser.getId(), mallUser.getRoleIds());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteByIds() {
        Integer[] ids = new Integer[]{4,6,7};
        this.mallUserService.deleteByIds(ids);
        Integer[] non_exist_ids = new Integer[]{104,78};
        try {
            this.mallUserService.deleteByIds(non_exist_ids);
            System.out.println("-----------------------------------Delete Test Successful!");
        }catch (Throwable exception) {
            assertEquals( "Deletion Failed！" , exception.getMessage() ) ;
            System.out.println("-----------------------------------Delete Test Successful!");
        }
    }

    @Test
    @Transactional
    @Rollback(true)
    public void basicInfo() {
        MallUserQuery mallUserQuery = new MallUserQuery("admin","22222@123.com","13135678900");
        mallUserQuery.setName("admin");
        mallUserQuery.setPhone("13135678900");
        Map<String, Object> result = this.mallUserService.basicInfo(mallUserQuery, 10);
        System.out.println("-----------------------------------result="+result.toString());
    }
}