package com.clickcar.clickcarback.dtos.users;

import com.clickcar.clickcarback.entities.Address;

import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserInput {

    private String name;
    private String email;
    private String cpf;
    private String phone;
    private String password;

    @OneToOne
    private Address address;
    
}
