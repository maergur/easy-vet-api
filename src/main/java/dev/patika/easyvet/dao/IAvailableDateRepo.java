package dev.patika.easyvet.dao;

import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.AvailableDate;
import dev.patika.easyvet.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IAvailableDateRepo extends JpaRepository<AvailableDate,Long> {
    List<AvailableDate> findByDoctorAndAvailableDateDate(
            Doctor doctor, LocalDate availableDate
    );

    List<AvailableDate> findByDoctorId(Long doctorId);

}
