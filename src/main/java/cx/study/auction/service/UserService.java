package cx.study.auction.service;

import cx.study.auction.pojo.User;

/**
 *
 * Created by cheng.xiao on 2017/4/6.
 */
public interface UserService {

    User login(User user);
    int register(User user);
}
