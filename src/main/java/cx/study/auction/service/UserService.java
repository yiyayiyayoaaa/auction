package cx.study.auction.service;

import cx.study.auction.pojo.User;
import cx.study.auction.pojo.UserAddress;
import cx.study.auction.query.UserQuery;

import java.util.List;

/**
 *
 * Created by cheng.xiao on 2017/4/6.
 */
public interface UserService {

    User login(User user);
    int register(User user);
    User findUserByUsername(String username);
    int addUserAddress(UserAddress address);
    int update(UserAddress address);
    int delete(int id);

    List<UserAddress> findAllAddressByUser(int id);

    List<User> findUser(UserQuery query);

    int updateUserSelective(User user);

    User findUserById(int id);
}
