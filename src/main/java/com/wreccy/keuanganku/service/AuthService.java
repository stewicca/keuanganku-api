package com.wreccy.keuanganku.service;

import com.wreccy.keuanganku.dto.*;

public interface AuthService {
    AuthResponse register(AuthRequest request);

    AuthResponse login(AuthRequest request);

    AuthResponse refresh(String token);
}
