package dev.patika.easyvet.dao;


import dev.patika.easyvet.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IVaccineRepo extends JpaRepository<Vaccine,Long> {

    List<Vaccine> findByProtectionFDBetween(
            LocalDate startDate, LocalDate finishDate);


    List<Vaccine> findByNameAndCode(
            String name, String code
    );
}
