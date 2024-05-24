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
    private Integer yearManufacture;
    @NotNull
    private Float mileage;
    @NotNull
    private Integer doors;
    @NotNull
    private Boolean eletricGlass;
    @NotNull
    private Boolean alarm;
    @NotNull
    private Boolean absBrake;
    @NotNull
    private Boolean sunroof;
    @NotNull
    private Float price;
    @NotNull
    private Boolean eletricLock;
    @NotNull
    private Boolean airbag;
    @NotNull
    private Boolean airConditioning;
    @NotNull
    private String color;
    @NotNull
    private String direction;
    @NotNull
    private String transmission;
    @NotNull
    private String category;
    
}
