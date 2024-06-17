package com.clickcar.clickcarback.dtos.favorits;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class FavoritInput {

    @NotNull
    private Long userId;
    @NotNull
    private Long carId;
    
}
