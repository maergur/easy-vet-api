package dev.patika.easyvet.business.concretes;


import dev.patika.easyvet.business.abstracts.IAnimalService;
import dev.patika.easyvet.business.abstracts.IAppointmentService;
import dev.patika.easyvet.business.abstracts.IAvailableDateService;
import dev.patika.easyvet.business.abstracts.IDoctorService;
import dev.patika.easyvet.core.exception.AvailabilityException;
import dev.patika.easyvet.core.utilities.ResultHelper;
import dev.patika.easyvet.dao.AnimalRepo;
import dev.patika.easyvet.dao.IAppointmentRepo;
import dev.patika.easyvet.dto.request.appointmentdate.AppointmentFilterByAnimal;
import dev.patika.easyvet.dto.request.appointmentdate.AppointmentFilterByDoctor;
import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.Appointment;
import dev.patika.easyvet.entities.AvailableDate;
import dev.patika.easyvet.entities.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentManager implements IAppointmentService {
    private final IAppointmentRepo appointmentRepo;
    private final IDoctorService doctorService;
    private final IAnimalService animalService;
    private final IAvailableDateService availableDateService;
    private final EntityManager entityManager;


    public AppointmentManager(IAppointmentRepo appointmentRepo, IDoctorService doctorRepo, IDoctorService doctorService, IAnimalService animalService, IAvailableDateService availableDateService, EntityManager entityManager) {
        this.appointmentRepo = appointmentRepo;
        this.doctorService = doctorService;
        this.animalService = animalService;
        this.availableDateService = availableDateService;
        this.entityManager = entityManager;
    }

    @Override
    public Appointment save(Appointment appointment) {
    if(appointment.getAppointmentDate().getMinute() != 0){
       throw new RuntimeException("Please enter in full hour notation!");
    }


    List<AvailableDate> availableDates = availableDateService.findByDoctorAndAvailableDateDate(
            appointment.getDoctor(),
            appointment.getAppointmentDate().toLocalDate()
    );

        List<Appointment> availableAppointments =this.findByDoctorAndAppointmentDate(
                appointment.getDoctor(),
                appointment.getAppointmentDate()
        );

    if (availableDates.isEmpty()) {
        throw new RuntimeException("There is no availability!");
    }

        if (!availableAppointments.isEmpty()) {
            throw new RuntimeException("There is no available time slots!");
        }

    return this.appointmentRepo.save(appointment);

    }

    @Override
    public Appointment update(Appointment appointment) {
        getById(appointment.getId());
        return this.appointmentRepo.save(appointment);

    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.appointmentRepo.findAll(pageable);
    }

    @Override
    public Appointment getById(Long id) {

        Appointment appointment = this.appointmentRepo.findById(id).orElse(null);
        if (appointment == null) {
            throw new RuntimeException(id + " ID was not found!");
        } else {
            return appointment;
        }
    }

    @Override
    public void delete(Long id) {
        getById(id);
        this.appointmentRepo.deleteById(id);
    }


    @Override
    public List<Appointment> findAll() {
        return this.appointmentRepo.findAll();
    }

    @Override
    public List<Appointment> filterbyDoctor(String doctorName, LocalDateTime startDate, LocalDateTime endDate) {
        AppointmentFilterByDoctor appointmentFilterByDoctorDTO = new AppointmentFilterByDoctor();
        appointmentFilterByDoctorDTO.setDoctorName(doctorName);
        appointmentFilterByDoctorDTO.setStartDate(startDate);
        appointmentFilterByDoctorDTO.setEndDate(endDate);
        Doctor doctor = this.doctorService.getByName(doctorName);

        List<Appointment> appointmentList = this.appointmentRepo.findByDoctorAndAppointmentDateBetween(
                doctor,
                startDate,
                endDate
        );

        return appointmentList;    }

    @Override
    public List<Appointment> filterbyAnimal(String animalName, LocalDateTime startDate, LocalDateTime endDate) {
        AppointmentFilterByAnimal appointmentFilterByAnimalDTO = new AppointmentFilterByAnimal();
        appointmentFilterByAnimalDTO.setAnimalName(animalName);
        appointmentFilterByAnimalDTO.setStartDate(startDate);
        appointmentFilterByAnimalDTO.setEndDate(endDate);
        Animal animal = this.animalService.getByName(animalName);

        List<Appointment> appointmentList = this.appointmentRepo.findByAnimalAndAppointmentDateBetween(
                animal,
                startDate,
                endDate
        );

        return appointmentList;    }


    @Override
    public List<Appointment> findByDoctorAndAppointmentDate(Doctor doctor, LocalDateTime appointmentDate) {
        return this.appointmentRepo.findByDoctorAndAppointmentDate(doctor, appointmentDate);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return this.appointmentRepo.findAll();
    }

}

