package com.binobook.controller.admin;

import com.binobook.base.BaseController;
import com.binobook.base.ResultInfo;
import com.binobook.exceptions.NoLoginException;
import com.binobook.model.AdminUserModel;
import com.binobook.po.AdminUser;
import com.binobook.query.AdminUserQuery;
import com.binobook.service.AdminUserService;
import com.binobook.utils.UserIDBase64;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @RestController means the returned format is JSONformat, @RestController equals to Controller + ResponseBody
 * Class With @RestController，can return JSON without @ResponseBody annotation
 */
@Api(tags = "AdminUser Module")
@Controller//@RestController
@RequestMapping("adminUser")
public class AdminUserController extends BaseController {

    @Resource
    private AdminUserService adminUserService;
    @Resource
    private HttpSession session;

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;

    private String loginAdminUserIdStr;

    @ApiOperation(value = "AdminUser Login", notes="AdminUser Name and Password cannot be empty")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="AdminUser Name",required=true,dataType = "String"),
            @ApiImplicitParam(name="userPwd",value="AdminUser Password",required=true,dataType = "String")
    })
    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String name, String userPwd){
        ResultInfo resultInfo = new ResultInfo();
        System.out.println("AdminController.userLogin says: name="+name+"+userPwd="+userPwd+"\n");
        AdminUserModel adminUserModel = adminUserService.userLogin(name,userPwd);
        resultInfo.setResult(adminUserModel);
//        if (adminUserModel != null) {
        loginAdminUserIdStr =  adminUserModel.getUserIdStr();
        session.setAttribute("loginAdminUser", adminUserModel.getName());
        session.setAttribute("loginAdminUserIdStr", adminUserModel.getUserIdStr());
        String temp1 = (String) session.getAttribute("loginAdminUser");
        String temp2 = (String) session.getAttribute("loginAdminUserIdStr");
        System.out.println("AdminUserController says: Session attributes setted: "+temp1+", "+ temp2);
        //session过期时间设置为7200秒 即两小时
        session.setMaxInactiveInterval(60 * 60 * 2);
        return resultInfo;
    }

    @ApiOperation(value = "url:Log Out", responseReference = "main")
    @GetMapping("logout")
    public String logout() {
        System.out.println("Log Out Performed!");
        session.removeAttribute("loginAdminUserIdStr");
        session.removeAttribute("loginAdminUser");
        session.removeAttribute("errorMsg");
        return "main";
    }

    @PostMapping("updateUserPwd")
    @ResponseBody
    @ApiOperation(value = "updateUserPwd", notes="AdminUser Name and Password cannot be empty")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userPwd",value="Old Password",required=true,dataType = "String"),
            @ApiImplicitParam(name="newPwd",value="New Password",required=true,dataType = "String"),
            @ApiImplicitParam(name="repeatPwd",value="Repeat Password",required=true,dataType = "String"),
    })
    public ResultInfo updateUserPwd(String userPwd, String newPwd, String repeatPwd){

        ResultInfo resultInfo = new ResultInfo();
        String idStr = (String) request.getSession().getAttribute("loginAdminUserIdStr");//Integer id = LoginUserUtil.releaseUserIdFromCookie(request);
        System.out.println("userController.updatePwd says: idStr="+idStr);
        Integer id = UserIDBase64.decoderUserID(idStr);
        System.out.println("userController.updatePwd says: Integer id="+id);
        adminUserService.updatePassWord(id, userPwd, newPwd, repeatPwd);
        session.removeAttribute("loginAdminUserIdStr");
        session.removeAttribute("loginAdminUser");
        session.removeAttribute("errorMsg");
        return resultInfo;
    }

    @ApiOperation(value = "url:Change AdminUser Password", responseReference = "adminUser/password")
    @RequestMapping("toPasswordPage")
    public String toPasswordPage() {
        return "adminUser/password";
    }

    @ApiOperation(value = "url:to AdminUser BasicInfo and Setting Page", responseReference = "adminUser/basicInfo")
    @RequestMapping("toSettingPage")
    public String toSettingPage() {
        return "adminUser/basicInfo";
    }


    /**
     * multipage List Query by multi conditions
     */
    @RequestMapping("list")
    @ApiOperation(value = " Query AdminUser List with Multiple Conditions")
    @ResponseBody
    public Map<String,Object> selectByParams(AdminUserQuery adminUserQuery) {
        System.out.println("UserController.selectByParams says: "+ adminUserQuery.toString());
        Map<String, Object> temp = adminUserService.queryByParamsForTable(adminUserQuery);
        System.out.println("UserController.selectByParams says: temp table to be run in frontend="+temp.toString());
        return temp;
    }
    /**
     * Query for admin self-basic information only
     */
    @RequestMapping("basicInfo")
    @ApiOperation(value = " Query Login AdminUser basic info with Multiple Conditions")
    @ResponseBody
    public Map<String,Object> basicInfo(AdminUserQuery adminUserQuery) {
        System.out.println("AdminUserController.basicInfo says: "+ adminUserQuery.toString());
        String loginUserName = String.valueOf(session.getAttribute("loginAdminUser"));
        String loginUserIdStr = String.valueOf(session.getAttribute("loginAdminUserIdStr"));
        System.out.println("AdminUserController.basicInfo says: loginUserName: "+ loginUserName);
        System.out.println("AdminUserController.basicInfo says: loginUserIdStr: "+ loginUserIdStr);
        Integer id = UserIDBase64.decoderUserID(loginUserIdStr);
        System.out.println("-----------------------------AdminUserController.basicInfo says: id: "+id);
        Map<String, Object> temp = adminUserService.basicInfo(adminUserQuery, id);
        System.out.println("AdminUserController.basicInfo says: temp table to be run in frontend="+temp.toString());
        return temp;
    }

    /**
     * Redirect to the adminUser/adminUser page
     */
    @ApiOperation(value = "url:AdminUserList Page", responseReference = "adminUser/adminUser")
    @RequestMapping("index")
    public String index() {
        return "adminUser/adminUser";
    }

    /**
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addUser(AdminUser adminUser) {
        System.out.println("AdminController.addUser says: adminUser="+ adminUser.toString());
        adminUserService.addUser(adminUser);
        System.out.println("AdminController.addUser says afetra: "+ adminUser.toString());
        return success("Added Successfully!");
    }


    /**
     */
    @PostMapping("update")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="AdminUser Name",required=true,dataType = "String"),
            @ApiImplicitParam(name="userPwd",value="AdminUser Password",required=true,dataType = "String")
    })
    public ResultInfo updateUser(AdminUser adminUser) {
        System.out.println("UserController.updateUser says: adminUser="+ adminUser.toString());
        adminUserService.updateUser(adminUser);
        return success("Updated Admin User Successfully！");
    }


    /**
     * toAddOrUpdateUserPage
     */
    @ApiOperation(value = "url:toAddOrUpdateUserPage", responseReference = "adminUser/add_update_admins")
    @RequestMapping("toAddOrUpdateUserPage")
    public String toAddOrUpdateUserPage(Integer id) {
        System.out.println("Controller.toAddOrUpdateUserPage says:Here is toAddOrUpdateUserPage");
        //Verify whether the ID is empty or not. If it is not empty,
        // it means update operation and query the user object
        if (id != null) {
            //Query user object by ID
            AdminUser adminUser = adminUserService.selectByPrimaryKey(id);
            // Set the data to the request domain
            request.setAttribute("userInfo", adminUser);
        }
        return "adminUser/add_update_admins";
    }
    /**
     * Open the admin basic info page
     */
    @ApiOperation(value = "url:the admin basic info page", responseReference = "adminUser/add_update_admins")
    @RequestMapping("toEditBasicInfoPage")
    public String toEditBasicInfoPage(Integer id) {
        System.out.println("Controller.toEditBasicInfoPage says:Here is toEditBasicInfoPage");
        AdminUser adminUser = adminUserService.selectByPrimaryKey(id);
        request.setAttribute("userInfo", adminUser);
        return "adminUser/update_basicInfo";
    }
    /**
     * Login Admin self account cancellation
     */
    @ApiOperation(value = "url:Login Admin self account cancellation", responseReference = "adminUser/add_update_admins")
    @PostMapping("deleteSelf")
    public String deleteSelf(Integer[] ids) throws NoLoginException, IOException {
        adminUserService.deleteByIds(ids);
        session.invalidate();
        response.flushBuffer();
//        response.sendRedirect(request.getContextPath() + "/login");
//        return success("Account Cancellation Success！");
        return "redirect:/adminLogin";
    }
    /**
     * Login Amdin Delete other Admin Account
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids) {

        adminUserService.deleteByIds(ids);
        return success("Deletion Success！");
    }

    @ApiIgnore
    @RequestMapping("swagger")
    public String swagger(){
        return "sgu";
    }

}
