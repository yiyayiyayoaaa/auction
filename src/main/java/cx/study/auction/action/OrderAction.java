package cx.study.auction.action;

import cx.study.auction.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 *
 * Created by cheng.xiao on 2017/4/17.
 */

@Controller
@RequestMapping("admin")
public class OrderAction {
    @Resource
    private OrderService orderService;
    public void findAllOrders(){
        //orderService.
    }
}
