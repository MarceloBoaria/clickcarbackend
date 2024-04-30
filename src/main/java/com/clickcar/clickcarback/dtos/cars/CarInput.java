package com.clickcar.clickcarback.dtos.cars;

import java.time.LocalDate;

import com.clickcar.clickcarback.entities.Details;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CarInput {

    @NotBlank
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private LocalDate yearManufacture;
    @NotNull
    private Float mileage;
    private Details details;
    
}
