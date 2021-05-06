package com.binobook.service;

import com.binobook.base.BaseService;
import com.binobook.model.AdminUserModel;
import com.binobook.po.AdminUser;
import com.binobook.po.AdminUserRole;
import com.binobook.dao.AdminUserMapper;
import com.binobook.dao.UserRoleMapper;
import com.binobook.query.AdminUserQuery;
import com.binobook.utils.AssertUtil;
import com.binobook.utils.Md5Util;
import com.binobook.utils.UserIDBase64;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.*;

@Service
public class AdminUserService extends BaseService<AdminUser, Integer> {

    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private UserRoleMapper userRoleMapper;


    public AdminUserModel userLogin(String name, String userPwd) {
        String userPassword = userPwd;
        // 1. Not null Verification for name and password
        checkLoginParams(name, userPassword);
        System.out.println("AdminUserService.userLogin says: name is"+name+" userPassword is"+userPassword);
        // 2. Query User By Name
        AdminUser adminUser = adminUserMapper.queryUserByName(name);
        System.out.println("AdminUserService.userLogin says: adminUser is: "+ adminUser);
        // 3. Not Null Verification
        AssertUtil.isTrue(adminUser == null, "User Not Exist！");

        // 4.Verify Password
        checkuserPassword(userPassword, adminUser.getuserPassword());

        // Return User Object
        return buildUserInfo(adminUser);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassWord(Integer userId, String oldPwd, String newPwd, String repeatPwd) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(userId);
        // Not Null Check
        AssertUtil.isTrue(null == adminUser, "No such user！");

        // Param Check
        checkPasswordParams(adminUser, oldPwd, newPwd, repeatPwd);

        // Send New Passowrd
        adminUser.setuserPassword(Md5Util.encode(newPwd));

        // Return
        AssertUtil.isTrue(adminUserMapper.updateByPrimaryKeySelective(adminUser) < 1, "Fail Update！");

    }

