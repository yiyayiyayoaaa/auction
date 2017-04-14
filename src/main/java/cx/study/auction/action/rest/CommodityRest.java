package cx.study.auction.action.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cx.study.auction.pojo.BidRecord;
import cx.study.auction.pojo.Result;
import cx.study.auction.service.CommodityService;
import cx.study.auction.util.ResponseUtil;
import cx.study.auction.vo.CommodityVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by cheng.xiao on 2017/3/25.
 */
@Controller
@RequestMapping("/CommodityRest")
public class CommodityRest {

    @Resource
    CommodityService commodityService;
    @RequestMapping("/findCommodityById")
    public void findCommodityById(HttpServletResponse response, @RequestBody()JsonObject json) throws Exception {
        //Integer id = RequestUtil.getInteger(request, "commodityId");
        int id = json.get("commodityId").getAsInt();
        CommodityVo commodityVo = commodityService.findCommodityById(id);
        Result result = new Result<>(0,"请求成功",commodityVo);
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }

    @RequestMapping("/bid")
    public void bid(HttpServletResponse response, @RequestBody()JsonObject json) throws Exception {
        int commodityId = json.get("commodityId").getAsInt();
        int userId = json.get("userId").getAsInt();
        double price = json.get("price").getAsDouble();
        BidRecord bidRecord = new BidRecord(commodityId,userId,price);
        Result result = commodityService.saveBidRecord(bidRecord);
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }

    @RequestMapping("/bidRecords")
    public void getBidRecord(HttpServletResponse response, @RequestBody()JsonObject json) throws Exception {
        int commodityId = json.get("commodityId").getAsInt();
        Result result = commodityService.findBidRecordById(commodityId);
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }
}
