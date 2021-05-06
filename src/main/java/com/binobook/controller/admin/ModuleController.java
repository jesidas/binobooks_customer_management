package com.binobook.controller.admin;

import com.binobook.base.BaseController;
import com.binobook.base.ResultInfo;
import com.binobook.model.TreeModel;
import com.binobook.service.ModuleService;
import com.binobook.po.Module;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Api(tags = "Module")
@RequestMapping("module")
@Controller
public class ModuleController extends BaseController {

    @Resource
    private ModuleService moduleService;



    @RequestMapping("queryAllModules")
    @ResponseBody
    public List<TreeModel> queryAllModules(Integer roleId) {
        return moduleService.queryAllModules(roleId);
    }


    @ApiOperation(value = "url:Set the role ID to be authorized to the request domain", responseReference = "role/grant")
    @RequestMapping("toAddGrantPage")
    public String toAddGrantPage(Integer roleId, HttpServletRequest request) {
        // Set the role ID to be authorized to the request domain,
        request.setAttribute("roleId", roleId);
        return "role/grant";
    }


    /***
     * Role List
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object>  queryModuleList() {
        return moduleService.queryModuleList();
    }


    /***
     * url to Module Management Page
     */
    @ApiOperation(value = "url:url to Module Management Page", responseReference = "module/module")
    @RequestMapping("index")
    public String index() {
        return "module/module";
    }



    @PostMapping("add")
    @ResponseBody
    public ResultInfo addModule(Module module) {

        moduleService.addModule(module);
        return success("Module Successfully Added！");
    }



    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateModule(Module module) {

        moduleService.updateModule(module);
        return success("Module Successfully Updated！");
    }


    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteModule(Integer id) {

        moduleService.deleteModule(id);
        return success("Module Successfully Deleted！");
    }


    /**
     * url to add module page
     * */
    @ApiOperation(value = "url:url to Module Management Page", responseReference = "module/add")
    @RequestMapping("toAddModulePage")
    public String toAddModulePage(Integer grade, Integer parentId, HttpServletRequest request) {
        // 将数据设置到请求域中
        request.setAttribute("grade", grade);
        request.setAttribute("parentId", parentId);

        return "module/add";
    }


    /**
     * Update Module
     */
    @ApiOperation(value = "url:url to Module Management Page", responseReference = "module/update")
    @RequestMapping("toUpdateModulePage")
    public String toUpdateModulePage(Integer id, Model model) {
        // 将要修改的资源对象设置到请求域中
        model.addAttribute("module", moduleService.selectByPrimaryKey(id));
        return "module/update";
    }
}
