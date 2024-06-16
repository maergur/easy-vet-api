package dev.patika.easyvet.dto.response.vaccine;

import dev.patika.easyvet.entities.Report;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineResponse {

    @NotNull(message = "Cannot be empty")
    private int id;
    private String name;
    private String code;
    private LocalDate protectionSD;
    private LocalDate protectionFD;
    private int animalId;
    private Report report;

}
