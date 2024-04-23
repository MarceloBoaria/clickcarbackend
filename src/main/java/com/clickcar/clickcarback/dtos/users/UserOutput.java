package com.clickcar.clickcarback.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserOutput {

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String phone;
}
