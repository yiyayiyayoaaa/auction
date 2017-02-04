package cx.study.auction.mapper;

import cx.study.auction.pojo.Logging;

public interface LoggingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Logging record);

    int insertSelective(Logging record);

    Logging selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Logging record);

    int updateByPrimaryKey(Logging record);
}