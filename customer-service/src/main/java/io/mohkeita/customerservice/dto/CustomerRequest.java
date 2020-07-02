package io.mohkeita.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class CustomerRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;
}
