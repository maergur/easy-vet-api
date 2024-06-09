package dev.patika.easyvet.dao;

import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepo extends JpaRepository<Animal,Integer> {

    Animal findByName(String name);
    boolean existsByName(String name);
}
