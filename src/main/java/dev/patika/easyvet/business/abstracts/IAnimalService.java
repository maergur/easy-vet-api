package dev.patika.easyvet.business.abstracts;

import dev.patika.easyvet.entities.Animal;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAnimalService {
    Animal save(Animal animal);

    Animal get(int id);

    Animal getByName(String name);

    Page<Animal> cursor(int page, int pageSize);

    Animal update(Animal animal);

    boolean delete(int id);

    List<Animal> getAnimalsByCustomerId(int customerId);

    List<Animal> getAnimalsByCustomerName(String customerName);

}
