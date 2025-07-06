package com.wreccy.keuanganku.service;

import com.wreccy.keuanganku.dto.*;
import com.wreccy.keuanganku.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getOne(String id);

    UserResponse create(UserRequest request);

    UserResponse updateMonthlySalary(UserMonthlySalaryRequest request);
}
