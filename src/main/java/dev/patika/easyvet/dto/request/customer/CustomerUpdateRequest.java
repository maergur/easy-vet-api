package dev.patika.easyvet.dto.request.customer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {
    @Positive(message = "ID must be a positive number!")
    private int id;
    @NotNull(message = "Customer name can not be empty or null!")
    private String name;
    @NotNull(message = "Customer phone can not be empty or null!")
    private String phone;
    @NotNull(message = "Customer mail can not be empty or null!")
    private String mail;
    @NotNull(message = "Customer address can not be empty or null!")
    private String address;
    @NotNull(message = "Customer city can not be empty or null!")
    private String city;
}
