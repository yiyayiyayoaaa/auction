package cx.study.auction.action.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.HomeContentItem;
import cx.study.auction.pojo.HomeItem;
import cx.study.auction.pojo.HomeTitleItem;
import cx.study.auction.query.CommodityQuery;
import cx.study.auction.service.CommodityService;
import cx.study.auction.util.ResponseUtil;
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
        query.setRows(8);
        query.setStatus(Commodity.CommodityStatus.AUCTION);
        List<Commodity> commodityList = commodityService.findCommodity(query);
        List<HomeItem> homeItems = new ArrayList<>();
        HomeItem<String> stringHomeItem1 = new HomeTitleItem();
        stringHomeItem1.setType(HomeItem.TITLE);
        stringHomeItem1.setObj("正在热拍");
        homeItems.add(stringHomeItem1);
        homeItems = getHomeItems(commodityList, homeItems);
        HomeItem<String> stringHomeItem2 = new HomeTitleItem();
        stringHomeItem2.setType(HomeItem.TITLE);
        stringHomeItem2.setObj("即将开始");
        homeItems.add(stringHomeItem2);
        query.setStatus(Commodity.CommodityStatus.WAIT_AUCTION);
        commodityList = commodityService.findCommodity(query);
        homeItems = getHomeItems(commodityList, homeItems);
        ResponseUtil.writeJson(response,new Gson().toJson(homeItems));
    }

    private List<HomeItem> getHomeItems(List<Commodity> commodityList, List<HomeItem> homeItems) {
        for (Commodity commodity : commodityList){
            HomeItem<Commodity> commodityHomeItem = new HomeContentItem();
            commodityHomeItem.setObj(commodity);
            commodityHomeItem.setType(HomeItem.CONTENT);
            homeItems.add(commodityHomeItem);
        }
        return homeItems;
    }


}
