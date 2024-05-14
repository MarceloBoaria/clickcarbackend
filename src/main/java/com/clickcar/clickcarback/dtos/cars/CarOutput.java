package com.clickcar.clickcarback.dtos.cars;

import java.time.LocalDate;

import com.clickcar.clickcarback.entities.Details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CarOutput {

    private Long id;
    private String model;
    private String brand;
    private LocalDate yearManufacture;
    private Float mileage;
    private Details details;
    
}
