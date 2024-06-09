package dev.patika.easyvet.business.abstracts;

import dev.patika.easyvet.entities.AvailableDate;
import dev.patika.easyvet.entities.Customer;
import dev.patika.easyvet.entities.Doctor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IAvailableDateService {

    AvailableDate save (AvailableDate availableDate);
    AvailableDate update (AvailableDate availableDate);
    Page<AvailableDate> cursor(int page, int pageSize);
    AvailableDate getById(Long id);
    void delete(Long id);
    List<AvailableDate> findAll();

    List<AvailableDate> findByDoctorAndAvailableDateDate(
            Doctor doctor, LocalDate availableDate
    );
}
