package com.clickcar.clickcarback.dtos.cars;

import com.clickcar.clickcarback.entities.Details;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CarInput {

    @NotBlank
    private String model;
    @NotNull
    private String brand;
    @NotNull
    private Integer yearManufacture;
    @NotNull
    private Float mileage;
    private Details details;
    
}
