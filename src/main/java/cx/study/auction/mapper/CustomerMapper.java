package cx.study.auction.mapper;

import cx.study.auction.pojo.Customer;
import cx.study.auction.query.CustomerQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerMapper {
    int addCustomer(Customer customer);
    List<Customer> findCustomer(CustomerQuery customerQuery);
    int getTotalCount();
    int updateCustomer(Customer customer);
    int deleteCustomerById(Integer id);
}