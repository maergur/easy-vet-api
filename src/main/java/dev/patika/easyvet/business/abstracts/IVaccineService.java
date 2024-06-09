package dev.patika.easyvet.business.abstracts;

import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.Vaccine;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {

    Vaccine save (Vaccine vaccine);
    Vaccine update (Vaccine vaccine);
    Vaccine getById(Long id);
    void delete(Long id);
    Page<Vaccine> cursor(int page, int pageSize);
    List<Vaccine> getVaccinesByAnimalId(Long animalId);
    List<Vaccine> getAnimalsByExpiringVaccines(LocalDate enterDate, LocalDate exitDate);

}
