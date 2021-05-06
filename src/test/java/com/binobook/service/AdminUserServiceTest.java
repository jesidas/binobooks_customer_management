package com.binobook.service;

import com.binobook.dao.AdminUserMapper;
import com.binobook.model.AdminUserModel;
import com.binobook.po.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class AdminUserServiceTest {

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private AdminUserMapper adminUserMapper;

    @Test
    public void userLogin() {
        AdminUserModel myu = new AdminUserModel();
        myu=this.adminUserService.userLogin("admin","123456");
        System.out.println(myu.toString());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void updatePassWord() {
        AdminUser adminUser = new AdminUser();
        adminUser.setId(10);
        this.adminUserService.updatePassWord(adminUser.getId(),"123456","12345","12345");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void addUser() {
        AdminUser adminUser = new AdminUser();
        adminUser.setName("lisi00");
        adminUser.setEmail("126@126.com");
        adminUser.setPhone("13322456789");
        AdminUser myu = this.adminUserService.addUser(adminUser);
//        System.out.println(this.adminUserService.selectByParams(myu));
        assertNull(myu);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void updateUser() {
        AdminUser adminUser = new AdminUser();
        adminUser.setId(13);
        adminUser.setName("yueyang");
        adminUser.setEmail("124@125.com");
        adminUser.setPhone("13324567890");
        Integer[] roles = new Integer[]{1,2,3};
        this.adminUserService.updateUser(adminUser);
        AdminUser temp = this.adminUserMapper.queryUserByName(adminUser.getName());
        assertEquals(temp.getName(), adminUser.getName());
        this.adminUserService.relationUserRole(adminUser.getId(), adminUser.getRoleIds());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteByIds() {
        Integer[] ids = new Integer[]{42,45,47};
        this.adminUserService.deleteByIds(ids);
        Integer[] non_exist_ids = new Integer[]{104,78};
        try {
            this.adminUserService.deleteByIds(non_exist_ids);
        }catch (Throwable exception) {
            assertEquals( "Deletion Failed！" , exception.getMessage() ) ;
        }
   }
}