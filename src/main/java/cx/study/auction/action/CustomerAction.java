package cx.study.auction.action;

import com.google.gson.Gson;
import cx.study.auction.pojo.Customer;
import cx.study.auction.pojo.ResponseMessage;
import cx.study.auction.query.CustomerQuery;
import cx.study.auction.service.CustomerService;
import cx.study.auction.util.RequestUtil;
import cx.study.auction.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
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
        Customer customer = getCustomerByRequest(request);
        customer.setRegistrationTime(new Date());
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
        String key = RequestUtil.getString(request,"key");
        Integer page = RequestUtil.getInteger(request, "page");
        Integer rows = RequestUtil.getInteger(request, "rows");
        CustomerQuery query = new CustomerQuery();
        if(page != null && rows != null){
            query.setPageNo(page);
            query.setRows(rows);
        }
        query.setIdcard(key);
        query.setName(key);
        List<Customer> customerList = customerService.findCustomer(query);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("rows",customerList);
        map.put("total",customerList.size());
        String result = new Gson().toJson(map);
        ResponseUtil.writeJson(response,result);
    }

    @RequestMapping("/updateCustomer")
    public void updateCustomer(HttpServletResponse response,HttpServletRequest request) throws Exception {
        Integer id = RequestUtil.getInteger(request,"id");
        Date registrationTime = RequestUtil.getDate(request, "registrationTime");
        Customer customer = getCustomerByRequest(request);
        customer.setId(id);
        customer.setUpdateTime(new Date());
        customer.setRegistrationTime(registrationTime);
        int resultCode = customerService.updateCustomer(customer);
        ResponseMessage<String> responseMessage = new ResponseMessage<String>();
        if (resultCode == OK){
            responseMessage.setResultCode(OK);
            responseMessage.setData("修改成功!");

        } else {
            responseMessage.setResultCode(ERROR);
            responseMessage.setData("修改失败!");
        }
        String result = new Gson().toJson(responseMessage,ResponseMessage.class);
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

    /**
     * 从 request 中获取customer信息
     * @param request HttpServletRequest
     * @return Customer
     * @throws ParseException ParseException
     */
    private Customer getCustomerByRequest(HttpServletRequest request) throws ParseException {
        String name = RequestUtil.getString(request,"name");
        Integer gender = RequestUtil.getInteger(request,"gender");
        Date birth = RequestUtil.getDate(request,"birth");
        String phone = RequestUtil.getString(request,"phone");
        String email = RequestUtil.getString(request,"email");
        String IDCard = RequestUtil.getString(request,"IDCard");
        String address = RequestUtil.getString(request,"address");
        return new Customer(name,gender,birth,IDCard,phone,email,address,null);
    }
}
