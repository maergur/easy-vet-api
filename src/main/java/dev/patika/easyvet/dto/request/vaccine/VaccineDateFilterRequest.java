package dev.patika.easyvet.dto.request.vaccine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineDateFilterRequest {
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
}
