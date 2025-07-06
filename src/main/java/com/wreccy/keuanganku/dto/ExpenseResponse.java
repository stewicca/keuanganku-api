package com.wreccy.keuanganku.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponse {
    private String id;

    private Integer amount;

    private String description;

    private LocalDate date;

    private String category;
}
