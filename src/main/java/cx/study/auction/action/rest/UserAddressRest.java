package cx.study.auction.action.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cx.study.auction.pojo.Result;
import cx.study.auction.pojo.User;
import cx.study.auction.pojo.UserAddress;
import cx.study.auction.service.UserService;
import cx.study.auction.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by cheng.xiao on 2017/4/19.
 */
@Controller
@RequestMapping("/UserAddressRest")
public class UserAddressRest {


    @Resource
    private UserService userService;
    @RequestMapping("/getAll")
    public void addressList(HttpServletResponse response, @RequestBody() JsonObject json) throws Exception {
        int id = json.get("id").getAsInt();
        List<UserAddress> list = userService.findAllAddressByUser(id);
        Result result = new Result<>(0,"",list);
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }

    @RequestMapping("/add")
    public void add(HttpServletResponse response,@RequestBody() JsonObject json) throws Exception{
        int userId = json.get("userId").getAsInt();
        String address = json.get("address").getAsString();
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setAddress(address);
        userAddress.setTime(new Date());
        int i = userService.addUserAddress(userAddress);
        Result result;
        if (i == 1){
            result = new Result<>(0,"请求成功",0);
        } else {
            result = new Result<>(1,"请求失败",1);
        }
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }

    @RequestMapping("/update")
    public void update(HttpServletResponse response,@RequestBody() JsonObject json) throws Exception{
        int id = json.get("id").getAsInt();
        int userId = json.get("userId").getAsInt();
        String address = json.get("address").getAsString();
        UserAddress userAddress = new UserAddress();
        userAddress.setId(id);
        userAddress.setUserId(userId);
        userAddress.setAddress(address);
        userAddress.setTime(new Date());
        int i = userService.update(userAddress);
        Result result;
        if (i == 1){
            result = new Result<>(0,"请求成功",0);
        } else {
            result = new Result<>(1,"请求失败",1);
        }
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }
    @RequestMapping("/delete")
    public void delete(HttpServletResponse response,@RequestBody() JsonObject json) throws Exception{
        int id = json.get("id").getAsInt();
        int i = userService.delete(id);
        Result result;
        if (i == 1){
            result = new Result<>(0,"请求成功",0);
        } else {
            result = new Result<>(1,"请求失败",1);
        }
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }
}
