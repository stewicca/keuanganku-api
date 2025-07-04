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

    private int year;

    private int month;

    private int budget_amount;

    private int current_amount;

    private float percentage;
}
