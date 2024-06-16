package dev.patika.easyvet.business.abstracts;

import dev.patika.easyvet.dto.response.vaccine.VaccineResponse;
import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.Vaccine;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {

    Vaccine save (Vaccine vaccine);
    Vaccine update (Vaccine vaccine);
    VaccineResponse getById(Long id);
    void delete(Long id);
    Page<Vaccine> cursor(int page, int pageSize);
    List<Vaccine> getVaccinesByAnimalId(Long animalId);

    List<Vaccine> getVaccinesByAnimalName(String animalName);

    List<Vaccine> getAnimalsByExpiringVaccines(LocalDate enterDate, LocalDate exitDate);

    List<Vaccine> findAll();
}
