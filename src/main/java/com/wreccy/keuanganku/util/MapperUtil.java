package com.wreccy.keuanganku.util;

import com.wreccy.keuanganku.dto.*;
import com.wreccy.keuanganku.entity.*;

public class MapperUtil {
    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    public static AuthResponse toAuthResponse(String token) {
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
