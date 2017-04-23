package cx.study.auction.action.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cx.study.auction.pojo.BidRecord;
import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.CommodityType;
import cx.study.auction.pojo.Result;
import cx.study.auction.query.CommodityQuery;
import cx.study.auction.service.CommodityService;
import cx.study.auction.service.CommodityTypeService;
import cx.study.auction.util.ResponseUtil;
import cx.study.auction.vo.CommodityVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by cheng.xiao on 2017/3/25.
 */
@Controller
@RequestMapping("/CommodityRest")
public class CommodityRest {

    @Resource
    private CommodityService commodityService;
    @Resource
    private CommodityTypeService commodityTypeService;
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

    @RequestMapping("/commodityType")
    public void getCommodityTypes(HttpServletResponse response, @RequestBody()JsonObject json) throws Exception {
        List<CommodityType> list  = commodityTypeService.findAll();
        Result<List<CommodityType>> result = new Result<>(0,"",list);
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }

    @RequestMapping("/commodities")
    public void getCommodities(HttpServletResponse response, @RequestBody()JsonObject json) throws Exception {
        int id = json.get("id").getAsInt();
        CommodityQuery query = new CommodityQuery();
        List<CommodityVo> commodityVos = new ArrayList<>();
        if (id < 0){
            switch (id){
                case -1:
                    query.setStatus(Commodity.CommodityStatus.WAIT_AUCTION);
                    List<CommodityVo> list1 = commodityService.findCommodityWithImg(query);
                    query.setStatus(Commodity.CommodityStatus.AUCTION);
                    List<CommodityVo> list2 = commodityService.findCommodityWithImg(query);
                    commodityVos.addAll(list1);
                    commodityVos.addAll(list2);
                    break;
                case -2:
                    query.setStatus(Commodity.CommodityStatus.AUCTION);
                    commodityVos.addAll(commodityService.findCommodityWithImg(query));
                    break;
                case -3:
                    query.setStatus(Commodity.CommodityStatus.WAIT_AUCTION);
                    commodityVos.addAll(commodityService.findCommodityWithImg(query));
                    break;
            }
        } else {
            query.setTypeId(id);
            query.setStatus(Commodity.CommodityStatus.WAIT_AUCTION);
            List<CommodityVo> list1 = commodityService.findCommodityWithImg(query);
            query.setStatus(Commodity.CommodityStatus.AUCTION);
            List<CommodityVo> list2 = commodityService.findCommodityWithImg(query);
            commodityVos.addAll(list1);
            commodityVos.addAll(list2);
        }
        Result result = new Result<>(0,"",commodityVos);
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }

    @RequestMapping("/auction")
    public void auction(HttpServletResponse response, @RequestBody()JsonObject json) throws Exception {
        int userId = json.get("userId").getAsInt();
        List<CommodityVo> commodityVos = commodityService.findCommodity(userId);
        Result result = new Result<>(0,"",commodityVos);
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }
}
