package dev.patika.easyvet.dto.request.availabledate;

import dev.patika.easyvet.entities.Doctor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.Doc;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateUpdateRequest {
    @NotNull(message = "Available Date can not be null or empty!")
    //@NotEmpty
    private Long id;
    private LocalDate availableDate;
    private int doctorId;
}
