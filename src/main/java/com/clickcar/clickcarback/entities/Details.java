package com.clickcar.clickcarback.entities;

import com.clickcar.clickcarback.entities.enums.Color;
import com.clickcar.clickcarback.entities.enums.Direction;
import com.clickcar.clickcarback.entities.enums.Transmission;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Embeddable
public class Details {

    private Boolean absBrake;
    private Boolean electricGlass;
    private Color color;
    private Boolean sunroof;
    private Integer doors;
    private Boolean eletricLock;
    private Boolean alarm;
    private Boolean airBag;
    @Enumerated(EnumType.STRING)
    private Direction direction;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    private Boolean airConditioning;
    
}
