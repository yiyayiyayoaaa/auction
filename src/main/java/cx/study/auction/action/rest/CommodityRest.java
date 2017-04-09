package cx.study.auction.action.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
        Map<String,Object> map = new HashMap<>();
        if (commodityVo != null){
            map.put("code",0);
            map.put("obj",commodityVo);
            map.put("msg","请求成功");
        }else{
            map.put("code",-1);
            map.put("obj",null);
            map.put("msg","请求失败");
        }
        ResponseUtil.writeJson(response,new Gson().toJson(map));
    }

    @RequestMapping("/bid")
    public void bid(HttpServletResponse response, @RequestBody()JsonObject json){

    }
}
