package cx.study.auction.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by AMOBBS on 2017/2/3.
 */
@Controller
@RequestMapping("/admin")
public class AdminPageAction {

    @RequestMapping("/loginPage")
    public String toLogin(){
        return "admin/login";
    }

    @RequestMapping("/addCustomerPage")
    public String toAddNewCustomer(){
        return "admin/addNewCustomer";
    }

    @RequestMapping("/customerList")
    public String toFindCustomer(){
        return "admin/customerList";
    }
}
