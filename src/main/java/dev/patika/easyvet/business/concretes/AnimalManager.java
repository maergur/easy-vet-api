package dev.patika.easyvet.business.concretes;

import dev.patika.easyvet.business.abstracts.IAnimalService;
import dev.patika.easyvet.core.exception.DuplicateException;
import dev.patika.easyvet.core.exception.NotFoundException;
import dev.patika.easyvet.core.utilities.Msg;
import dev.patika.easyvet.dao.AnimalRepo;
import dev.patika.easyvet.dao.CustomerRepo;
import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.Customer;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

@Service
public class AnimalManager implements IAnimalService {
    private final AnimalRepo animalRepo;
    private final CustomerRepo customerRepo;
    private final EntityManager entityManager;


    public AnimalManager (AnimalRepo animalRepo, CustomerRepo customerRepo, EntityManager entityManager) {
        this.animalRepo = animalRepo;
        this.customerRepo = customerRepo;
        this.entityManager = entityManager;
    }

    @Override
    public Animal save(Animal animal) {
        if (animalRepo.existsById(animal.getId())) {
            throw new DuplicateException("Animal with ID " + animal.getId() + " already exists");
        }

        return this.animalRepo.save(animal);
    }

    @Override
    public Animal get(int id) {
        return this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }
    @Override
    public Animal getByName(String name) {
        return this.animalRepo.findByName(name);
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.animalRepo.findAll(pageable);
    }

    @Override
    public Animal update(Animal animal) {

        Animal checkAnimal = get(Math.toIntExact(animal.getId()));
        if (checkAnimal != null) {
            return this.animalRepo.save(animal);
        } else {
            throw new RuntimeException(Msg.NOT_FOUND);
        }
    }

    @Override
    public boolean delete(int id) {
        Animal animal = this.get(id);
        this.animalRepo.delete(animal);
        return true;
    }

    @Override
    public List<Animal> getAnimalsByCustomerId(int customerId) {

        Customer customer = this.customerRepo.findById(customerId).
                orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        return customer.getAnimalList();
    }

    @Override
    public List<Animal> getAnimalsByCustomerName(String customerName) {
        Customer customer = customerRepo.findByName(customerName);
        if (customer != null) {
            return animalRepo.findByCustomerId(customer.getId());
        }
        return null;

    }


}
