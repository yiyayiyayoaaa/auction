package cx.study.auction.service;

import cx.study.auction.pojo.Customer;
import cx.study.auction.query.CustomerQuery;
import cx.study.auction.vo.CustomerAllVo;

import java.util.List;

/**
 * Created by AMOBBS on 2017/2/4.
 */
public interface CustomerService {
    int addCustomer(Customer customer);
    List<Customer> findCustomer(CustomerQuery customerQuery);
    int getTotalCount();
    int updateCustomer(Customer customer);
    int deleteCustomerById(Integer id);

    List<CustomerAllVo> findAllCustomer();
}