    /**
     * Check Params
     */
    private void checkPasswordParams(AdminUser adminUser, String oldPwd, String newPwd, String repeatPwd) {
        //  Not Null Checks
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd), "Old Password Required！");

        AssertUtil.isTrue(!adminUser.getuserPassword().equals(Md5Util.encode(oldPwd)), "Wrong Old Passwoerd！");

        AssertUtil.isTrue(StringUtils.isBlank(newPwd), "New Password Required！");

        AssertUtil.isTrue(oldPwd.equals(newPwd),"Idenntical Old and New Passowrd！");

        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd),"Confirm Password reuqired！");
        // 判断确认密码是否与新密码一致
        AssertUtil.isTrue(!newPwd.equals(repeatPwd), "Inconsistent Confirm and New Password！");

    }


    /**
     * Build User Info for Front End
     */
    private AdminUserModel buildUserInfo(AdminUser adminUser) {
        AdminUserModel adminUserModel = new AdminUserModel();
        // adminUserModel.setUserId(adminUser.getId());
        // 设置加密的用户ID
        adminUserModel.setUserIdStr(UserIDBase64.encoderUserID(adminUser.getId()));
        adminUserModel.setName(adminUser.getName());
        adminUserModel.setTrueName(adminUser.getTrueName());
        System.out.println("service.builduserInfo says:"+ adminUserModel.toString());
        return adminUserModel;
    }


    private void checkuserPassword(String userPassword, String pwd) {

        userPassword = Md5Util.encode(userPassword);

        AssertUtil.isTrue(!userPassword.equals(pwd), "Wrong Password！");
    }


    private void checkLoginParams(String name, String userPassword) {
        System.out.println("UserService.checkLoginParams says: name="+name);

        AssertUtil.isTrue(StringUtils.isBlank(name), "UserName Required！");

        AssertUtil.isTrue(StringUtils.isBlank(userPassword), "UserPassword Required！");
    }


    /**
     * Add User
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AdminUser addUser(AdminUser adminUser) {
        System.out.println("Service.addUser says01: "+ adminUser.toString());

        checkUserParams(adminUser.getName(), adminUser.getEmail(), adminUser.getPhone(), null);

        adminUser.setCreateDate(new Date());
        adminUser.setUpdateDate(new Date());

        adminUser.setuserPassword(Md5Util.encode("123456"));
        System.out.println("Service.addUser says02: "+ adminUser.toString());

        AssertUtil.isTrue(adminUserMapper.insertSelective(adminUser) < 1, "Fail to Add User！");
        System.out.println("Service.addUser says03: "+ adminUser.toString());


        /**User Role Relation Set Up
         *  userId
         *  roleIds
         */
        relationUserRole(adminUser.getId(), adminUser.getRoleIds());
        System.out.println("Service.addUser says04: "+ adminUser.toString());

        return null;
    }

    public void relationUserRole(Integer userId, String roleIds) {

        // Query User Role By Id
        Integer count = userRoleMapper.countUserRoleByUserId(userId);
        // Check if User Record Exists
        if (count > 0) {
            // Delete User Role if exists
            AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(userId) != count, "Fail to Assign Roles！");
        }

        // Verify RoleId Not Null
        if (StringUtils.isNotBlank(roleIds)) {
            // Batch Addition of user Role
            List<AdminUserRole> adminUserRoleList = new ArrayList<>();
            // Covert RoleId into Array
            String[] roleIdsArray = roleIds.split(",");
            // Iterate Array，Insert Role Object to List
            for (String roleId : roleIdsArray) {
                AdminUserRole adminUserRole = new AdminUserRole();
                adminUserRole.setRoleId(Integer.parseInt(roleId));
                adminUserRole.setUserId(userId);
                adminUserRole.setCreateDate(new Date());
                adminUserRole.setUpdateDate(new Date());
                // Addition into Sets
                adminUserRoleList.add(adminUserRole);
            }
            // Batch Addition
            AssertUtil.isTrue(userRoleMapper.insertBatch(adminUserRoleList) != adminUserRoleList.size(), "Fail to Assign Roles！");
        }

    }

    /**
     * Update User
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(AdminUser adminUser) {
        // Not Null Check
        System.out.println("Service.addUser says: adminUser="+ adminUser.toString());
        AssertUtil.isTrue(null == adminUser.getId(), "Empty Input！");
        // Query User By Id
        AdminUser temp = adminUserMapper.selectByPrimaryKey(adminUser.getId());
        // Not Null Check
        AssertUtil.isTrue(null == temp, "Record do not Exist！");
        // Params Check
        checkUserParams(adminUser.getName(), adminUser.getEmail(), adminUser.getPhone(), adminUser.getId());

        // Set Default Values
        adminUser.setUpdateDate(new Date());

        // Update Operation and Verification
        AssertUtil.isTrue(adminUserMapper.updateByPrimaryKeySelective(adminUser) != 1, "Update Failure！");


        /**User Role Relation Set Up
         *  userId
         *  roleIds
         */
        relationUserRole(adminUser.getId(), adminUser.getRoleIds());

    }


    /**
     *  Params Check
     */
    private void checkUserParams(String name, String email, String phone, Integer userId) {

        AssertUtil.isTrue(StringUtils.isBlank(name), "User Name Required！");

        AdminUser temp = adminUserMapper.queryUserByName(name);

        AssertUtil.isTrue(null != temp && !(temp.getId().equals(userId)), "User Name ALready Exist！");

        AssertUtil.isTrue(StringUtils.isBlank(email), "Email Required！");

        AssertUtil.isTrue(StringUtils.isBlank(phone), "Phone Number Required！");

//        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "手机号格式不正确！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByIds(Integer[] ids) {
        // Non-Empty Verification
        System.out.println("AdminUserService.deleteByIds says: ids="+ids.toString());
        AssertUtil.isTrue(ids == null || ids.length == 0, "No Such Records！");
        AssertUtil.isTrue(adminUserMapper.deleteBatch(ids) != ids.length, "Deletion Failed！");

        for (Integer userId : ids) {
            // Query User By Id
            Integer count  = userRoleMapper.countUserRoleByUserId(userId);
            if (count > 0) {
                AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(userId) != count, "Deletion Failed！");
            }
        }
    }

    /**
     * Admin Self-Info Update and Cancel Account Page
     */
    public Map<String, Object> basicInfo(AdminUserQuery adminUserQuery, Integer id){
        Map<String,Object> result = new HashMap<String,Object>();
        System.out.println("AdminUserService.basicInfo says loginUserIdStr="+id);
//        adminUserQuery.setId(id);
        System.out.println("AdminUserService.basicInfo says adminUserQuery="+ adminUserQuery.toString());
        List<AdminUser> temp = Collections.singletonList(adminUserMapper.selectByPrimaryKey(id));
        System.out.println("AdminUserService.basicInfo says temp="+temp.toString());
        PageHelper.startPage(adminUserQuery.getPage(), adminUserQuery.getLimit());
        PageInfo<AdminUser> pageInfo =new PageInfo<AdminUser>(temp);
        result.put("count",10);
        result.put("data",pageInfo.getList());
        result.put("code",0);
        result.put("msg","");
        return result;
    }


}
