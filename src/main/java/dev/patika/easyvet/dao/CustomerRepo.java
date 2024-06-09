package dev.patika.easyvet.dao;

import dev.patika.easyvet.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    Customer findByName(String name);
    boolean existsByName(String name);

}
