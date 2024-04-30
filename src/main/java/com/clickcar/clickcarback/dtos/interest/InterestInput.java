package com.clickcar.clickcarback.dtos.interest;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class InterestInput {

    @NotNull
    private Long userId;
    @NotNull
    private Long carId;
    
}
