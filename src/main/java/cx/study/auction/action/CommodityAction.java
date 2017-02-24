package cx.study.auction.action;

import com.google.gson.Gson;
import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.CommodityImage;
import cx.study.auction.pojo.ResponseMessage;
import cx.study.auction.query.CommodityQuery;
import cx.study.auction.query.CustomerQuery;
import cx.study.auction.service.CommodityService;
import cx.study.auction.util.RequestUtil;
import cx.study.auction.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * Created by AMOBBS on 2017/2/18.
 */
@Controller
@RequestMapping("/admin")
public class CommodityAction {
    private static final int OK = 1;
    private static final int ERROR = 0;
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

    @RequestMapping("/addCommodity")
    public void addCommodity(HttpServletRequest request,HttpServletResponse response) throws Exception{
        Commodity commodity = getCommodityByRequest(request);
        List<CommodityImage> imageList = getImagesByRequest(request);
        int code = commodityService.addCommodity(commodity,imageList);
       // int code = commodityService.saveImages(imageList,commodity1);
        ResponseMessage<String> responseMessage = new ResponseMessage<String>();
        if(code == OK){
            responseMessage.setResultCode(OK);
            responseMessage.setData("添加成功！");
        }else{
            responseMessage.setResultCode(ERROR);
            responseMessage.setData("添加失败！");
        }
        String result = new Gson().toJson(responseMessage,ResponseMessage.class);
        ResponseUtil.writeJson(response,result);
    }



    private List<CommodityImage> getImagesByRequest(HttpServletRequest request){
        String images = RequestUtil.getString(request,"images");
        if(StringUtils.isNotBlank(images)){ 
            String[] urls = images.split(",");
            List<CommodityImage> commodityImages = new ArrayList<>(urls.length);
            for (String url:urls){
                CommodityImage commodityImage = new CommodityImage();
                commodityImage.setUrl(url);
                commodityImages.add(commodityImage);
            }
            return commodityImages;
        }
        return null;
    }
    private Commodity getCommodityByRequest(HttpServletRequest request){
        String name = RequestUtil.getString(request,"name");
        Integer typeId = RequestUtil.getInteger(request,"typeId");
        Integer customerId = RequestUtil.getInteger(request,"customerId");
        double reservePrice = RequestUtil.getBigDecimal(request,"reservePrice");
        String description = RequestUtil.getString(request,"description");
        Commodity commodity = new Commodity();
        commodity.setCommodityName(name);
        commodity.setTypeId(typeId);
        commodity.setCustomerId(customerId);
        commodity.setReservePrice(reservePrice);
        commodity.setDescription(description);
        commodity.setRegistrationTime(new Date());
        commodity.setStatus(0);
        return commodity;
    }

}
