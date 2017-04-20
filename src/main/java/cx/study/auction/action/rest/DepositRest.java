package cx.study.auction.action.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cx.study.auction.pojo.Deposit;
import cx.study.auction.pojo.Result;
import cx.study.auction.service.CommodityService;
import cx.study.auction.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Created by cheng.xiao on 2017/4/20.
 */
@Controller
@RequestMapping("/DepositRest")
public class DepositRest {
    @Resource
    CommodityService commodityService;
    @RequestMapping("/pay")
    public void pay(HttpServletResponse response, @RequestBody()JsonObject json) throws Exception{
        //支付保证金
        int userId = json.get("userId").getAsInt();
        int commodityId = json.get("commodityId").getAsInt();
        int i = commodityService.payDeposit(userId, commodityId);
        Result result;
        if (i == 1){
            result = new Result<>(0,"请求成功",i);
        } else if (i == -1){
            result = new Result<>(-1,"余额不足",i);
        } else {
            result = new Result<>(1,"请求失败",i);
        }
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }

    @RequestMapping("/isPayDeposit")
    public void isPayDeposit(HttpServletResponse response, @RequestBody()JsonObject json) throws Exception{
        int userId = json.get("userId").getAsInt();
        int commodityId = json.get("commodityId").getAsInt();
        Deposit deposit = commodityService.findUserIsPayDeposit(userId, commodityId);
        Result result;
        if (deposit != null){
            result = new Result<>(0,"请求成功",0);
        } else {
            result = new Result<>(1,"请求失败",1);
        }
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }
}
