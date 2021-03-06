package cx.study.auction.action.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cx.study.auction.pojo.Order;
import cx.study.auction.pojo.Result;
import cx.study.auction.query.OrderQuery;
import cx.study.auction.service.OrderService;
import cx.study.auction.util.ResponseUtil;
import cx.study.auction.vo.OrderVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by cheng.xiao on 2017/4/19.
 */
@Controller
@RequestMapping("OrderRest")
public class OrderRest {
    @Resource
    private OrderService orderService;

    @RequestMapping("/orderInfo")
    public void getOrderById(HttpServletResponse response, @RequestBody()JsonObject json) throws Exception {
        int id = json.get("id").getAsInt();
        OrderVo orderVo = orderService.findOrderById(id);
        Result result = new Result<>(0,"",orderVo);
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }


    @RequestMapping("/orderList")
    public void getOrderListByStatus(HttpServletResponse response, @RequestBody()JsonObject json) throws Exception{
        int userId = json.get("userId").getAsInt();
        int status = json.get("status").getAsInt();
        OrderQuery query = new OrderQuery();
        query.setUserId(userId);
        if (status < 0){
            //查全部
            query.setStatus(-1);
        } else {
            //根据status查
            query.setStatus(status);
        }
        List<OrderVo> orderVos = orderService.findOrderByUser(query);
        Result result = new Result<>(0,"",orderVos);
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }
    //支付   支付成功退回保证金
    @RequestMapping("pay")
    public void pay(HttpServletResponse response,@RequestBody()JsonObject json) throws Exception {
        int id = json.get("id").getAsInt();  //订单id
        String address = json.get("address").getAsString();
        //支付逻辑
        int pay = orderService.pay(id,address);
        Result result;
        if (pay > 0){
            //成功
            result = new Result<>(0,"支付成功",pay);
        } else if (pay == 0){
            //支付失败
            result = new Result<>(1,"支付失败",pay);
        } else {
            //余额不足
            result = new Result<>(-1,"余额不足",pay);
        }
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }
    //取消订单  不退保证金
    @RequestMapping("cancel")
    public void cancel(HttpServletResponse response,@RequestBody()JsonObject json) throws Exception {
        int id = json.get("id").getAsInt();
        int cancel = orderService.cancel(id);
        Result result;
        if (cancel > 0){
            result = new Result<>(0,"取消成功",cancel);
        } else {
            result = new Result<>(1,"取消失败",cancel);
        }
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }

    @RequestMapping("/finish")
    public void finish(HttpServletResponse response,@RequestBody()JsonObject json) throws Exception {
        int id = json.get("id").getAsInt();
        Order order = new Order();
        order.setId(id);
        order.setStatus(3);
        order.setUpdateTime(new Date());
        order.setEndTime(new Date());
        int i = orderService.updateOrder(order);
        Result result;
        if (i > 0){
            result = new Result<>(0,"取消成功",i);
        } else {
            result = new Result<>(1,"取消失败",i);
        }
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }
}
