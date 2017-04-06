package cx.study.auction.mapper;

import cx.study.auction.pojo.User;

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

    int register(User user);

    User updateUserInfo(User user);

    User resetPassword(User user);


}