package cx.study.auction.action;

import com.google.gson.Gson;
import cx.study.auction.pojo.Customer;
import cx.study.auction.pojo.ResponseMessage;
import cx.study.auction.query.CustomerQuery;
import cx.study.auction.service.CustomerService;
import cx.study.auction.util.RequestUtil;
import cx.study.auction.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.security.action.GetIntegerAction;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AMOBBS on 2017/2/4.
 */
@Controller
@RequestMapping("/admin")
public class CustomerAction {
    private static final int OK = 1;
    private static final int ERROR = 0;
    @Resource
    private CustomerService customerService;
    @RequestMapping("/addCustomer")
    public void addCustomer(HttpServletResponse response, HttpServletRequest request) throws Exception{
        String name = RequestUtil.getString(request,"name");
        Integer gender = RequestUtil.getInteger(request,"gender");
        Date birth = RequestUtil.getDate(request,"birth");
        String phone = RequestUtil.getString(request,"phone");
        String email = RequestUtil.getString(request,"email");
        String IDCard = RequestUtil.getString(request,"IDCard");
        String address = RequestUtil.getString(request,"address");
        Customer customer = new Customer(name,gender,birth,IDCard,phone,email,address,new Date());
        int code = customerService.addCustomer(customer);
        ResponseMessage<String> responseMessage = new ResponseMessage<String>();
        if (code == OK){
            responseMessage.setResultCode(OK);
            responseMessage.setData("提交成功!");

        } else {
            responseMessage.setResultCode(ERROR);
            responseMessage.setData("提交失败!");
        }
        String result = new Gson().toJson(responseMessage,ResponseMessage.class);
        ResponseUtil.writeJson(response,result);
    }

    @RequestMapping("/findCustomer")
    public void findCustomer(HttpServletResponse response,HttpServletRequest request) throws Exception {
        Integer page = RequestUtil.getInteger(request, "page");
        Integer rows = RequestUtil.getInteger(request, "rows");
        CustomerQuery query = new CustomerQuery();
        query.setPageNo(page);
        query.setRows(rows);
        List<Customer> customerList = customerService.findCustomer(query);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("rows",customerList);
        map.put("total",customerList.size());
        System.out.println(customerList.size());
        String result = new Gson().toJson(map);
        ResponseUtil.writeJson(response,result);
    }

    @RequestMapping("/deleteCustomer")
    public void deleteCustomer(HttpServletResponse response,HttpServletRequest request) throws Exception {
        Integer id = RequestUtil.getInteger(request, "id");
        int code = customerService.deleteCustomerById(id);
        ResponseMessage<String> responseMessage = new ResponseMessage<String>();
        if (code == OK){
            responseMessage.setResultCode(OK);
            responseMessage.setData("删除成功!");
        } else {
            responseMessage.setResultCode(ERROR);
            responseMessage.setData("删除失败!");
        }
        String result = new Gson().toJson(responseMessage,ResponseMessage.class);
        ResponseUtil.writeJson(response,result);
    }
}
