package com.clickcar.clickcarback.dtos.users;

import com.clickcar.clickcarback.entities.Address;

import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserOutput {

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String phone;

    @OneToOne
    private Address address;
}
