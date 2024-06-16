package dev.patika.easyvet.business.concretes;

import dev.patika.easyvet.business.abstracts.IAvailableDateService;
import dev.patika.easyvet.dao.DoctorRepo;
import dev.patika.easyvet.dao.IAvailableDateRepo;
import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.AvailableDate;
import dev.patika.easyvet.entities.Customer;
import dev.patika.easyvet.entities.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AvailableDateManager implements IAvailableDateService {
    private final IAvailableDateRepo availableDateRepo;

    private final DoctorRepo doctorRepo;
    private final EntityManager entityManager;

    public AvailableDateManager(IAvailableDateRepo availableDateRepo, DoctorRepo doctorRepo, EntityManager entityManager) {
        this.availableDateRepo = availableDateRepo;
        this.doctorRepo = doctorRepo;
        this.entityManager = entityManager;
    }


    @Override
    public AvailableDate save(AvailableDate availableDate) {
        List<AvailableDate> availableDates = checkAvailableDatesByDoctor(availableDate);
        if (availableDates.size() == 0) {
            return this.availableDateRepo.save(availableDate);
        } else {
            throw new RuntimeException("Please enter a valid date!");
        }
    }


    @Override
    public AvailableDate update(AvailableDate availableDate) {
        getById(availableDate.getId());
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public Page<AvailableDate> cursor(int page, int pageSize) {
        return null;
    }

    @Override
    public AvailableDate getById(Long id) {

        AvailableDate availableDate = this.availableDateRepo.findById(id).orElse(null);
        if (availableDate == null) {
            throw new RuntimeException(id + " ID was not found!");
        } else {
            return availableDate;
        }
    }

    @Override
    public void delete(Long id) {
        AvailableDate checkAvailableDate = getById(id);
        if (checkAvailableDate != null) {
            this.availableDateRepo.deleteById(id);
        } else {
            throw new RuntimeException(id + " ID was not found!");
        }
    }

    @Override
    public List<AvailableDate> findAll() {
        return this.availableDateRepo.findAll();
    }

    @Override
    public List<AvailableDate> findByDoctorAndAvailableDateDate(Doctor doctor, LocalDate availableDate) {
        return this.availableDateRepo.findByDoctorAndAvailableDateDate(doctor,availableDate);
    }

    @Override
    public List<AvailableDate> getAllAvailableDates() {
        return this.availableDateRepo.findAll();
    }

    @Override
    public List<AvailableDate> getAvailableDatesByDoctorName(String doctorName) {
        Doctor doctor = doctorRepo.findByName(doctorName);
        if (doctor != null) {
            return availableDateRepo.findByDoctorId(doctor.getId());
        }
        return null;
    }

    public List<AvailableDate> checkAvailableDatesByDoctor(AvailableDate availableDate) {
        String queryString = "SELECT a FROM AvailableDate a WHERE a.doctor.id = :doctor_id AND a.availableDateDate = :available_date";

        Query query = entityManager.createQuery(queryString);
        query.setParameter("doctor_id", availableDate.getDoctor().getId());
        query.setParameter("available_date", availableDate.getAvailableDateDate());

        return query.getResultList();
    }
}
