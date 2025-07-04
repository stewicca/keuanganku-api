package com.wreccy.keuanganku.service;

import com.wreccy.keuanganku.entity.User;
import com.auth0.jwt.interfaces.DecodedJWT;

public interface JwtService {
    String generateToken(User user);

    DecodedJWT verifyToken(String token);
}
