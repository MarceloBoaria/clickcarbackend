package com.clickcar.clickcarback.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.clickcar.clickcarback.dtos.token.LoginOutpu;
import com.clickcar.clickcarback.entities.User;
import com.clickcar.clickcarback.repositories.UserRepository;

@Service
public class TokenService {

    @Autowired
    private UserRepository repository;

    private String secret = "SECRET_TOKEN";
    private Integer expiration = 1800;
    private String issuer = "clickcar"; // Nome do projeto

    public LoginOutpu createToken(UserDetails userDetails) {
        var algoritmo = Algorithm.HMAC256(secret);
        var token = JWT.create()
                .withIssuer(issuer)
                .withSubject(userDetails.getUsername())
                .withExpiresAt(getExpiration())
                .sign(algoritmo);
        var user = repository.findByCpf(userDetails.getUsername());
        return convertOutput(token, user);
    }

    private Instant getExpiration() {
        return Instant.now().plusSeconds(expiration * 60);
    }

    public String validToken(String jwt) {
        var algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(jwt)
                .getSubject();
    }

    public LoginOutpu convertOutput(String token, User user){
        LoginOutpu login = new LoginOutpu(token, user.getId(), user.getIsAdmin());
        return login;
    }
    
}
