package cx.study.auction.service;

import cx.study.auction.pojo.Order;
import cx.study.auction.query.OrderQuery;
import cx.study.auction.vo.OrderVo;

import java.util.List;

/**
 *
 * Created by cheng.xiao on 2017/4/17.
 */
public interface OrderService {

    int addOrder(Order order) throws Exception;

    int updateOrder(Order order) throws Exception;

    OrderVo findOrderByUser(int id) throws Exception;

    List<OrderVo> findAll(OrderQuery query) throws Exception;
}
