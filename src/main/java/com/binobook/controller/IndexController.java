package com.binobook.controller;

import com.binobook.base.BaseController;
import com.binobook.po.AdminUser;
import com.binobook.po.MallUser;
import com.binobook.service.MallUserService;
import com.binobook.service.PermissionService;
import com.binobook.service.AdminUserService;
import com.binobook.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@ApiIgnore
public class IndexController extends BaseController {

    @Resource
    private AdminUserService adminUserService;
    @Resource
    private MallUserService mallUserService;
    @Resource
    private PermissionService permissionService;

    /**
     * MallUser Login
     */
    @ApiIgnore
    @RequestMapping("mallUserLogin")
    public String indexMallUser(){
        return "mallUserLogin";
    }
    /**
     * AdminUser Login
     */
    @ApiIgnore
    @RequestMapping("adminLogin")
    public String indexAdmin(){
        return "adminLogin";
    }

    @ApiIgnore
    @RequestMapping("register")
    public String register(){
        return "mallUserRegister";
    }


    /**swagger-ui
     * Welcome FrontPage
     */
    @ApiIgnore
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * MainPage of backend management
     */
    @ApiIgnore
    @RequestMapping("mallMain")
    public String mallMain(HttpServletRequest request){

        // Obtain the userId in cookies
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        System.out.println("IndexController.mallMain says: userId="+userId);
        //Search for user object in the session domain
        MallUser mallUser = mallUserService.selectByPrimaryKey(userId);
        System.out.println("IndexController.mallMain says: adminUser="+ mallUser.toString());
        request.getSession().setAttribute("user", mallUser);


        //Query the resource list owned by the current login user through
        // the current login user ID (query the authorization code of the corresponding resource)
        List<String> permissions = permissionService.queryUserHasRoleHasPermissionByUserId(userId);
        System.out.println("IndexController.mallMain says: permissions="+permissions.toString());
        // Set the collection to the session scope
        request.getSession().setAttribute("permissions", permissions);

        return "mallMain";
    }
    /**
     *  Back end management main page
     */
    @ApiIgnore
    @RequestMapping("adminMain")
    public String adminMain(HttpServletRequest request){

        // Obtain the userId in cookies
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        System.out.println("IndexController.AdminMain says: userId="+userId);
        // Search for user object in the session domain
        AdminUser adminUser = adminUserService.selectByPrimaryKey(userId);
        System.out.println("IndexController.AdminMain says: adminUser="+ adminUser.toString());
        request.getSession().setAttribute("user", adminUser);

        //Query the resource list owned by the current login user through
        // the current login user ID (query the authorization code of the corresponding resource)
        List<String> permissions = permissionService.queryUserHasRoleHasPermissionByUserId(userId);
        System.out.println("IndexController.AdminMain says: permissions="+permissions.toString());
        // Set the collection to the session scope
        request.getSession().setAttribute("permissions", permissions);

        return "main";
    }

}
