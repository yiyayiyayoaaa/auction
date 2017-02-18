package cx.study.auction.action;

import com.google.gson.Gson;
import cx.study.auction.pojo.Commodity;
import cx.study.auction.query.CommodityQuery;
import cx.study.auction.query.CustomerQuery;
import cx.study.auction.service.CommodityService;
import cx.study.auction.util.RequestUtil;
import cx.study.auction.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by AMOBBS on 2017/2/18.
 */
@Controller
@RequestMapping("/admin")
public class CommodityAction {

    @Resource
    private CommodityService commodityService;

    @RequestMapping("/findCommodity")
    public void findCommodity(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer page = RequestUtil.getInteger(request, "page");
        Integer rows = RequestUtil.getInteger(request, "rows");
        CommodityQuery query = new CommodityQuery();
        if(page != null && rows != null){
            query.setPageNo(page);
            query.setRows(rows);
        }
        List<Commodity> list = commodityService.findCommodity(query);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",list.size());
        String result = new Gson().toJson(map);
        ResponseUtil.writeJson(response,result);
    }
}
