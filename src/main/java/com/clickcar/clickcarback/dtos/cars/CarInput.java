package com.clickcar.clickcarback.dtos.cars;

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
    private String yearManufacture;
    @NotNull
    private String mileage;
    @NotNull
    private String doors;
    @NotNull
    private String eletricGlass;
    @NotNull
    private String alarm;
    @NotNull
    private String absBrake;
    @NotNull
    private String sunroof;
    @NotNull
    private String price;
    @NotNull
    private String eletricLock;
    @NotNull
    private String airbag;
    @NotNull
    private String airConditioning;
    @NotNull
    private String color;
    @NotNull
    private String direction;
    @NotNull
    private String transmission;
    @NotNull
    private String category;
    
}
