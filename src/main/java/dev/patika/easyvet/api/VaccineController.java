package dev.patika.easyvet.api;

import dev.patika.easyvet.business.abstracts.IVaccineService;
import dev.patika.easyvet.core.config.modelMapper.IModelMapperService;
import dev.patika.easyvet.core.result.ResultData;
import dev.patika.easyvet.core.utilities.ResultHelper;
import dev.patika.easyvet.dto.request.animal.AnimalSaveRequest;
import dev.patika.easyvet.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.easyvet.dto.response.doctor.DoctorResponse;
import dev.patika.easyvet.dto.response.vaccine.VaccineResponse;
import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.Doctor;
import dev.patika.easyvet.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping ("v1/vaccines")
public class VaccineController {

    private final IModelMapperService modelMapper;
    private final IVaccineService vaccineService;


    public VaccineController(IModelMapperService modelMapper, IVaccineService vaccineService) {

        this.modelMapper = modelMapper;
        this.vaccineService = vaccineService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        Vaccine saveVaccine = this.modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
        this.vaccineService.save(saveVaccine);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveVaccine, VaccineResponse.class));
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Vaccine update (@RequestBody Vaccine vaccine) {
        return this.vaccineService.update(vaccine);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    void delete (@PathVariable("id") Long id){
        this.vaccineService.delete(id);
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vaccine getById(@PathVariable("id") Long id) {
        return this.vaccineService.getById(id);
    }

    @GetMapping("/getVaccinesByAnimalId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> getVaccinesByAnimalId(@PathVariable("id") Long id) {
        return this.vaccineService.getVaccinesByAnimalId(id);
    }

    @GetMapping("/getAnimalsByExpiringVaccines")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> getAnimalsByComingVaccines(
            @RequestParam(name = "vaccine_protection_sd") LocalDate enterDate ,
            @RequestParam(name = "vaccine_protection_fd") LocalDate exitDate) {

        return this.vaccineService.getAnimalsByExpiringVaccines(enterDate, exitDate);
    }

}