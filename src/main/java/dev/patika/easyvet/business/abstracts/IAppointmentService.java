package dev.patika.easyvet.business.abstracts;

import dev.patika.easyvet.entities.Appointment;
import dev.patika.easyvet.entities.AvailableDate;
import dev.patika.easyvet.entities.Customer;
import dev.patika.easyvet.entities.Doctor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    Appointment save (Appointment appointment);
    Appointment update (Appointment appointment);
    Page<Appointment> cursor(int page, int pageSize);
    Appointment getById(Long id);
    void delete(Long id);
    List<Appointment> findAll();

    List<Appointment> filterbyDoctor(String doctorName, LocalDateTime startDate, LocalDateTime endDate);
    public List<Appointment> filterbyAnimal(String animalName, LocalDateTime startDate, LocalDateTime endDate);

    List<Appointment> findByDoctorAndAppointmentDate(
            Doctor doctor, LocalDateTime appointmentDate
    );

    List<Appointment> getAllAppointments();


}
