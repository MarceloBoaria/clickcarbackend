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
