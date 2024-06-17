package com.clickcar.clickcarback.dtos.token;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class LoginInput {

    @NotBlank
    private String cpf;
    @NotBlank
    private String password;
    
}
