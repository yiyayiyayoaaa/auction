package cx.study.auction.service.impl;

import cx.study.auction.mapper.UserMapper;
import cx.study.auction.pojo.User;
import cx.study.auction.pojo.UserAddress;
import cx.study.auction.query.UserQuery;
import cx.study.auction.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by cheng.xiao on 2017/4/6.
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;
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

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public int addUserAddress(UserAddress address) {
        return userMapper.insertAddress(address);
    }

    @Override
    public int update(UserAddress address) {
        return userMapper.updateAddress(address);
    }

    @Override
    public int delete(int id) {
        return userMapper.deleteAddress(id);
    }

    @Override
    public List<UserAddress> findAllAddressByUser(int id) {
        return userMapper.getAddressByUserId(id);
    }

    @Override
    public List<User> findUser(UserQuery query) {
        return userMapper.findUser(query);
    }

    @Override
    public int updateUserSelective(User user) {
        user.setUpdateTime(new Date());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User findUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
