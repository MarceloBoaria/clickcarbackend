package com.clickcar.clickcarback.dtos.cars;

import java.time.LocalDate;

import com.clickcar.clickcarback.entities.enums.Category;
import com.clickcar.clickcarback.entities.enums.Color;
import com.clickcar.clickcarback.entities.enums.Direction;
import com.clickcar.clickcarback.entities.enums.Transmission;

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
    private Integer doors;
    private Boolean eletricGlass;
    private Boolean alarm;
    private Boolean absBrake;
    private Boolean sunroof;
    private Float price;
    private Boolean eletricLock;
    private Boolean airbag;
    private Boolean airConditioning;
    private Color color;
    private Direction direction;
    private Transmission transmission;
    private Category category;
    private Integer favoritsNumber;
    
}
