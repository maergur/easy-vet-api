package dev.patika.easyvet.business.concretes;

import dev.patika.easyvet.business.abstracts.IVaccineService;
import dev.patika.easyvet.core.config.modelMapper.IModelMapperService;
import dev.patika.easyvet.core.utilities.ResultHelper;
import dev.patika.easyvet.dao.IVaccineRepo;
import dev.patika.easyvet.dto.request.vaccine.VaccineDateFilterRequest;
import dev.patika.easyvet.dto.response.vaccine.VaccineResponse;
import dev.patika.easyvet.entities.Vaccine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccineManager implements IVaccineService {
    private final IVaccineRepo vaccineRepo;
    private final EntityManager entityManager;

    private final IModelMapperService modelMapper;


    public VaccineManager(IVaccineRepo vaccineRepo, EntityManager entityManager, IModelMapperService modelMapper) {
        this.vaccineRepo = vaccineRepo;
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        List<Vaccine> vaccineList = this.vaccineRepo.findByNameAndCode(vaccine.getName(),vaccine.getCode());
        if(!vaccineList.isEmpty()){
            for (Vaccine searchVaccine : vaccineList) {
                if(searchVaccine.getProtectionFD().isAfter(vaccine.getProtectionSD()))
                {
                    throw  new RuntimeException("This vaccine is already in use!");
                }
            }
        }
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        getById((long) vaccine.getId());
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public VaccineResponse getById(Long id) {
        Vaccine vaccine = this.vaccineRepo.findById(id).orElse(null);
        if (vaccine == null) {
            throw new RuntimeException(id + " ID was not found!");
        } else {
            return this.modelMapper.forResponse().map(vaccine,VaccineResponse.class);
        }

    }

    @Override
    public void delete(Long id) {
        getById(id);
        this.vaccineRepo.deleteById(id);

    }

    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page,pageSize);
        return this.vaccineRepo.findAll(pageable);
    }


    @Override
    public List<Vaccine> getVaccinesByAnimalId(Long animalId) {
        String queryString = "SELECT v FROM Vaccine v WHERE v.animal.id = :animal_id";

        Query query = entityManager.createQuery(queryString, Vaccine.class);
        query.setParameter("animal_id", animalId);

        return query.getResultList();
    }

    @Override
    public List<Vaccine> getVaccinesByAnimalName(String animalName) {
        // The JPQL query string to fetch vaccines based on animal name
        String queryString = "SELECT v FROM Vaccine v WHERE v.animal.name = :animal_name";

        // Creating a query with the specified JPQL string and setting the result type to Vaccine
        Query query = entityManager.createQuery(queryString, Vaccine.class);

        // Setting the parameter 'animal_name' to the value provided in the method's argument
        query.setParameter("animal_name", animalName);

        // Executing the query and returning the result list
        return query.getResultList();
    }

    public List<Vaccine> checkVaccineByAnimal(Vaccine vaccine) {
        String queryString = "SELECT v FROM Vaccine v WHERE v.animal.id = " +
                ":animal_id AND v.code = :code AND v.name = :vaccine_name " +
                "AND v.protectionFinishDate > :date";

        Query query = entityManager.createQuery(queryString);
        query.setParameter("animal_id", vaccine.getAnimal().getId());
        query.setParameter("code", vaccine.getCode());
        query.setParameter("vaccine_name", vaccine.getName());
        query.setParameter("date", LocalDate.now());

        return query.getResultList();
    }

    public List<Vaccine> getAnimalsByExpiringVaccines(LocalDate enterDate, LocalDate exitDate) {
        VaccineDateFilterRequest vaccineDateFilterRequest = new VaccineDateFilterRequest();
        vaccineDateFilterRequest.setProtectionStartDate(enterDate);
        vaccineDateFilterRequest.setProtectionFinishDate(exitDate);
        List<Vaccine> vaccineList = this.vaccineRepo.findByProtectionFDBetween(
                enterDate,
                exitDate
        );
        return vaccineList;

    }

    @Override
    public List<Vaccine> findAll() {
        return this.vaccineRepo.findAll();
    }

}
