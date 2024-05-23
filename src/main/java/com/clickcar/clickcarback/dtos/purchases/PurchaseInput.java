package com.clickcar.clickcarback.dtos.purchases;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PurchaseInput {

    @NotNull
    private Long userId;
    @NotNull
    private Long carId;
    
}
