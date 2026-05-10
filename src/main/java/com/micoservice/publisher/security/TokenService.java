package com.micoservice.publisher.security;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

    @Value("${jwt.secret:mysecretkey}")
    private String secretKey;
    private final String issuer = "application";


    public String generateToken(String username){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create().withIssuer(issuer).withSubject(username).withExpiresAt(Instant.now().plusSeconds(60*60*24)).sign(algorithm);
    }

    public String validate(String token){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.require(algorithm).withIssuer(issuer).build().verify(token).getSubject();
    }
}
