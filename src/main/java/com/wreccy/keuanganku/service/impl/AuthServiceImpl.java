package com.wreccy.keuanganku.service.impl;

import com.wreccy.keuanganku.dto.*;
import com.wreccy.keuanganku.util.*;
import lombok.RequiredArgsConstructor;
import com.wreccy.keuanganku.service.*;
import com.wreccy.keuanganku.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final UserService userService;
    private final ValidationUtil validationUtil;
    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AuthResponse register(AuthRequest request) {
        validationUtil.validate(request);

        UserRequest userRequest = UserRequest.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        userService.create(userRequest);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        return MapperUtil.toAuthResponse(jwtService.generateToken(user));
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        validationUtil.validate(request);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        return MapperUtil.toAuthResponse(jwtService.generateToken(user));
    }
}
