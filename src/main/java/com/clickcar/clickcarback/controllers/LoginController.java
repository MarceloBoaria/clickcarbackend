package com.clickcar.clickcarback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickcar.clickcarback.dtos.token.LoginInput;
import com.clickcar.clickcarback.dtos.token.LoginOutpu;
import com.clickcar.clickcarback.service.TokenService;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private TokenService service;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public LoginOutpu login(@RequestBody LoginInput input) {
        var user = new UsernamePasswordAuthenticationToken(
            input.getCpf(), input.getPassword()
        );
        var auth = manager.authenticate(user);
         LoginOutpu token = service.createToken((UserDetails) auth.getPrincipal());
        return token;
    }
    
}
