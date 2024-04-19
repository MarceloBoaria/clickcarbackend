package com.clickcar.clickcarback.entities;

import com.clickcar.clickcarback.entities.enums.Color;
import com.clickcar.clickcarback.entities.enums.Direction;
import com.clickcar.clickcarback.entities.enums.Transmission;

import jakarta.persistence.Embeddable;
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
    private Direction direction;
    private Transmission transmission;
    private Boolean airConditioning;
    
}
