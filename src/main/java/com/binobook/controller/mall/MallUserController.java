package com.binobook.controller.mall;

import com.binobook.base.BaseController;
import com.binobook.base.ResultInfo;
import com.binobook.exceptions.NoLoginException;
import com.binobook.model.MallUserModel;
import com.binobook.po.MallUser;
import com.binobook.query.MallUserQuery;
import com.binobook.service.MallUserService;
import com.binobook.utils.UserIDBase64;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @RestController means the returned format is JSONformat, @RestController equals to Controller + ResponseBody
 * Class With @RestController，can return JSON without @ResponseBody annotation
 */
@Api(tags = "MallUser Module")
@Controller//@RestController
@RequestMapping("mallUser")
public class MallUserController extends BaseController {

    @Resource
    private MallUserService mallUserService;
    @Resource
    private HttpSession session;

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;

    private String loginMallUserIdStr;

    @ApiOperation(value = "MallUser Login", notes="MallUser Name and Password cannot be empty")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="MallUser Name",required=true,dataType = "String"),
            @ApiImplicitParam(name="userPwd",value="MallUser Password",required=true,dataType = "String")
    })
    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String name, String userPwd) {//public ResultInfo userLogin(String name, String userPwd) throws IOException {
        ResultInfo resultInfo = new ResultInfo();
        System.out.println("MallUserController.userLogin says: name="+name+"+userPwd="+userPwd+"\n");
        MallUserModel mallUserModel = mallUserService.userLogin(name,userPwd);
        resultInfo.setResult(mallUserModel);
//        if (mallUserModel != null) {
        loginMallUserIdStr =  mallUserModel.getUserIdStr();
        session.setAttribute("loginMallUser", mallUserModel.getName());
        session.setAttribute("loginMallUserIdStr", mallUserModel.getUserIdStr());
        String temp1 = (String) session.getAttribute("loginMallUser");
        String temp2 = (String) session.getAttribute("loginMallUserIdStr");
        System.out.println("MallUserController says: Session attributes set: "+temp1+", "+ temp2);
        //set session expire in 3600s
        session.setMaxInactiveInterval(60 * 60 * 1);
        return resultInfo;
    }

    @ApiOperation(value = "url:Log Out", responseReference = "mallMain")
    @GetMapping("logout")
    public String logout() {
        System.out.println("Log Out Performed!");
        session.removeAttribute("loginMallUser");
        session.removeAttribute("loginMallUser");
        session.removeAttribute("errorMsg");
        return "mallMain";
    }

    /**
     *
     * @param userPwd
     * @param newPwd
     * @param repeatPwd
     * @return
     */
    @PostMapping("updateUserPwd")
    @ResponseBody
    @ApiOperation(value = "updateUserPwd", notes="MallUser Name and Password cannot be empty")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userPwd",value="Old Password",required=true,dataType = "String"),
            @ApiImplicitParam(name="newPwd",value="New Password",required=true,dataType = "String"),
            @ApiImplicitParam(name="repeatPwd",value="Repeat Password",required=true,dataType = "String"),
    })
    public ResultInfo updateUserPwd(String userPwd, String newPwd, String repeatPwd){

        ResultInfo resultInfo = new ResultInfo();
        String idStr = (String) request.getSession().getAttribute("loginMallUserIdStr");//Integer id = LoginUserUtil.releaseUserIdFromCookie(request);
        System.out.println("MallUserController.updatePwd says: idStr="+idStr);
        Integer id = UserIDBase64.decoderUserID(idStr);
        System.out.println("userController.updatePwd says: Integer id="+id);
        mallUserService.updatePassWord(id, userPwd, newPwd, repeatPwd);
        session.removeAttribute("loginMallUserIdStr");
        session.removeAttribute("loginMallUser");
        session.removeAttribute("errorMsg");
        return resultInfo;
    }

    @ApiOperation(value = "url:Update MallUser Password", responseReference = "mallUser/password")
    @ApiIgnore
    @RequestMapping("toPasswordPage")
    public String toPasswordPage() {

        return "mallUser/password";
    }

    @ApiOperation(value = "url:Change MallUser Password", responseReference = "mallUser/basicInfo")
    @ApiIgnore
    @RequestMapping("toSettingPage")
    public String toSettingPage() {

        return "mallUser/basicInfo";
    }


    /**
     * Query User List to render a multipage frontend table
    @RequestMapping("list")
    @ApiOperation(value = " Query MallUser List with Multiple Conditions")
    @ResponseBody*/
    public Map<String,Object> selectByParams(MallUserQuery mallUserQuery) {
        System.out.println("UserController.selectByParams says: "+ mallUserQuery.toString());
        Map<String, Object> temp = mallUserService.queryByParamsForTable(mallUserQuery);
        System.out.println("UserController.selectByParams says: temp table to be run in frontend="+temp.toString());
        return temp;
    }

    @RequestMapping("basicInfo")
    @ApiOperation(value = " Query MallUser List with Multiple Conditions")
    @ResponseBody
    public Map<String,Object> basicInfo(MallUserQuery mallUserQuery) {
        System.out.println("UserController.basicInfo says: "+ mallUserQuery.toString());
        String loginUserName = String.valueOf(session.getAttribute("loginMallUser"));
        String loginUserIdStr = String.valueOf(session.getAttribute("loginMallUserIdStr"));
        System.out.println("MallUserController.basicInfo says: loginUserName: "+ loginUserName);
        System.out.println("MallUserController.basicInfo says: loginUserIdStr: "+ loginUserIdStr);
        Integer id = UserIDBase64.decoderUserID(loginUserIdStr);
        System.out.println("-----------------------------MallUserController.basicInfo says: id: "+id);
        Map<String, Object> temp = mallUserService.basicInfo(mallUserQuery, id);
        System.out.println("UserController.basicInfo says: temp table to be run in frontend="+temp.toString());
        return temp;
    }

    @ApiOperation(value = "url:Update MallUser List Page", responseReference = "mallUser/mallUser")
    @RequestMapping("index")
    public String index() {
        return "mallUser/mallUser";
    }


    @PostMapping("add")
