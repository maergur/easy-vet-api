package dev.patika.easyvet.api;


import dev.patika.easyvet.business.abstracts.IAvailableDateService;
import dev.patika.easyvet.core.config.modelMapper.IModelMapperService;
import dev.patika.easyvet.core.result.ResultData;
import dev.patika.easyvet.core.utilities.ResultHelper;
import dev.patika.easyvet.dto.response.CursorResponse;
import dev.patika.easyvet.dto.response.animal.AnimalResponse;
import dev.patika.easyvet.dto.response.availableData.AvailableDateResponse;
import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.AvailableDate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("v1/availabledates") public class AvailableDateController {
    private final IAvailableDateService availableDateService;
    private IModelMapperService modelMapper;

    public AvailableDateController(IAvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public AvailableDate save(@RequestBody AvailableDate availableDate)  {
        return this.availableDateService.save(availableDate);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDate update (@RequestBody AvailableDate availableDate) {
        return this.availableDateService.update(availableDate);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    void delete (@PathVariable("id") Long id){
        this.availableDateService.delete(id);
    }


    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDate getById(@PathVariable("id") Long id) {
        return this.availableDateService.getById(id);
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDate> getAllAvailableDates() {
        return this.availableDateService.getAllAvailableDates();
    }

    @GetMapping("/getDatesByDoctorName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDate> getDatesByDoctorNames(@PathVariable("name") String name) {
        return this.availableDateService.getAvailableDatesByDoctorName(name);
    }
}

