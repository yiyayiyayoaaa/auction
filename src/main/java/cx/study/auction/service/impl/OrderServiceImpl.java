package cx.study.auction.service.impl;

import cx.study.auction.mapper.OrderMapper;
import cx.study.auction.pojo.Order;
import cx.study.auction.query.OrderQuery;
import cx.study.auction.service.OrderService;
import cx.study.auction.vo.OrderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * Created by cheng.xiao on 2017/4/17.
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Resource
    OrderMapper orderMapper;
    @Override
    public int addOrder(Order order) throws Exception {
        return orderMapper.insertSelective(order);
    }

    @Override
    public int updateOrder(Order order) throws Exception {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public OrderVo findOrderByUser(int id) throws Exception {
        return orderMapper.findOrderByUser(id);
    }

    @Override
    public List<OrderVo> findAll(OrderQuery query) throws Exception {
        return orderMapper.findAll(query);
    }
}
