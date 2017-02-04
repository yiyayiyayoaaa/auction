package cx.study.auction.action;

import cx.study.auction.pojo.Admin;
import cx.study.auction.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by AMOBBS on 2017/2/3.
 */
@Controller
@RequestMapping("/admin")
public class AdminAction {
    @Resource
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session, HttpServletRequest request) throws Exception{
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        Admin loginAdmin = adminService.login(admin);
        if ( loginAdmin != null){
            admin.setId(loginAdmin.getId());
            admin.setLast_login_time(new Date());
            adminService.updateLoginTime(admin);
            session.setAttribute("loginAdmin",loginAdmin);
            return "admin/main";
        } else {
            request.setAttribute("loginFail","用户名或密码错误");
            return "admin/login";
        }
    }
}
