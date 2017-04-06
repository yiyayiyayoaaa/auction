package cx.study.auction.action.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cx.study.auction.mapper.UserMapper;
import cx.study.auction.pojo.User;
import cx.study.auction.service.UserService;
import cx.study.auction.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UnknownFormatConversionException;


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


    /**
     * 注册
     * @throws Exception
     */
    @RequestMapping("/register")
    public void register(HttpServletResponse response, @RequestBody JsonObject json) throws Exception{
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int register = userService.register(user);
        Map<String,Object> map = new HashMap<>();
        if (register == 1){
            map.put("code",0);
            map.put("obj",0);
            map.put("msg","请求成功");
        } else {
            map.put("code",1);
            map.put("obj",1);
            map.put("msg","请求失败");
        }
        ResponseUtil.writeJson(response,new Gson().toJson(map));
    }

    /**
     * 重置密码
     * @throws Exception
     */
    public void resetPassword() throws Exception{

    }

    /**
     * 查看个人信息
     * @throws Exception
     */
    public void findUserInfo() throws Exception{

    }

    /**
     * 修改个人信息
     * @throws Exception
     */
    public void updateUserInfo() throws Exception{

    }

}
