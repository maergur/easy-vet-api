package dev.patika.easyvet.dto.request.doctor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdateRequest {
    @Positive(message = "ID must be a positive number!")
    private int id;
    @NotNull(message = "Doctor name can not be empty or null!")
    private String name;
    @NotNull(message = "Doctor phone can not be empty or null!")
    private String phone;
    @NotNull(message = "Doctor mail can not be empty or null!")
    private String mail;
    @NotNull(message = "Doctor address can not be empty or null!")
    private String address;
    @NotNull(message = "Doctor city can not be empty or null!")
    private String city;
}
