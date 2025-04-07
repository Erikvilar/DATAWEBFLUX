package com.ltadcrm.ltadcrm.security.JWT;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ltadcrm.ltadcrm.security.entity.Account;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String secret;

    public String generatedToken(Account account) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("ltadcrm")
                    .withSubject(account.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
              
            return token.trim();

        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error jwt creation", exception);
        }
    }

    public String validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("ltadcrm")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant genExpirationDate() {

        return ZonedDateTime.now(ZoneOffset.of("-03:00"))
                .plusHours(12)
                .toInstant();
    }
    
    public String getUserByToken(String token){
        DecodedJWT decoded =  JWT.decode(token);
        return decoded.getSubject();
        

    }
}
