package com.clickcar.clickcarback.dtos.favorits;

import java.time.LocalDate;

import com.clickcar.clickcarback.entities.Car;
import com.clickcar.clickcarback.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class FavoriteOutput {

    private Long id;
    private LocalDate FavoriteDate;
    private User user;
    private Car car;
    
}
