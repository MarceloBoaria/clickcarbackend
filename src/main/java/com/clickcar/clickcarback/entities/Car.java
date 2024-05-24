package com.clickcar.clickcarback.entities;

import java.util.List;

import com.clickcar.clickcarback.entities.enums.Category;
import com.clickcar.clickcarback.entities.enums.Color;
import com.clickcar.clickcarback.entities.enums.Direction;
import com.clickcar.clickcarback.entities.enums.Transmission;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String brand;
    private Integer yearManufacture;
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
    @Enumerated(EnumType.STRING)
    private Color color;
    @Enumerated(EnumType.STRING)
    private Direction direction;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToOne(optional = true)
    private Photograph photograph;
    @ManyToMany(mappedBy = "favorits")
    private List<User> usersFavorits;
    
}
