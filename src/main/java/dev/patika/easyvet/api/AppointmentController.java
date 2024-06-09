package dev.patika.easyvet.api;

import dev.patika.easyvet.business.abstracts.IAnimalService;
import dev.patika.easyvet.business.abstracts.IAppointmentService;
import dev.patika.easyvet.business.abstracts.IDoctorService;
import dev.patika.easyvet.core.config.modelMapper.IModelMapperService;
import dev.patika.easyvet.core.result.Result;
import dev.patika.easyvet.core.result.ResultData;
import dev.patika.easyvet.core.utilities.ResultHelper;
import dev.patika.easyvet.dto.request.appointmentdate.AppointmentDateSaveRequest;
import dev.patika.easyvet.dto.response.animal.AnimalResponse;
import dev.patika.easyvet.dto.response.appointment.AppointmentDateResponse;
import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.Appointment;
import dev.patika.easyvet.entities.Customer;
import dev.patika.easyvet.entities.Doctor;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping ("v1/appointments")
public class AppointmentController {
    private final IAppointmentService appointmentService;
    private final IModelMapperService modelMapper;
    private final IAnimalService animalService;
    private final IDoctorService doctorService;

    public AppointmentController(IAppointmentService appointmentService, IModelMapperService modelMapper, IAnimalService animalService, IDoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
        this.animalService = animalService;
        this.doctorService = doctorService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentDateResponse> save(@Valid @RequestBody AppointmentDateSaveRequest appointmentDateSaveRequest) {
        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentDateSaveRequest, Appointment.class);
        Animal animal = this.animalService.get(appointmentDateSaveRequest.getAnimalId());
        Doctor doctor = this.doctorService.get(appointmentDateSaveRequest.getDoctorId());

        saveAppointment.setAnimal(animal);
        saveAppointment.setDoctor(doctor);
        this.appointmentService.save(saveAppointment);

        return ResultHelper.created(this.modelMapper.forResponse().map(saveAppointment, AppointmentDateResponse.class));
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Appointment update (@RequestBody Appointment appointment) {
        return this.appointmentService.update(appointment);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    void delete (@PathVariable("id") Long id){
        this.appointmentService.delete(id);
    }
    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Appointment getById(@PathVariable("id") Long id) {
        return this.appointmentService.getById(id);
    }


    @GetMapping("/filterByDoctor")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> filterByDoctor(
            @RequestParam(name = "doctor_id") int doctorId ,
            @RequestParam(name = "startDate") LocalDateTime startDate ,
            @RequestParam(name = "endDate") LocalDateTime endDate) {

        return this.appointmentService.filterbyDoctor(doctorId, startDate, endDate);
    }



    @GetMapping("/filterByAnimal")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> filterByAnimal(
            @RequestParam(name = "animal_id") int animalId ,
            @RequestParam(name = "startDate") LocalDateTime startDate ,
            @RequestParam(name = "endDate") LocalDateTime endDate) {
        return this.appointmentService.filterbyAnimal(animalId, startDate, endDate);
    }

}
