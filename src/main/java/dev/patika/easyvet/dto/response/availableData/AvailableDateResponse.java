package dev.patika.easyvet.dto.response.availableData;

import dev.patika.easyvet.entities.Doctor;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateResponse {
    private LocalDate availableDate;
    private int doctorId;
    private Doctor doctor;

}
