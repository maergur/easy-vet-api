package dev.patika.easyvet.business.concretes;

import dev.patika.easyvet.business.abstracts.ICustomerService;
import dev.patika.easyvet.core.exception.NotFoundException;
import dev.patika.easyvet.core.utilities.Msg;
import dev.patika.easyvet.dao.CustomerRepo;
import dev.patika.easyvet.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements ICustomerService {
    private final CustomerRepo customerRepo;

    public CustomerManager(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepo.save(customer);
    }


    @Override
    public Customer get(int id) {
        return this.customerRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }


    @Override
    public Customer getByName(String name) {
        return this.customerRepo.findByName(name);
    }

    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.customerRepo.findAll(pageable);
    }

    @Override
    public Customer update(Customer customer) {
    this.get(Math.toIntExact(customer.getId()));
    return this.customerRepo.save(customer);
    }


    @Override
    public boolean delete(int id) {
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }

    @Override
    public boolean existsByName(String name) {
            return customerRepo.existsByName(name);

    }
}
