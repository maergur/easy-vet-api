package dev.patika.easyvet.dao;

import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    Doctor findByName(String name);
}
