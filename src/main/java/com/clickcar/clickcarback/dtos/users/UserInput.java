package com.clickcar.clickcarback.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserInput {

    @NotBlank
    private String name;
    @NotNull @Email
    private String email;
    @Size(min = 11)
    private String cpf;
    @NotNull
    private String phone;
    @NotEmpty @Size(min = 6)
    private String password;
    
}
