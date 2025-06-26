package com.wreccy.keuanganku.controller;

import org.springframework.http.*;
import lombok.RequiredArgsConstructor;
import com.wreccy.keuanganku.dto.AuthRequest;
import com.wreccy.keuanganku.util.ResponseUtil;
import com.wreccy.keuanganku.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        return ResponseUtil.buildResponse(
                HttpStatus.CREATED,
                "Registration successful",
                authService.register(request)
        );
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                "Login successful",
                authService.login(request)
        );
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<?> refresh(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {
        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                "Token refreshed",
                authService.refresh(token)
        );
    }
}
