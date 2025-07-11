package com.wreccy.keuanganku.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetResponse {
    private String id;

    private String name;

    private String label;

    private Integer year;

    private Integer month;

    private Integer budget_amount;

    private Integer current_amount;

    private Float percentage;
}
