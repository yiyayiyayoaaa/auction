package cx.study.auction.service.impl;

import cx.study.auction.mapper.CustomerMapper;
import cx.study.auction.pojo.Customer;
import cx.study.auction.query.CustomerQuery;
import cx.study.auction.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by AMOBBS on 2017/2/4.
 */
@Service
public class CustomerServiceImpl implements CustomerService{
    @Resource
    private CustomerMapper customerMapper;

    public int addCustomer(Customer customer) {
        return customerMapper.addCustomer(customer);
    }

    public List<Customer> findCustomer(CustomerQuery customerQuery) {
        return customerMapper.findCustomer(customerQuery);
    }

    public int getTotalCount() {
        return customerMapper.getTotalCount();
    }

    public int deleteCustomerById(Integer id) {
        return customerMapper.deleteCustomerById(id);
    }
}
