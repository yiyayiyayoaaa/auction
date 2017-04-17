package cx.study.auction.action;

import com.google.gson.Gson;
import cx.study.auction.pojo.CommodityType;
import cx.study.auction.pojo.ResponseMessage;
import cx.study.auction.service.CommodityTypeService;
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
public class CommodityTypeAction {
    private static final int OK = 1;
    private static final int ERROR = 0;
    @Resource
    private CommodityTypeService commodityTypeService;

    @RequestMapping("/findAllType")
    public void findAll(HttpServletResponse response) throws Exception{
        List<CommodityType> list  = commodityTypeService.findAll();
        ResponseUtil.writeJson(response,new Gson().toJson(list));
    }

    @RequestMapping("/findTypeList")
    public void findTypeList(HttpServletResponse response) throws Exception{
        List<CommodityType> list  = commodityTypeService.findAll();
        Map<String,Object> map = new HashMap<>();
        map.put("rows",list);
        ResponseUtil.writeJson(response,new Gson().toJson(map));
    }
    @RequestMapping("deleteType")
    public void delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Integer id = RequestUtil.getInteger(request, "id");
        int code = commodityTypeService.delete(id);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
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
    @RequestMapping("updateType")
    public void update(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Integer id = RequestUtil.getInteger(request, "id");
        String typeName = RequestUtil.getString(request, "typeName");
        String description = RequestUtil.getString(request, "description");
        CommodityType type = new CommodityType();
        type.setId(id);
        type.setTypeName(typeName);
        type.setDescription(description);
        int code = commodityTypeService.update(type);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
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
    @RequestMapping("addType")
    public void add(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String typeName = RequestUtil.getString(request, "typeName");
        String description = RequestUtil.getString(request, "description");
        CommodityType type = new CommodityType();
        type.setTypeName(typeName);
        type.setDescription(description);
        int code = commodityTypeService.addType(type);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
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
}
