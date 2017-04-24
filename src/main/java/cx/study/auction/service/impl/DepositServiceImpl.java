package cx.study.auction.service.impl;

import cx.study.auction.pojo.Deposit;
import cx.study.auction.pojo.Order;
import cx.study.auction.pojo.User;
import cx.study.auction.service.CommodityService;
import cx.study.auction.service.DepositService;
import cx.study.auction.service.UserService;
import cx.study.auction.vo.CommodityVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by cheng.xiao on 2017/4/24.
 */
@Service
public class DepositServiceImpl implements DepositService{
    @Resource
    private UserService userService;
    @Resource
    private CommodityService commodityService;

    @Override
    public int reverseDeposit(Order order) throws Exception {
        int commodityId  = order.getCommodityId();
        int userId = order.getUserId();
        CommodityVo commodityVo = commodityService.findCommodityById(commodityId);
        double depositPrice = commodityVo.getBiddingDeposit();
        List<Deposit> deposits = commodityService.depositRecordByCommodityId(commodityId);
        int i = 0;
        for(Deposit deposit : deposits){
            if (deposit.getUserId() != userId){
                User user = userService.findUserById(deposit.getUserId());
                user.setAccount(user.getAccount() + depositPrice);
                user.setUpdateTime(new Date());
                i = userService.updateUserSelective(user);
            }
        }
        return i;
    }
}
