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
    private String yearManufacture;
    private String mileage;
    private String doors;
    private String eletricGlass;
    private String alarm;
    private String absBrake;
    private String sunroof;
    private String price;
    private String eletricLock;
    private String airbag;
    private String airConditioning;
    private Color color;
    private Direction direction;
    private Transmission transmission;
    private Category category;
    private Integer favoritsNumber;
    
}
