package com.binobook.service;

import com.binobook.base.BaseService;

import com.binobook.dao.MallUserMapper;
import com.binobook.dao.UserRoleMapper;

import com.binobook.model.MallUserModel;


import com.binobook.po.MallUser;

import com.binobook.po.Orders;
import com.binobook.query.MallUserQuery;
import com.binobook.query.OrderItemsQuery;
import com.binobook.query.OrdersQuery;
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
public class MallUserService extends BaseService<MallUser, Integer> {

    @Resource
    private MallUserMapper mallUserMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    public MallUserModel userLogin(String name, String userPwd) {
        String userPassword = userPwd;

        checkLoginParams(name, userPassword);
        System.out.println("MallUserservice.userLogin says: name is"+name+" userPassword is"+userPassword);

        MallUser mallUser = mallUserMapper.queryUserByName(name);
        System.out.println("MallUserService.userLogin says: mallUser is: "+ mallUser);

        AssertUtil.isTrue(mallUser == null, " No Such User！");

        checkuserPassword(userPassword, mallUser.getUserPassword());

        MallUserModel mallUserModel = buildUserInfo(mallUser);
        System.out.println("MallUserModel says: mallUserModel="+mallUserModel.toString());
        return mallUserModel;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassWord(Integer userId, String oldPwd, String newPwd, String repeatPwd) {

        MallUser mallUser = mallUserMapper.selectByPrimaryKey(userId);

        AssertUtil.isTrue(null == mallUser, "No Such Record！");

        checkPasswordParams(mallUser, oldPwd, newPwd, repeatPwd);

        mallUser.setUserPassword(Md5Util.encode(newPwd));

        AssertUtil.isTrue(mallUserMapper.updateByPrimaryKeySelective(mallUser) < 1, "Fail to Update！");

    }


    private void checkPasswordParams(MallUser mallUser, String oldPwd, String newPwd, String repeatPwd) {

        AssertUtil.isTrue(StringUtils.isBlank(oldPwd), "Old Password Required！");

        AssertUtil.isTrue(!mallUser.getUserPassword().equals(Md5Util.encode(oldPwd)), "Wrong Old Pssword！");

        AssertUtil.isTrue(StringUtils.isBlank(newPwd), "New Password Required！");

        AssertUtil.isTrue(oldPwd.equals(newPwd),"Idenntical Old and New Passowrd！！");

        // 判断确认密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd),"Confirm Password reuqired！");
        // 判断确认密码是否与新密码一致
        AssertUtil.isTrue(!newPwd.equals(repeatPwd), "Inconsistent Confirm and New Password！");

    }

    /**
     * User Info To Be Returned
     */
    private MallUserModel buildUserInfo(MallUser mallUser) {
        MallUserModel mallUserModel = new MallUserModel();
        // mallUserModel.setUserId(mallUser.getId());
        // 设置加密的用户ID
        mallUserModel.setUserIdStr(UserIDBase64.encoderUserID(mallUser.getId()));
        mallUserModel.setName(mallUser.getName());
        mallUserModel.setTrueName(mallUser.getTrueName());
        System.out.println("service.builduserInfo says:"+ mallUserModel.toString());
        return mallUserModel;
    }

    private void checkuserPassword(String userPassword, String pwd) {

        userPassword = Md5Util.encode(userPassword);

        System.out.println("MallUserService says: userPassword="+userPassword);
        AssertUtil.isTrue(!userPassword.equals(pwd), "Wrong Password！");
    }

    private void checkLoginParams(String name, String userPassword) {
        System.out.println("UserService.checkLoginParams says: name="+name);

        AssertUtil.isTrue(StringUtils.isBlank(name), "User Name Required！");

        AssertUtil.isTrue(StringUtils.isBlank(userPassword), "Password Required！");
    }


    /**
     * Add User
     */
    //return throwable because of adding test it originally returns viod
    @Transactional(propagation = Propagation.REQUIRED)
    public Throwable addUser(MallUser mallUser) {
        System.out.println("mallUserService.addUser says01: "+ mallUser.toString());

        checkUserParams(mallUser.getName(), mallUser.getEmail(), mallUser.getPhone(), null);


        mallUser.setCreateDate(new Date());
        mallUser.setUpdateDate(new Date());

        mallUser.setUserPassword(Md5Util.encode("123456"));
        System.out.println("mallUserService.addUser says02: "+ mallUser.toString());

        AssertUtil.isTrue(mallUserMapper.insertSelective(mallUser) < 1, "Fail to Add User！！");
        System.out.println("mallUserService.addUser says03: "+ mallUser.toString());

        return null;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(MallUser mallUser) {

        System.out.println("Service.addUser says: mallUser="+ mallUser.toString());
        AssertUtil.isTrue(null == mallUser.getId(), "Empty Input！");

        MallUser temp = mallUserMapper.selectByPrimaryKey(mallUser.getId());

        AssertUtil.isTrue(null == temp, "Record do not Exist！");

        checkUserParams(mallUser.getName(), mallUser.getEmail(), mallUser.getPhone(), mallUser.getId());

        mallUser.setUpdateDate(new Date());

        AssertUtil.isTrue(mallUserMapper.updateByPrimaryKeySelective(mallUser) != 1, "Update Failure！");

    }


    /**
     *  Params Check
     */
    private void checkUserParams(String name, String email, String phone, Integer userId) {

        AssertUtil.isTrue(StringUtils.isBlank(name), "mallUser Name Required！");

        MallUser temp = mallUserMapper.queryUserByName(name);

        AssertUtil.isTrue(null != temp && !(temp.getId().equals(userId)), "mallUser Name ALready Exist！");

        AssertUtil.isTrue(StringUtils.isBlank(email), "Email Required！");

        AssertUtil.isTrue(StringUtils.isBlank(phone), "Phone Number Required！");

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByIds(Integer[] ids) {

        AssertUtil.isTrue(ids == null || ids.length == 0, "No Such Records！");

        AssertUtil.isTrue(mallUserMapper.deleteBatch(ids) != ids.length, "Deletion Failed！");

        for (Integer userId : ids) {
            // Query User By Id
            Integer count  = userRoleMapper.countUserRoleByUserId(userId);
            if (count > 0) {
                AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(userId) != count, "Deletion Failed！");
            }
        }
    }

    public Map<String,Object> queryMallUserOrderlistsByID(Integer id) {
        Map<String,Object> result = new HashMap<String,Object>();
        System.out.println("MullUserService.queryMallUserOrderlistsByID says: results = "+id);
        List<Orders> orderLists = mallUserMapper.queryMallUserOrderlistsByID(id);
        System.out.println("MullUserService.queryMallUserOrderlistsByID says: results = "+orderLists);
        OrdersQuery ordersQuery = new OrdersQuery();
        PageHelper.startPage(ordersQuery.getPage(), ordersQuery.getLimit());
        PageInfo<Orders> pageInfo =new PageInfo<Orders>(orderLists);
        result.put("count",10);
        result.put("data",pageInfo.getList());
        result.put("code",0);
        result.put("msg","");
        return result;
    }
    public Map<String,Object> queryOrderDetailByID(Integer orderId) {
        Map<String,Object> result = new HashMap<String,Object>();
        System.out.println("MullUserService.queryOrderDetailByID says: results = "+orderId+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        List<Orders> orderLists = mallUserMapper.queryOrderDetailByID(orderId);
        System.out.println("MullUserService.queryOrderDetailByID says: results = "+orderLists);
        OrderItemsQuery orderItemsQuery = new OrderItemsQuery();
        PageHelper.startPage(orderItemsQuery.getPage(), orderItemsQuery.getLimit());
        PageInfo<Orders> pageInfo =new PageInfo<Orders>(orderLists);
        result.put("count",10);
        result.put("data",pageInfo.getList());
        result.put("code",0);
        result.put("msg","");
        System.out.println("MullUserService.queryOrderDetailByID says: results = "+result.toString());
        return result;
    }
    /**
     * mallUser Self-Info Update and Cancel Account Page
     */
    public Map<String, Object> basicInfo(MallUserQuery mallUserQuery, Integer id){
        Map<String,Object> result = new HashMap<String,Object>();
        System.out.println("MallUserService.basicInfo says loginUserIdStr="+id);
//        mallUserQuery.setId(id);
        System.out.println("MallUserService.basicInfo says mallUserQuery="+ mallUserQuery.toString());
        List<MallUser> temp = Collections.singletonList(mallUserMapper.selectByPrimaryKey(id));
        System.out.println("MallUserService.basicInfo says temp="+temp.toString());
        PageHelper.startPage(mallUserQuery.getPage(), mallUserQuery.getLimit());
        PageInfo<MallUser> pageInfo =new PageInfo<MallUser>(temp);
        result.put("count",10);
        result.put("data",pageInfo.getList());
        result.put("code",0);
        result.put("msg","");
        return result;

    }
}
