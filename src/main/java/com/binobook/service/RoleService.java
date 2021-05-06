package com.binobook.service;

import com.binobook.base.BaseService;
import com.binobook.dao.ModuleMapper;
import com.binobook.dao.UserRoleMapper;
import com.binobook.po.AdminUserRole;
import com.binobook.po.Permission;
import com.binobook.po.Role;
import com.binobook.dao.PermissionMapper;
import com.binobook.dao.RoleMapper;
import com.binobook.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class RoleService extends BaseService<Role, Integer> {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private ModuleMapper moduleMapper;

    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return roleMapper.queryAllRoles(userId);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(Role role) {
        /* 1. Params */
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "RoleName required！");
        // Query role records by roleName
        Role temp = roleMapper.selectByRoleName(role.getRoleName());
        // verify if role exists
        AssertUtil.isTrue(temp != null, "Role name already exists！");

        /* 2. set defaault params  */

        role.setCreateDate(new Date());

        role.setUpdateDate(new Date());

        /* 3. Addition operation performed，verify affected records */
        AssertUtil.isTrue(roleMapper.insertSelective(role) < 1, "Adding Role Failed！");
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole(Role role) {
        /* 1. Check Params */
        // Role Name        not null and unique
        AssertUtil.isTrue(null == role.getId(), "RoleName required！");
        // Query Roel By Id
        Role temp = roleMapper.selectByPrimaryKey(role.getId());
        // Verify if role exists
        AssertUtil.isTrue(null == temp, "Record do not exists");

        // Role Name        not null and unique
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "Role Name is empty！");
        // Query role records by roleName
        temp = roleMapper.selectByRoleName(role.getRoleName());
        // Verify if role records exists; if yes, means can be set , else if different, can be updated
        AssertUtil.isTrue(null != temp && (!temp.getId().equals(role.getId())), "Role Name already exists，cannot be used！");

        /* 2. default params */
        role.setUpdateDate(new Date());

        /* 3. Update operation performed，verify affected records  */
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role) < 1, "Update failure！");
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Integer roleId) {

        AssertUtil.isTrue(null == roleId, "Role Required！");

        Role role = roleMapper.selectByPrimaryKey(roleId);

        AssertUtil.isTrue(null == role, "No such role！");

        role.setUpdateDate(new Date());

        AssertUtil.isTrue(roleMapper.deleteByPrimaryKey(roleId) < 1, "Deleting Failed！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addGrant(Integer roleId, Integer[] mIds) {
        // 1. Query permissions by roleId
        Integer count = permissionMapper.countPermissionByRoleId(roleId);
        // 2. if permission exists, delete the permission records of this role
        if (count > 0) {
            permissionMapper.deletePermissionByRoleId(roleId);
        }
        // 3. if permission exists, add permission records
        if (mIds != null &&  mIds.length > 0) {
            //  define Permission list
            List<Permission> permissionList = new ArrayList<>();

            for(Integer mId: mIds) {
                Permission permission = new Permission();
                permission.setModuleId(mId);
                permission.setRoleId(roleId);
                permission.setAclValue(moduleMapper.selectByPrimaryKey(mId).getOptValue());
                permission.setCreateDate(new Date());
                permission.setUpdateDate(new Date());
                permissionList.add(permission);
            }

            // Batch Update operation performed，verify affected records
            AssertUtil.isTrue(permissionMapper.insertBatch(permissionList) != permissionList.size(), "AddGrant failed！");
        }
    }


}
