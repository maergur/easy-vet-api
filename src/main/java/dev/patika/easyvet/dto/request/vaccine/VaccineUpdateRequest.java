package dev.patika.easyvet.dto.request.vaccine;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineUpdateRequest {

    @NotNull(message = "Cannot be empty")
    private int id;
    private String name;
    private String code;
    private LocalDate protectionSD;
    private LocalDate protectionFD;
    private int animalId;

}
