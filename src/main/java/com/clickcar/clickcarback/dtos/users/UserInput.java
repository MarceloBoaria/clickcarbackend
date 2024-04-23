package com.clickcar.clickcarback.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserInput {

    private String name;
    private String email;
    private String cpf;
    private String phone;
    private String password;
    
}
