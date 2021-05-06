package com.binobook.controller.admin;

import com.binobook.base.BaseController;
import com.binobook.base.ResultInfo;
import com.binobook.query.CustomerQuery;
import com.binobook.service.CustomerService;
import com.binobook.po.Customer;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Resource
    private CustomerService customerService;

    /**
     * multiCondition to get customer list
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomerByParams(CustomerQuery customerQuery) {
        System.out.println("CustomerController.queryCustomerByParams says: customerQuery="+customerQuery.toString());
        return customerService.queryCustomerByParams(customerQuery);
    }

    @ApiOperation(value = "url:To customer list page", responseReference = "customer/customer")
    @RequestMapping("index")
    public String index() {
        System.out.println("CustomerController.index says: customer="+"customer/customer");
        return "customer/customer";
    }


    @PostMapping("add")
    @ResponseBody
    public ResultInfo addCustomer(Customer customer) {
        System.out.println("CustomerController.addCustomer says: customer="+customer.toString());
        customerService.addUser(customer);
        return success("customer info Successfully added！");
    }


    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateCustomer(Customer customer) {
        System.out.println("CustomerController.updateCustomer says: customer="+customer.toString());
        customerService.updateCustomer(customer);
        System.out.println("CustomerController.updateCustomer says: customer="+customer.toString());
        return success("customer info Successfully updated！");
    }


    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteCustomer(Integer[] ids) {
        System.out.println("CustomerController.deleteCustomer says: I am deleting");
        customerService.deleteByIds(ids);
        return success("customer info Successfully deleted！");
    }

    /**
     * Open dialog of customer/add_update_customer
     */
    @RequestMapping("toAddOrUpdateCustomerPage")
    public String toAddOrUpdateCustomerPage(Integer id, HttpServletRequest request) {
        System.out.println("CustomerController.toAddOrUpdateCustomerPage says: id="+ id);
        // Query if customer id not null
        if (null != id) {
            Customer customer = customerService.selectByPrimaryKey(id);
            // Save customer info in cookie
            request.setAttribute("customer",customer);
            System.out.println("CustomerController.toAddOrUpdateCustomerPage says: customer="+ customer.toString());
        }
        return "customer/add_update_customer";
    }

    @ApiIgnore
    @RequestMapping("order")
    public String toCustomerOrder() {
        System.out.println("return \"customer/customer_order\";");
        return "customer/customer_order";
    }

    @RequestMapping("listAllCustomerOrders")
    @ResponseBody
    public Map<String,Object> listAllCustomerOrders(){
        System.out.println("I am CustomerController.listAllCustomerOrders!");
        Map<String,Object> temp = customerService.listAllCustomerOrders();
        System.out.println("CustomerController.listAllCustomerOrders says: temp="+temp);
        return temp;
    }

    @RequestMapping("toOrderDetailPage")
    @ResponseBody
    public Map<String,Object> queryOrderDetailsByParams(String orderId) {
        System.out.println("CustomerController.queryOrderDetailsByParams says：orderId:"+orderId);
        Integer id = Integer.valueOf(orderId);
        System.out.println("CustomerController.queryOrderDetailsByParams says：id:"+orderId);
        Map<String, Object> temp = customerService.queryOrderDetailsByParams(id);
        System.out.println("CustomerController.queryOrderDetailsByParams says：the map is "+temp.toString());
        return temp;
    }
}
