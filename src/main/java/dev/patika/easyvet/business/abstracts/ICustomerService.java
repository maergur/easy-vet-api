package dev.patika.easyvet.business.abstracts;

import dev.patika.easyvet.entities.Customer;
import org.springframework.data.domain.Page;

public interface ICustomerService {

    Customer save(Customer customer);
    Customer get(int id);
    Customer getByName(String name);
    Page<Customer> cursor(int page, int pageSize);
    Customer update(Customer customer);
    boolean delete(int id);
    boolean existsByName(String name);
}
