package com.wreccy.keuanganku.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;

    private String username;

    private Integer monthly_salary;

    private Integer region_umr;
}
