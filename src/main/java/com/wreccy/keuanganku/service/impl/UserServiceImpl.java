package com.wreccy.keuanganku.service.impl;

import com.wreccy.keuanganku.dto.*;
import com.wreccy.keuanganku.util.*;
import lombok.RequiredArgsConstructor;
import com.wreccy.keuanganku.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.wreccy.keuanganku.service.UserService;
import com.wreccy.keuanganku.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final ValidationUtil validationUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getOne(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public UserResponse create(UserRequest request) {
        validationUtil.validate(request);

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return MapperUtil.toUserResponse(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }

        return user.get();
    }
}
