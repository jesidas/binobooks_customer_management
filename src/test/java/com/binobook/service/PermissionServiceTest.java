package com.binobook.service;

import com.binobook.dao.PermissionMapper;
import com.binobook.po.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PermissionServiceTest {

    @Resource
    private PermissionService permissionService;

    @Resource
    private PermissionMapper permissionMapper;

    @Test
    @Transactional
    @Rollback(true)
    public void queryUserHasRoleHasPermissionByUserId() {
        AdminUser adminUser = new AdminUser();
        adminUser.setId(10);
        this.permissionService.queryUserHasRoleHasPermissionByUserId(10);
        AdminUser non_exist_adminUser = new AdminUser();
        non_exist_adminUser.setId(200);
        try {
            this.permissionService.queryUserHasRoleHasPermissionByUserId(200);
        }catch (Throwable exception) {
            assertEquals( "Non-existUser！" , exception.getMessage() ) ;
        }
    }
}