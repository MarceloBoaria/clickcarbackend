package com.clickcar.clickcarback.dtos.cars;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class CarInput {

    private String brand;
    private String model;
    private LocalDate yearManufacture;
    private Float mileage;
    
}
