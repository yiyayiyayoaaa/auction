package cx.study.auction.action.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import cx.study.auction.pojo.*;
import cx.study.auction.query.CommodityQuery;
import cx.study.auction.service.CommodityService;
import cx.study.auction.util.ResponseUtil;
import cx.study.auction.vo.CommodityVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by cheng.xiao on 2017/3/10.
 */

@RequestMapping("/rest")
@Controller
public class HomePageRest {
    @Resource
    private CommodityService commodityService;
    @RequestMapping("/homeInfo")
    public void homeInfo(HttpServletResponse response) throws Exception {
        CommodityQuery query = new CommodityQuery();
        query.setPageNo(1);
        query.setRows(6);
        query.setStatus(Commodity.CommodityStatus.AUCTION);
        List<CommodityVo> commodityList = commodityService.findCommodityWithImg(query);
        List<HomeItem> homeItems = new ArrayList<>();
        HomeItem<String> stringHomeItem1 = new HomeTitleItem();
        stringHomeItem1.setType(HomeItem.TITLE);
        stringHomeItem1.setObj("正在热拍");
        homeItems.add(stringHomeItem1);
        getHomeItems(commodityList, homeItems);
        HomeItem<String> stringHomeItem2 = new HomeTitleItem();
        stringHomeItem2.setType(HomeItem.TITLE);
        stringHomeItem2.setObj("即将开始");
        homeItems.add(stringHomeItem2);
        query.setStatus(Commodity.CommodityStatus.WAIT_AUCTION);
        commodityList = commodityService.findCommodityWithImg(query);
        getHomeItems(commodityList, homeItems);
        Result result = new Result(0,"",homeItems);
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }

    private void getHomeItems(List<CommodityVo> commodityList, List<HomeItem> homeItems) {
        for (Commodity commodity : commodityList){
            HomeItem<Commodity> commodityHomeItem = new HomeContentItem();
            commodityHomeItem.setObj(commodity);
            commodityHomeItem.setType(HomeItem.CONTENT);
            homeItems.add(commodityHomeItem);
        }
    }


}
