package com.marley_store.stock_system.service.user.jwtToken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.marley_store.stock_system.model.user.userDetailsImpl.UserDetailsImpl;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {
    private static final String SECRET_KEY = "4Z^XrroxR@dWxqf$mTTKwW$!@#qGr4P";
    private static final String ISSUER = "pizzurg-api";
    private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public String generateToken(UserDetailsImpl userDetails){
        try{
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(userDetails.getUsername())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new JWTCreationException("Erro ao gerar token.", exception);
        }
    }

    public String getSubjectFromToken(String token){
        try{
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token invalido ou expirado");
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Brasilia")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Brasilia")).plusHours(4).toInstant();
    }
}
