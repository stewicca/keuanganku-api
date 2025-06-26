package com.wreccy.keuanganku.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.wreccy.keuanganku.entity.User;

public interface JwtService {
    String generateToken(User user);

    DecodedJWT verifyToken(String token);
}
