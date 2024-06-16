package dev.patika.easyvet.dao;

import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepo extends JpaRepository<Animal,Integer> {

    Animal findByName(String name);
    List<Animal> findByCustomerId(int customerId);

}
