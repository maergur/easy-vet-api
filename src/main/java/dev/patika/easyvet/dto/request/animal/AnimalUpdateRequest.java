package dev.patika.easyvet.dto.request.animal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalUpdateRequest {
    @Positive(message = "ID must be a positive number!")
    private int id;
    @NotNull(message = "Animal name can not be empty or null!")
    private String name;
    private String species;
    private String breed;
    @NotNull(message = "Animal gender can not be empty or null!")
    private String gender;
    private String color;
    @NotNull(message = "Animal date of birth can not be empty or null!")
    private LocalDate dateOfBirth;
    @NotNull(message = "Animal customer id can not be empty or null!")
    private int customerId;
}
