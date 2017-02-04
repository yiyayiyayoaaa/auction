package cx.study.auction.service;

import cx.study.auction.pojo.Admin;

import java.util.Date;

/**
 * Created by AMOBBS on 2017/2/3.
 */
public interface AdminService {
    Admin login(Admin admin);

    void updateLoginTime(Admin admin);
}
