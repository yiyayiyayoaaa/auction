package cx.study.auction.action;

import com.google.gson.Gson;
import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.Order;
import cx.study.auction.pojo.ResponseMessage;
import cx.study.auction.query.OrderQuery;
import cx.study.auction.query.UserQuery;
import cx.study.auction.service.CommodityService;
import cx.study.auction.service.OrderService;
import cx.study.auction.util.RequestUtil;
import cx.study.auction.util.ResponseUtil;
import cx.study.auction.vo.OrderVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by cheng.xiao on 2017/4/17.
 */

@Controller
@RequestMapping("admin")
public class OrderAction {
    @Resource
    private OrderService orderService;
    @Resource
    private CommodityService commodityService;
    private static final int OK = 1;
    private static final int ERROR = 0;
    @RequestMapping("/findAllOrders")
    public void findAllOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer page = RequestUtil.getInteger(request, "page");
        Integer rows = RequestUtil.getInteger(request, "rows");
        String keyWord = RequestUtil.getString(request, "key");
        OrderQuery query = new OrderQuery();
        if(page != null && rows != null){
            query.setPageNo(page);
            query.setRows(rows);
        }
        if (!StringUtils.isEmpty(keyWord)){
            query.setKeyWord("%"+keyWord+"%");
        }
        List<OrderVo> list = orderService.findAll(query);
        Map<String,Object> map = new HashMap<>();
        map.put("rows",list);
        map.put("total",list.size());
        String result = new Gson().toJson(map);
        ResponseUtil.writeJson(response,result);
    }

    @RequestMapping("/cancelOrder")
    public void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = RequestUtil.getInteger(request, "id");
        Order order = new Order();
        order.setStatus(4);
        order.setId(id);
        order.setUpdateTime(new Date());
        int code = orderService.updateOrder(order);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        if (code == OK){
            commodityService.commodityStatusChange(order.getCommodityId(), Commodity.CommodityStatus.CANCEL);
            responseMessage.setResultCode(OK);
            responseMessage.setData("取消成功!");

        } else {
            responseMessage.setResultCode(ERROR);
            responseMessage.setData("取消失败!");
        }
        String result = new Gson().toJson(responseMessage,ResponseMessage.class);
        ResponseUtil.writeJson(response,result);
    }

    @RequestMapping("/send")
    public void send(HttpServletResponse response,HttpServletRequest request) throws Exception{
        Integer id = RequestUtil.getInteger(request, "id");
        Order order = new Order();
        order.setId(id);
        order.setUpdateTime(new Date());
        order.setStatus(2);
        int code = orderService.updateOrder(order);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        if (code == OK){
            commodityService.commodityStatusChange(order.getCommodityId(), Commodity.CommodityStatus.CANCEL);
            responseMessage.setResultCode(OK);
            responseMessage.setData("发货成功!");

        } else {
            responseMessage.setResultCode(ERROR);
            responseMessage.setData("发货失败!");
        }
        String result = new Gson().toJson(responseMessage,ResponseMessage.class);
        ResponseUtil.writeJson(response,result);
    }
}
