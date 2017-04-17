package cx.study.auction.action;

import com.google.gson.Gson;
import cx.study.auction.util.RequestUtil;
import cx.study.auction.util.ResponseUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by cheng.xiao on 2017/2/3.
 */
@Controller
@RequestMapping("/admin")
public class AdminPageAction {

    @RequestMapping("/loginPage")
    public String toLogin(){
        return "admin/login";
    }

    @RequestMapping("/customerList")
    public String toFindCustomer(){
        return "admin/customerList";
    }

    @RequestMapping("/commodityList")
    public String toCommodityList(){
        return "admin/commodityList";
    }

    @RequestMapping("/userList")
    public String toUserList(){
        return "admin/userList";
    }

    @RequestMapping("/typeList")
    public String toTypeList(){
        return "admin/commodityType";
    }
    @RequestMapping("testLogin")
    public void test(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String loginId = RequestUtil.getString(request,"loginId");
        String password = RequestUtil.getString(request,"password");
        String json = "{" +
                "'patientId': '123456789'," +
                "'patientName': '高永俊'," +
                "'photo': '78/45/784546A579D614E5D033351AEF77E037'," +
                "'loginId': '371326198808082653'," +
                "'phone': '13800138000'," +
                "'genderType': 0," +
                "'age': 46," +
                "'height': 175.5," +
                "'weight': 63.4," +
                "'id': '123'," +
                "'outpatientId':'4567'," +
                "'departmentType': '心电科'," +
                "'bedNum': '301-8'," +
                "'hospitalNum': '42080819'," +
                "'remark': ''," +
                "'doctorId': '42000001'," +
                "'doctorName':'王医生'," +
                "'hospital':'微心百源测试医院'" +
                "}";
        Map map = new HashMap();
        map.put("code",0);
        map.put("msg","123");
        map.put("obj",json);
        ResponseUtil.writeJson(response,new Gson().toJson(map));
    }

    @RequestMapping("/register")
    public void register(HttpServletRequest request,HttpServletResponse response,@RequestBody String str) throws Exception {
        System.out.println(str);
        Map map = new HashMap();
        map.put("code",0);
        map.put("msg","123");
        String json = "{'patientId': '123456789'}";
        map.put("obj",json);
        ResponseUtil.writeJson(response,new Gson().toJson(map));
    }

}
