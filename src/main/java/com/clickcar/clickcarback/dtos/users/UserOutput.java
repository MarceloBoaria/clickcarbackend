package com.clickcar.clickcarback.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserOutput {

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String phone;
}
