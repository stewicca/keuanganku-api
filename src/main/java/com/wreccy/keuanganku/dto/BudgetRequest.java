package com.wreccy.keuanganku.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetRequest {
    private Integer year;

    private Integer month;
}
