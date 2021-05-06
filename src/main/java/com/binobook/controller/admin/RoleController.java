package com.binobook.controller.admin;

import com.binobook.base.BaseController;
import com.binobook.base.ResultInfo;
import com.binobook.query.RoleQuery;
/*
import com.binobook.service.AdminUserRoleService;
*/
import com.binobook.service.RoleService;
import com.binobook.po.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Api(tags = "Role Module")
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;

    /**
     * queryAllRoles
     */
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return roleService.queryAllRoles(userId);
    }


    /**
     * QueryRoleList
     */
    @GetMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(RoleQuery roleQuery) {
        return roleService.queryByParamsForTable(roleQuery);
    }


    /**
     * url to role management
     */
    @ApiOperation(value = "url:url to Role Management Page", responseReference = "role/role")
    @RequestMapping("index")
    public String index() {
        return "role/role";
    }



    /**
     * Add Role
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addRole(Role role) {
        roleService.addRole(role);
        return success("Role Added Successfully！");
    }


    /***
     * url to toAddOrUpdateRolePage
     */
    @ApiOperation(value = "url:url to Role Update Page", responseReference = "role/add_update")
    @RequestMapping("toAddOrUpdateRolePage")
    public String toAddOrUpdateRolePage(Integer roleId, HttpServletRequest request) {
        // Query role, if roleId not null
        if (roleId != null) {
            Role role = roleService.selectByPrimaryKey(roleId);
            request.setAttribute("role", role);
        }
        return "role/add_update";
    }


    /**
     * Update Role
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateRole(Role role) {
        roleService.updateRole(role);
        return success("Role Successfully Updated！");
    }



    /**
     * Delete Role
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteRole(Integer roleId) {
        roleService.deleteRole(roleId);
        return success("Role Delete Successfully！");
    }


    /**
     * Role Grant
     */
    @PostMapping("addGrant")
    @ResponseBody
    public ResultInfo addGrant(Integer roleId, Integer[] mIds) {

        roleService.addGrant(roleId, mIds);

        return success("Role Granted Successfully！");
    }


}
