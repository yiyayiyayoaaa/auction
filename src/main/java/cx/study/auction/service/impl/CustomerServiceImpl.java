package cx.study.auction.service.impl;

import cx.study.auction.mapper.CustomerMapper;
import cx.study.auction.pojo.Customer;
import cx.study.auction.query.CustomerQuery;
import cx.study.auction.service.CustomerService;
import cx.study.auction.vo.CustomerAllVo;
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
        if(customerQuery.getName() != null){
            customerQuery.setName("%" + customerQuery.getName() + "%");
        }
        return customerMapper.findCustomer(customerQuery);
    }

    public int getTotalCount() {
        return customerMapper.getTotalCount();
    }

    public int updateCustomer(Customer customer) {
        return customerMapper.updateByPrimaryKeySelective(customer);
    }

    public int deleteCustomerById(Integer id) {
        return customerMapper.deleteCustomerById(id);
    }

    @Override
    public List<CustomerAllVo> findAllCustomer() {
        return customerMapper.findAllCustomer();
    }
}
