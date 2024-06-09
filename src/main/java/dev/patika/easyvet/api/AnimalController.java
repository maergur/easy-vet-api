package dev.patika.easyvet.api;

import dev.patika.easyvet.business.abstracts.IAnimalService;
import dev.patika.easyvet.business.abstracts.ICustomerService;
import dev.patika.easyvet.core.config.modelMapper.IModelMapperService;
import dev.patika.easyvet.core.result.Result;
import dev.patika.easyvet.core.result.ResultData;
import dev.patika.easyvet.core.utilities.ResultHelper;
import dev.patika.easyvet.dto.request.animal.AnimalSaveRequest;
import dev.patika.easyvet.dto.request.animal.AnimalUpdateRequest;
import dev.patika.easyvet.dto.response.CursorResponse;
import dev.patika.easyvet.dto.response.animal.AnimalResponse;
import dev.patika.easyvet.dto.response.customer.CustomerResponse;
import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {

    private final IModelMapperService modelMapper;
    private final ICustomerService customerService;
    private final IAnimalService animalService;

    public AnimalController(
            IModelMapperService modelMapper, ICustomerService customerService, IAnimalService animalService) {

        this.modelMapper = modelMapper;
        this.customerService = customerService;
        this.animalService = animalService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);
        Customer customer = this.customerService.get(animalSaveRequest.getCustomerId());

        saveAnimal.setCustomer(customer);
        this.animalService.save(saveAnimal);

        return ResultHelper.created(this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class));

    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") int id) {
        Animal animal = this.animalService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    @GetMapping("/getByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> getName(@PathVariable("name") String name) {
        Animal animal = this.animalService.getByName(name);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    @GetMapping("/getAnimalsByCustomerId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> getAnimalsByCustomerId(@PathVariable("id") int customerId) {
        return this.animalService.getAnimalsByCustomerId(customerId);
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Animal> animalPage = this.animalService.cursor(page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage
                .map(category -> this.modelMapper.forResponse().map(category, AnimalResponse.class));

        return ResultHelper.cursor(animalResponsePage);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        this.animalService.get(animalUpdateRequest.getId());
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        this.animalService.update(updateAnimal);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAnimal, AnimalResponse.class));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.animalService.delete(id);
        return ResultHelper.ok();
    }

}
