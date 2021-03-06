package cx.study.auction.mapper;

import cx.study.auction.pojo.User;
import cx.study.auction.pojo.UserAddress;
import cx.study.auction.query.UserQuery;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**********************************************************************/
    User findUserById(int id);

    User login(User user);

    User findUserByUsername(String username);

    int register(User user);

    User updateUserInfo(User user);

    User resetPassword(User user);

    int insertAddress(UserAddress address);

    List<UserAddress> getAddressByUserId(int id);

    int deleteAddress(int id);

    int updateAddress(UserAddress address);

    List<User> findUser(UserQuery query);
}