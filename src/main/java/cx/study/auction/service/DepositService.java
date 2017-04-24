package cx.study.auction.service;

import cx.study.auction.pojo.Order;

/**
 *
 * Created by cheng.xiao on 2017/4/24.
 */
public interface DepositService {

    int reverseDeposit(Order order) throws Exception;
}
