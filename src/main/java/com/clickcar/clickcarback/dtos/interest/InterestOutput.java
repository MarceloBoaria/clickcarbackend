package com.clickcar.clickcarback.dtos.interest;

import java.time.LocalDate;

import com.clickcar.clickcarback.entities.Car;
import com.clickcar.clickcarback.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class InterestOutput {

    private Long id;
    private LocalDate dateOfInterest;
    private User user;
    private Car car;
    
}
