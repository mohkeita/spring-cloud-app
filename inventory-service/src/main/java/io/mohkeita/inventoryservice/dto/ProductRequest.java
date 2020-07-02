package io.mohkeita.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class ProductRequest {

    @NotEmpty
    private String name;
    //@NotEmpty(message = "Please enter price")
    private double price;
}
