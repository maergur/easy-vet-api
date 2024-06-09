package dev.patika.easyvet.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {
    @NotNull
    private String name;

    @NotNull
    @Email
    private String mail;

    @NotNull
    private String phone;

    @NotNull
    private String address;

    @NotNull
    private String city;
}
