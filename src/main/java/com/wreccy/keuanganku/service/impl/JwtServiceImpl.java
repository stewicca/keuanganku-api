package com.wreccy.keuanganku.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.*;
import lombok.RequiredArgsConstructor;
import com.wreccy.keuanganku.entity.User;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import com.wreccy.keuanganku.service.JwtService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public String generateToken(User user) {
        try {
            return JWT.create()
                    .withSubject(user.getId())
                    .withClaim("rexp", Instant.now().plus(3600 * 24, ChronoUnit.SECONDS))
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plus(3600, ChronoUnit.SECONDS))
                    .withIssuer("KeuanganKu")
                    .sign(Algorithm.HMAC256("SECRET"));
        } catch (JWTCreationException error) {
            throw new RuntimeException("Error generating token");
        }
    }

    @Override
    public DecodedJWT verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("SECRET"))
                    .withIssuer("KeuanganKu")
                    .build();
            return verifier.verify(parseToken(token));
        } catch (JWTVerificationException error) {
            throw new RuntimeException("Error verifying token");
        }
    }

    private String parseToken(String token) {
        if (token != null && token.startsWith("Bearer")) return token.substring(7);
        return null;
    }
}
