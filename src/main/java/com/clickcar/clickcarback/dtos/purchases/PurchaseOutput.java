package com.clickcar.clickcarback.dtos.purchases;

import java.time.LocalDate;

import com.clickcar.clickcarback.entities.Car;
import com.clickcar.clickcarback.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PurchaseOutput {

    private Long id;
    private LocalDate PurchaseDate;
    private User user;
    private Car car;
    
}
