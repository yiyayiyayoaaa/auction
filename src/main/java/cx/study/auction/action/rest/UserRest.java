package cx.study.auction.action.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cx.study.auction.mapper.UserMapper;
import cx.study.auction.pojo.Result;
import cx.study.auction.pojo.User;
import cx.study.auction.pojo.UserAddress;
import cx.study.auction.service.UserService;
import cx.study.auction.util.DefaultNicknameUtil;
import cx.study.auction.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 *
 * Created by cheng.xiao on 2017/4/6.
 */
@RequestMapping("/UserRest")
@Controller
public class UserRest {
    @Resource
    UserService userService;
    /**
     * 登录
     * @throws Exception
     */
    @RequestMapping("/login")
    public void login(HttpServletResponse response, @RequestBody JsonObject json) throws Exception{
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User login = userService.login(user);
        Map<String,Object> map = new HashMap<>();
        if (login!= null){
                map.put("code",0);
                map.put("obj",login);
                map.put("msg","请求成功");
            } else {
                map.put("code",1);
                map.put("obj",null);
                map.put("msg","请求失败");
            }
        ResponseUtil.writeJson(response,new Gson().toJson(map));
    }

    @RequestMapping("/userInfo")
    public void userInfo(HttpServletResponse response, @RequestBody JsonObject json) throws Exception {
        int id = json.get("id").getAsInt();
        User user = userService.findUserById(id);
        Result result = new Result<>(0,"",user);
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }
    /**
     * 注册
     * @throws Exception
     */
    @RequestMapping("/register")
    public void register(HttpServletResponse response, @RequestBody JsonObject json) throws Exception{
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        int gender = json.get("gender").getAsInt();
        User user1 = userService.findUserByUsername(username);
        if (user1 != null){
            Result result = new Result<>(1,"该用户名已存在",1);
            ResponseUtil.writeJson(response,new Gson().toJson(result));
            return;
        }
        User user = new User();
        user.setUsername(username);
        user.setGender(gender);
        user.setPassword(password);
        user.setNickname(DefaultNicknameUtil.buildNickname());
        user.setRegistrationTime(new Date());
        int register = userService.register(user);
        Result result;
        if (register == 1){
            result = new Result<>(0,"请求成功",0);
        } else {
            result = new Result<>(1,"请求失败",1);
        }
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }

    /**
     * 重置密码
     * @throws Exception
     */
    public void resetPassword(HttpServletResponse response, @RequestBody JsonObject json) throws Exception{

    }

    /**
     * 查看个人信息
     * @throws Exception
     */
    public void findUserInfo(HttpServletResponse response, @RequestBody JsonObject json) throws Exception{

    }

    /**
     * 修改个人信息
     * @throws Exception
     */
    public void updateUserInfo(HttpServletResponse response, @RequestBody JsonObject json) throws Exception{

    }

    @RequestMapping("addUserAddress")
    public void addUserAddress(HttpServletResponse response, @RequestBody JsonObject json) throws Exception{
        int id = json.get("id").getAsInt();
        String address = json.get("address").getAsString();
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(id);
        userAddress.setAddress(address);
        int i = userService.addUserAddress(userAddress);
        Result result;
        if (i == 1){
            result = new Result<>(0,"请求成功",0);
        } else {
            result = new Result<>(1,"请求失败",1);
        }
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }

    @RequestMapping("/userAddress")
    public void findAllAddressByUser(HttpServletResponse response, @RequestBody JsonObject json) throws Exception{
        int id = json.get("id").getAsInt();
        List<UserAddress> allAddressByUser = userService.findAllAddressByUser(id);
        Result result = new Result<>(0,"",allAddressByUser);
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }

    /**
     * 充值
     * @param response
     * @param json
     */
    @RequestMapping("/recharge")
    public void recharge(HttpServletResponse response, @RequestBody JsonObject json) throws Exception {
        int userId = json.get("userId").getAsInt();
        int money = json.get("money").getAsInt();
        User user = userService.findUserById(userId);
        user.setAccount(user.getAccount() + money);
        int i = userService.updateUserSelective(user);
        Result result;
        if (i == 1){
            result = new Result<>(0,"请求成功",0);
        } else {
            result = new Result<>(1,"请求失败",1);
        }
        ResponseUtil.writeJson(response,new Gson().toJson(result));
    }
}
