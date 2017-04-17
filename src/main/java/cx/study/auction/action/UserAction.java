package cx.study.auction.action;

import com.google.gson.Gson;
import cx.study.auction.pojo.User;
import cx.study.auction.query.UserQuery;
import cx.study.auction.service.UserService;
import cx.study.auction.util.RequestUtil;
import cx.study.auction.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;
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
 * Created by cheng.xiao on 2017/4/17.
 */
@Controller
@RequestMapping("/admin")
public class UserAction {
    @Resource
    UserService userService;

    @RequestMapping("findUser")
    public void findAllUser(HttpServletResponse response, HttpServletRequest request) throws Exception {
        Integer page = RequestUtil.getInteger(request, "page");
        Integer rows = RequestUtil.getInteger(request, "rows");
        String keyWord = RequestUtil.getString(request, "key");
        UserQuery query = new UserQuery();
        if(page != null && rows != null){
            query.setPageNo(page);
            query.setRows(rows);
        }
        if (!StringUtils.isEmpty(keyWord)){
            query.setKeyWord("%"+keyWord+"%");
        }
        List<User> list = userService.findUser(query);
        Map<String,Object> map = new HashMap<>();
        map.put("rows",list);
        map.put("total",list.size());
        String result = new Gson().toJson(map);
        ResponseUtil.writeJson(response,result);
    }
}
