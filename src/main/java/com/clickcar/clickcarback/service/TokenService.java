package com.clickcar.clickcarback.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.clickcar.clickcarback.dtos.token.LoginOutpu;
import com.clickcar.clickcarback.entities.User;

@Service
public class TokenService {

    private String secret = "SECRET_TOKEN";
    private Integer expiration = 30;
    private String issuer = "clickcar"; // Nome do projeto

    public LoginOutpu createToken(User user) {
        var algoritmo = Algorithm.HMAC256(secret);
        var token = JWT.create()
                .withIssuer(issuer)
                .withSubject(user.getUsername())
                .withExpiresAt(getExpiration())
                .sign(algoritmo);
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
