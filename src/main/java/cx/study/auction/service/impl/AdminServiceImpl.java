package cx.study.auction.service.impl;

import cx.study.auction.mapper.AdminMapper;
import cx.study.auction.pojo.Admin;
import cx.study.auction.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by AMOBBS on 2017/2/3.
 */
@Service
public class AdminServiceImpl implements AdminService{
    @Resource
    AdminMapper adminMapper;

    public Admin login(Admin admin) {
        return adminMapper.login(admin);
    }

    public void updateLoginTime(Admin admin) {
        adminMapper.updateLoginTime(admin);
    }

}
