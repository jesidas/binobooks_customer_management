package com.binobook.controller.admin;

import com.binobook.base.BaseController;
import com.binobook.service.AdminUserRoleService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


@Api(tags = "AdminUserRole Module")
@Controller
@RequestMapping("userRole")
public class UserRoleController extends BaseController {

    @Resource
    private AdminUserRoleService adminUserRoleService;
}
