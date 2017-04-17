package cx.study.auction.service;

import cx.study.auction.pojo.Order;

/**
 *
 * Created by cheng.xiao on 2017/4/17.
 */
public interface OrderService {

    int addOrder(Order order) throws Exception;

    int updateOrder(Order order) throws Exception;

}
