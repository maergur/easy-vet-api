package dev.patika.easyvet.dao;

import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.Appointment;
import dev.patika.easyvet.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IAppointmentRepo extends JpaRepository<Appointment,Long> {
    List<Appointment> findByDoctorAndAppointmentDateBetween(
            Doctor doctor, LocalDateTime startDate, LocalDateTime finishDate);

    List<Appointment> findByAnimalAndAppointmentDateBetween(
            Animal animal, LocalDateTime startDate, LocalDateTime finishDate);

    List<Appointment> findByDoctorAndAppointmentDate(
            Doctor doctor, LocalDateTime appointmentDate
    );

}
