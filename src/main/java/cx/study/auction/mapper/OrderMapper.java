package cx.study.auction.mapper;

import cx.study.auction.pojo.Order;
import cx.study.auction.query.OrderQuery;
import cx.study.auction.vo.OrderVo;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderVo> findAll(OrderQuery query);

    OrderVo findOrderByUser(int id);
}