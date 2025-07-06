package com.wreccy.keuanganku.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMonthlySalaryRequest {
    private Integer monthly_salary;
}
