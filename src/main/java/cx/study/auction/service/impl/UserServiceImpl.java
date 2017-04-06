package cx.study.auction.service.impl;

import cx.study.auction.mapper.UserMapper;
import cx.study.auction.pojo.User;
import cx.study.auction.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 *
 * Created by cheng.xiao on 2017/4/6.
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    UserMapper userMapper;
    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public int register(User user) {
        user.setRegistrationTime(new Date());
        user.setUpdateTime(new Date());
        return userMapper.register(user);
    }
}
