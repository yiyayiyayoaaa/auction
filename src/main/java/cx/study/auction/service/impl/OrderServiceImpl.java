package cx.study.auction.service.impl;

import cx.study.auction.mapper.CommodityMapper;
import cx.study.auction.mapper.OrderMapper;
import cx.study.auction.mapper.UserMapper;
import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.Order;
import cx.study.auction.pojo.User;
import cx.study.auction.query.OrderQuery;
import cx.study.auction.service.OrderService;
import cx.study.auction.service.UserService;
import cx.study.auction.vo.OrderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by cheng.xiao on 2017/4/17.
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Resource
    OrderMapper orderMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    CommodityMapper commodityMapper;
    @Override
    public int addOrder(Order order) throws Exception {
        return orderMapper.insertSelective(order);
    }

    @Override
    public int updateOrder(Order order) throws Exception {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public List<OrderVo> findOrderByUser(OrderQuery query) throws Exception {
        return orderMapper.findOrderByUser(query);
    }

    @Override
    public List<OrderVo> findAll(OrderQuery query) throws Exception {
        return orderMapper.findAll(query);
    }

    @Override
    public int pay(int id) throws Exception {
        //找出订单
        Order order = orderMapper.selectByPrimaryKey(id);
        if (order.getStatus() == 0){
            Double price = order.getPrice();
            //找出订单对应用户
            User user = userMapper.selectByPrimaryKey(order.getUserId());
            //找出订单对应商品
            Commodity commodity = commodityMapper.findCommodityById(order.getCommodityId());
            Double account = user.getAccount();
            double deposit = commodity.getBiddingDeposit();//保证金
            if ((account + deposit) < price){
                //余额不足 返回 -1
                return -1;
            }
            user.setAccount(account - price + deposit);//退还保证金
            int i = userMapper.updateByPrimaryKeySelective(user);//从用户账户中扣除钱
            if (i> 0){
                order.setStatus(1);//设置订单状态为已支付
                order.setPayTime(new Date());
                order.setUpdateTime(new Date());
                return orderMapper.updateByPrimaryKeySelective(order);
            }
            return i;
        }
        return 0;
    }

    @Override
    public int cancel(int id) throws Exception {
        Order order = orderMapper.selectByPrimaryKey(id);
        //判断当前订单是否已支付
        if (order.getStatus() != 4){
            if (order.getStatus() == 0){
                //未支付  //取消订单  不退保证金
                order.setStatus(4);
                order.setUpdateTime(new Date());
                orderMapper.updateByPrimaryKeySelective(order);
            } else {
                //已支付  //取消订单，退还支付金额，扣除保证金 + 百分之5的费用
                //查询对应订单及用户
                User user = userMapper.selectByPrimaryKey(order.getUserId());
                //找出订单对应商品
                Commodity commodity = commodityMapper.findCommodityById(order.getCommodityId());
                Double price = order.getPrice();
                user.setAccount(user.getAccount() + price * 0.95 - commodity.getBiddingDeposit());
                int i = userMapper.updateByPrimaryKeySelective(user);
                commodity.setStatus(Commodity.CommodityStatus.CANCEL);
                if (i>0){
                    order.setStatus(4);
                    commodityMapper.updateCommodity(commodity);
                }
            }
            orderMapper.updateByPrimaryKeySelective(order);
        }
        return 0;
    }
}
