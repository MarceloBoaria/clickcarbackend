package com.clickcar.clickcarback.dtos.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class LoginOutpu {

    private String token;
    private Long id;
    private Boolean isAdmin;
    
}