/*    @ApiOperation(value = "Add MallUser")
    @ApiImplicitParam(name = "mallUser",value = "MallUser Entity Class",dataType = "MallUser")*/
    @ResponseBody
    public ResultInfo addUser(MallUser mallUser) {
        System.out.println("Controller.addUser says: mallUser="+ mallUser.toString());
        mallUserService.addUser(mallUser);
        System.out.println("Controller.addUser says afetra: "+ mallUser.toString());
        return success("Add Success!");
    }

    @PostMapping("register")
/*    @ApiOperation(value = "Register MallUser")
    @ApiImplicitParam(name = "mallUser",value = "MallUser Entity Class",dataType = "MallUser")*/
    @ResponseBody
    public ResultInfo registerUser(MallUser mallUser) {
        ResultInfo resultInfo = new ResultInfo();
        System.out.println("MallUserController.registerUser says: mallUser="+ mallUser.toString());
        mallUserService.addUser(mallUser);
        System.out.println("MallUserController.registerUser says afetra: "+ mallUser.toString());
        resultInfo.setMsg("Register Succeeds！");
        resultInfo.setCode(200);
        return resultInfo;
    }

    @PostMapping("update")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="MallUser Name",required=true,dataType = "String"),
            @ApiImplicitParam(name="userPwd",value="MallUser Password",required=true,dataType = "String")
    })
    public ResultInfo updateUser(MallUser mallUser) {
        System.out.println("UserController.updateUser says: mallUser="+ mallUser.toString());
        mallUserService.updateUser(mallUser);
        return success("Update Success！");
    }


    /**
     * 打开添加或修改用户的页面
     *
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param
     * @return java.lang.String
     */
    @ApiIgnore
    @RequestMapping("toAddOrUpdateUserPage")
    public String toAddOrUpdateUserPage(Integer id) {
        System.out.println("Controller.toAddOrUpdateUserPage says:Here is toAddOrUpdateUserPage");
        // 判断id是否为空，不为空表示更新操作，查询用户对象
        if (id != null) {
            // 通过id查询用户对象
            MallUser mallUser = mallUserService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("userInfo", mallUser);
        }
        return "mallUser/add_update_mallUsers";
    }

    /**
     * Open dialog of customer/add_update_customer
     */
    @ApiOperation(value = "url:pen dialog of customer/update_basicInfo", responseReference = "mallUser/mallUser")
    @RequestMapping("toEditBasicInfoPage")
    public String toEditBasicInfoPage(Integer id) {
        System.out.println("-----------------------------Controller.toEditBasicInfoPage says:Here is toEditBasicInfoPage");
        //verify who is the mallUser
        MallUser mallUser = mallUserService.selectByPrimaryKey(id);
        request.setAttribute("userInfo", mallUser);
        return "mallUser/update_basicInfo";
    }


    /**
     * Cancel Account
     */
    @ApiOperation(value = "url:open dialog after account cancellation", responseReference = "mallUser/mallLogin")
    @PostMapping("deleteSelf")
    public String deleteSelf(Integer[] ids) throws NoLoginException, IOException {
        System.out.println("line 310: -----------------------------------");
        mallUserService.deleteByIds(ids);
        System.out.println("line 312: ");
//        response.setStatus(200);
        System.out.println("line314: ");
        session.invalidate();
        response.flushBuffer();
        System.out.println("line318: ");
//        response.sendRedirect(request.getContextPath() + "/login");
        System.out.println("line320: ");
//        return success("Cancel Self Successfully！");
        return "redirect:/mallLogin";
    }
    /**
     * User Deletion
     * */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids) {

        mallUserService.deleteByIds(ids);
        return success("Deletion Success！");
    }

    @ApiIgnore
    @RequestMapping("swagger")
    public String swagger(){
        return "sgu";
    }

    @ApiIgnore
    @RequestMapping("toMyOrderPage")
    public String toMyOrderPage(){
        System.out.println("MallUserController.toMyOrderPage says: go to mallUser/mallUser_order of front stage.");
        return "mallUser/mallUser_order";
    }



    /**
     * Query MallUser's order list
     */
    @RequestMapping("mallUserOrderlist")
    @ResponseBody
    public Map<String,Object> queryMallUserOrderlistsByID(Integer id){
        String idstr = String.valueOf(session.getAttribute("loginMallUserIdStr"));
        id = UserIDBase64.decoderUserID(idstr);
        System.out.println("MallUserController.queryMallUserOrderlistsByID says: idstr="+idstr+" "+id);
        Map<String,Object> temp = mallUserService.queryMallUserOrderlistsByID(id);
        System.out.println("MallUserController.queryMallUserOrderlistsByID says: temp="+temp);
        return temp;
    }

    /**
     * Query Order Items By Id, do not function yet , still under construction
     */
    @ApiIgnore
    @RequestMapping("toOrderItemsPage")
    public Integer toOrderItemsPage(Integer id) throws IOException {
        System.out.println("id:"+id+"MallUserController.toMyOrderPage says: go to mallUser/mallUser_order of front stage.");
        Map<String, Object> irderdetails = queryOrderDetailByID(id);
        response.sendRedirect( "mallUser/mallUser_order_detail");
        return id;
    }
    /**
     * Query Order Items By Id, do not function yet , still under construction
     */
    @RequestMapping("queryOrderDetailByID")
    @ResponseBody
    private Map<String,Object> queryOrderDetailByID(Integer id){
        System.out.println("MallUserController.queryOrderDetailByID says: id="+", "+id);
        Map<String,Object> temp = mallUserService.queryOrderDetailByID(id);
        System.out.println("MallUserController.queryOrderDetailByID says: temp="+temp);
        return temp;
    }
}
