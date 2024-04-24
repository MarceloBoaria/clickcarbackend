package com.clickcar.clickcarback.dtos.adresses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class AddressInput {

    private Integer cep;
    private Integer number;
    private String complement;
    
}
