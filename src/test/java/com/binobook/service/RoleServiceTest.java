package com.binobook.service;

import com.binobook.dao.ModuleMapper;
import com.binobook.dao.RoleMapper;
import com.binobook.po.AdminUser;
import com.binobook.po.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoleServiceTest {
    @Resource
    private RoleService roleService;

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private ModuleMapper moduleMapper;

    @Test
    @Transactional
    @Rollback(true)
    public void queryAllRoles() {
        AdminUser adminUser = new AdminUser();
        adminUser.setId(10);
        this.roleService.queryAllRoles(adminUser.getId());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void addRole() {
        Role role = new Role();
        role.setId(30);
        role.setRoleName("frontDesk");
        this.roleService.addRole(role);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void updateRole() {
        Role role = new Role();
        role.setId(17);
        role.setRoleName("Test Engineers");
        this.roleService.updateRole(role);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteRole() {
        Role role = new Role();
        role.setId(2);
        this.roleService.deleteRole(role.getId());
        try {
            this.roleService.deleteRole(35);
        }catch (Throwable exception) {
            assertEquals( "No such role！" , exception.getMessage() ) ;
        }

    }
}