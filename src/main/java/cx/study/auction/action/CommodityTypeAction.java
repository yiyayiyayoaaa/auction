package cx.study.auction.action;

import com.google.gson.Gson;
import cx.study.auction.pojo.CommodityType;
import cx.study.auction.service.CommodityTypeService;
import cx.study.auction.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * Created by AMOBBS on 2017/2/18.
 */
@Controller
@RequestMapping("/admin")
public class CommodityTypeAction {

    @Resource
    private CommodityTypeService commodityTypeService;

    @RequestMapping("/findAllType")
    public void findAll(HttpServletResponse response) throws Exception{
        List<CommodityType> list  = commodityTypeService.findAll();
        ResponseUtil.writeJson(response,new Gson().toJson(list));
    }
}
